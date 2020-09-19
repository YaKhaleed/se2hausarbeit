package org.se2.gui.components;

import com.vaadin.ui.ComboBox;
/**
@author zmorin2s
 */
public class AnredeField extends ComboBox {
    public AnredeField() {
        this.setItems("Herr", "Frau");
        this.setPlaceholder("Anrede");
        this.setWidth("500px");
    }
}

