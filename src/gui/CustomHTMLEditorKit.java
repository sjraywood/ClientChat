package gui;

import javax.swing.text.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public final class CustomHTMLEditorKit extends HTMLEditorKit {
    private static HTMLFactory factory = null;

    @Override
    public ViewFactory getViewFactory() {
        if (factory == null) {
            factory = new MyHTMLFactory();
        }
        return factory;
    }

    private static class MyHTMLFactory extends HTMLFactory {

        @Override
        public View create(Element elem) {
            AttributeSet attrs = elem.getAttributes();
            Object elementName = attrs.getAttribute(AbstractDocument.ElementNameAttribute);
            Object o = (elementName != null) ? null : attrs.getAttribute(StyleConstants.NameAttribute);
            if (o instanceof HTML.Tag) {
                HTML.Tag kind = (HTML.Tag) o;
                if (kind == HTML.Tag.IMG) {
                    // HERE is the call to the special class...
                    return new CustomImageView(elem);
                }
            }
            return super.create(elem);
        }
    }
}
