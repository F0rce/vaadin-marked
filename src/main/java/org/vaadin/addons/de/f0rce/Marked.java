package org.vaadin.addons.de.f0rce;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import org.vaadin.addons.de.f0rce.constants.MarkedConstants;
import org.vaadin.addons.de.f0rce.constants.MarkedOperations;
import org.vaadin.addons.de.f0rce.exception.MarkedAlreadyInitializedException;
import org.vaadin.addons.de.f0rce.util.MarkedProvider;
import org.vaadin.addons.de.f0rce.util.MarkedUtil;

public class Marked implements Serializable {

  private static final long serialVersionUID = -7185977334346585663L;

  private final UI ui;
  private final MarkedProvider markedProvider = new MarkedProvider();

  private HashMap<Component, String> state = new HashMap<>();

  private boolean hasBeenDetached = false;

  public static Marked getCurrent() {
    return get(UI.getCurrent());
  }

  public static Marked get(UI ui) {
    return (Marked) ComponentUtil.getData(ui, MarkedConstants.UI_KEY);
  }

  private static void set(UI ui, Marked marked) {
    ComponentUtil.setData(ui, MarkedConstants.UI_KEY, marked);
  }

  public Marked(UI ui) throws MarkedAlreadyInitializedException {
    this.ui = ui;

    if (Marked.get(ui) != null) {
      throw new MarkedAlreadyInitializedException();
    }

    this.ui.add(markedProvider);
    initListeners();

    Marked.set(ui, this);
  }

  private void initListeners() {
    this.markedProvider.addAttachListener(
        event -> {
          if (this.hasBeenDetached) {
            for (Entry<Component, String> e : this.state.entrySet()) {
              this.setMarkdown(e.getValue(), e.getKey());
            }

            this.hasBeenDetached = false;
          }
        });

    this.markedProvider.addDetachListener(
        event -> {
          this.hasBeenDetached = true;
        });
  }

  private UI getUI(Optional<Component> component) {
    Optional<UI> optionalUI = component.get().getUI();

    if (optionalUI.isPresent()) {
      UI componentUI = optionalUI.get();

      if (componentUI != ui) {
        return componentUI;
      }
    }

    return ui;
  }

  public void setMarkdown(String markdown, Component component) {
    UI ui = getUI(Optional.of(component));

    MarkedUtil.securelyAccessUI(
        ui,
        () -> {
          this.state.put(component, markdown);

          component
              .getElement()
              .executeJs(MarkedOperations.INJECT_HTML_FROM_MARKDOWN, markdown, component);
        });
  }
}
