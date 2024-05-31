package org.vaadin.addons.de.f0rce;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.addons.de.f0rce.exception.MarkedAlreadyInitializedException;

/** Test view for manual and automated testing of the component. */
@Route("")
public class ExampleView extends VerticalLayout {
  Marked marked;

  public ExampleView() {
    this.setSizeFull();

    try {
      marked = new Marked(UI.getCurrent());
    } catch (MarkedAlreadyInitializedException e) {
    }

    Div div = new Div();
    div.setWidthFull();
    div.setHeightFull();

    marked.setMarkdown(
        "# GFM\n"
            + "\n"
            + "## Autolink literals\n"
            + "\n"
            + "www.example.com, https://example.com, and contact@example.com.\n"
            + "\n"
            + "## Footnote\n"
            + "\n"
            + "A note[^1]\n"
            + "\n"
            + "[^1]: Big note.\n"
            + "\n"
            + "## Strikethrough\n"
            + "\n"
            + "~one~ or ~~two~~ tildes.\n"
            + "\n"
            + "## Table\n"
            + "<a href=\"https://google.com\" target=\"_blank\">Google</a>"
            + "\n"
            + "| This header spans two   || Header A |\n"
            + "| columns *and* two rows ^|| Header B |\n"
            + "|-------------|------------|----------|\n"
            + "| Cell A      | Cell B     | Cell C   |"
            + "\n"
            + "## Tasklist\n"
            + "\n"
            + "* [ ] to do\n"
            + "* [x] done",
        div);

    this.add(div);
  }
}
