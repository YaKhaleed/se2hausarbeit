package org.se2.gui.components;

import com.vaadin.ui.Button;

/**
 @author zmorin2s
 */

public class ButtonComponent extends Button {
    public ButtonComponent(String caption, String width) {
        this.setCaption(caption);
        this.setWidth(width);
    }
}

