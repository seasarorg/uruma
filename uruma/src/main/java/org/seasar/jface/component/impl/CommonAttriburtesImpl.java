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
package org.seasar.jface.component.impl;

import org.seasar.jface.component.CommonAttributes;

/**
 * @author y-komori
 */
public class CommonAttriburtesImpl extends AbstractUIElement implements
        CommonAttributes {
    private String foreGround;

    private String backGround;

    private String width;

    private String height;

    private String fontName;

    private String fontHeight;

    private String fontStyle;

    public String getBackGround() {
        return this.backGround;
    }

    public void setBackGround(String backGround) {
        this.backGround = backGround;
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

    public String getForeGround() {
        return this.foreGround;
    }

    public void setForeGround(String foreGround) {
        this.foreGround = foreGround;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
