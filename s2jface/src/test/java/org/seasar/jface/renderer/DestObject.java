/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.renderer;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
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

    public void setBooleanProperty(boolean booleanProperty) {
        this.booleanProperty = booleanProperty;
    }

    public Color getColorProperty() {
        return this.colorProperty;
    }

    public void setColorProperty(Color colorProperty) {
        this.colorProperty = colorProperty;
    }

    public int getIntProperty() {
        return this.intProperty;
    }

    public void setIntProperty(int intProperty) {
        this.intProperty = intProperty;
    }

    public String getStringProperty() {
        return this.stringProperty;
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public int getSwtConstProperty() {
        return this.swtConstProperty;
    }

    public void setSwtConstProperty(int swtConstProperty) {
        this.swtConstProperty = swtConstProperty;
    }

    public String getTextProperty() {
        return this.textProperty;
    }

    public void setTextProperty(String textProperty) {
        this.textProperty = textProperty;
    }

    public Image getImageProperty() {
        return this.imageProperty;
    }

    public void setImageProperty(Image imageProperty) {
        this.imageProperty = imageProperty;
    }

    public int getAcceleratorProperty() {
        return this.acceleratorProperty;
    }

    public void setAcceleratorProperty(int acceleratorProperty) {
        this.acceleratorProperty = acceleratorProperty;
    }

    public char getCharProperty() {
        return this.charProperty;
    }

    public void setCharProperty(char charProperty) {
        this.charProperty = charProperty;
    }

    public int[] getIntArrayProperty() {
        return this.intArrayProperty;
    }

    public void setIntArrayProperty(int[] intArrayProperty) {
        this.intArrayProperty = intArrayProperty;
    }
    
}
