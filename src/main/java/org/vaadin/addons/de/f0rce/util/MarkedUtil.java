package org.vaadin.addons.de.f0rce.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;

public final class MarkedUtil {

  private MarkedUtil() {}

  public static void securelyAccessUI(UI ui, Command command) {
    if (ui != null && !ui.isClosing() && ui.getSession() != null) {
      if (ui.getSession().hasLock()) {
        command.execute();
      } else {
        ui.access(command);
      }
    }
  }
}
