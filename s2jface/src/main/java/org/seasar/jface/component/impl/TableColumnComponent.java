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
package org.seasar.jface.component.impl;

import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;

/**
 * @author bskuroneko
 */
public class TableColumnComponent extends AbstractItemComponent {
    
    @ComponentAttribute(conversionType = ConversionType.SWT_CONST)
    private String alignment;
    
    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String moveable;
    
    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String resizable;
    
    @ComponentAttribute(conversionType = ConversionType.TEXT)
    private String toolTipText;
    
    @ComponentAttribute(conversionType = ConversionType.INT)
    private String width;

    public String getAlignment() {
        return this.alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getMoveable() {
        return this.moveable;
    }

    public void setMoveable(String moveable) {
        this.moveable = moveable;
    }

    public String getResizable() {
        return this.resizable;
    }

    public void setResizable(String resizable) {
        this.resizable = resizable;
    }

    public String getToolTipText() {
        return this.toolTipText;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
    
}
