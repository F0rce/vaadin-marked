package org.vaadin.addons.de.f0rce.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

// Marked
@NpmPackage(value = "marked", version = "12.0.0")
// Marked extensions
@NpmPackage(value = "marked-alert", version = "2.0.1")
@NpmPackage(value = "marked-custom-heading-id", version = "2.0.7")
@NpmPackage(value = "marked-extended-tables", version = "1.0.8")
@NpmPackage(value = "marked-footnote", version = "1.2.2")
@NpmPackage(value = "highlight.js", version = "11.9.0")
@NpmPackage(value = "marked-highlight", version = "2.1.1")
@NpmPackage(value = "marked-mangle", version = "1.1.7")
// Sanitization
@NpmPackage(value = "dompurify", version = "3.0.9")
@NpmPackage(value = "@types/dompurify", version = "3.0.5")
// Logic
@JsModule("./@f0rce/marked.ts")
@Tag("div")
public class MarkedProvider extends Component {

  private static final long serialVersionUID = 5323161787016747207L;
}
