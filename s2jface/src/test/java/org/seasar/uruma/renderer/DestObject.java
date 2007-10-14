/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.uruma.renderer;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * {@link RendererSupportUtil} のテストのためのクラスです。<br />
 * 
 * @author y-komori
 */
public class DestObject {
    public String nonTargetField;

    public String stringField;

    public String textField;

    public int intField;

    public int[] intArrayField;

    public boolean booleanField;

    public Color colorField;

    public int swtConstField;

    public Image imageField;

    public int acceleratorField;

    public char charField;

    private String stringProperty;

    private String textProperty;

    private int intProperty;

    private int[] intArrayProperty;

    private boolean booleanProperty;

    private Color colorProperty;

    private int swtConstProperty;

    private Image imageProperty;

    private int acceleratorProperty;

    private char charProperty;

    public boolean getBooleanProperty() {
        return this.booleanProperty;
    }

    public void setBooleanProperty(final boolean booleanProperty) {
        this.booleanProperty = booleanProperty;
    }

    public Color getColorProperty() {
        return this.colorProperty;
    }

    public void setColorProperty(final Color colorProperty) {
        this.colorProperty = colorProperty;
    }

    public int getIntProperty() {
        return this.intProperty;
    }

    public void setIntProperty(final int intProperty) {
        this.intProperty = intProperty;
    }

    public String getStringProperty() {
        return this.stringProperty;
    }

    public void setStringProperty(final String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public int getSwtConstProperty() {
        return this.swtConstProperty;
    }

    public void setSwtConstProperty(final int swtConstProperty) {
        this.swtConstProperty = swtConstProperty;
    }

    public String getTextProperty() {
        return this.textProperty;
    }

    public void setTextProperty(final String textProperty) {
        this.textProperty = textProperty;
    }

    public Image getImageProperty() {
        return this.imageProperty;
    }

    public void setImageProperty(final Image imageProperty) {
        this.imageProperty = imageProperty;
    }

    public int getAcceleratorProperty() {
        return this.acceleratorProperty;
    }

    public void setAcceleratorProperty(final int acceleratorProperty) {
        this.acceleratorProperty = acceleratorProperty;
    }

    public char getCharProperty() {
        return this.charProperty;
    }

    public void setCharProperty(final char charProperty) {
        this.charProperty = charProperty;
    }

    public int[] getIntArrayProperty() {
        return this.intArrayProperty;
    }

    public void setIntArrayProperty(final int[] intArrayProperty) {
        this.intArrayProperty = intArrayProperty;
    }

}
