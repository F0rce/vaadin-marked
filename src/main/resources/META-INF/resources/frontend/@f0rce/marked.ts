/* Sanitization */
import DOMPurify from "dompurify";

/* Marked */
import { Marked } from "marked";

/* Marked extensions */
import markedAlert from "marked-alert";
// @ts-ignore
import customHeadingId from "marked-custom-heading-id";
// @ts-ignore
import extendedTables from "marked-extended-tables";
import markedFootnote from "marked-footnote";
import hljs from "highlight.js";
import "highlight.js/styles/default.css";
import { markedHighlight } from "marked-highlight";
import { mangle } from "marked-mangle";

const _marked = {
  injectHTML: async (markdown: string, element: HTMLElement) => {
    const marked = new Marked();
    marked.options({ async: true, gfm: true, breaks: true });
    marked.use(
      markedAlert(),
      customHeadingId(),
      extendedTables(),
      markedFootnote(),
      markedHighlight({
        langPrefix: "hljs language-",
        highlight(code, lang, info) {
          const language = hljs.getLanguage(lang) ? lang : "plaintext";
          return hljs.highlight(code, { language }).value;
        },
      }),
      mangle()
    );

    DOMPurify.addHook("afterSanitizeAttributes", function (node) {
      if ("target" in node) {
        node.setAttribute("target", "_blank");
        node.setAttribute("rel", "noopener");
      }
    });

    element.innerHTML = DOMPurify.sanitize(
      await marked.parse(
        markdown.replace(/^[\u200B\u200C\u200D\u200E\u200F\uFEFF]/, "")
      )
    );
  },
};

if (!window.f0rce) {
  window.f0rce = {};
}

window.f0rce.marked = _marked;

declare global {
  interface Window {
    f0rce?: {
      marked?: typeof _marked;
    };
  }
}
