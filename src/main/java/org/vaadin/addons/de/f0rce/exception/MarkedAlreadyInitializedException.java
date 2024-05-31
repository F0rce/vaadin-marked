package org.vaadin.addons.de.f0rce.exception;

public class MarkedAlreadyInitializedException extends Exception {

  private static final long serialVersionUID = -944792450484997977L;

  public MarkedAlreadyInitializedException() {
    super(
        "There is already another instance of Marked for this UI.\\nCall Marked.get(UI) to get it.");
  }
}
