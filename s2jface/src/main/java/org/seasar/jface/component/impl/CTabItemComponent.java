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
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;

/**
 * @author bskuroneko
 * 
 */
public class CTabItemComponent extends AbstractUIContainerItemComponent {

    @ComponentAttribute(conversionType = ConversionType.TEXT)
    private String toolTipText;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontHeight;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontName;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontStyle;

    public String getToolTipText() {
        return this.toolTipText;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    public String getFontHeight() {
        return this.fontHeight;
    }

    public void setFontHeight(String fontHeight) {
        this.fontHeight = fontHeight;
    }

    public String getFontName() {
        return this.fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontStyle() {
        return this.fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }
}
