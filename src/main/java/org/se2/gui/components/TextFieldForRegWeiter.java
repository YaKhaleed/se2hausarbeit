package org.se2.gui.components;

import com.vaadin.ui.TextField;

public class TextFieldForRegWeiter extends TextField {
    public TextFieldForRegWeiter(String caption, String width) {
        this.setPlaceholder(caption);
        this.setWidth(width);
    }
}

