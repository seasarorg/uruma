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
package org.seasar.uruma.component.jface;

import org.seasar.uruma.component.CommonAttributes;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * {@link CommonAttributes} の実装クラスです。<br />
 * 
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

    /*
     * @see org.seasar.uruma.component.CommonAttributes#getBackGround()
     */
    public String getBackGround() {
        return this.backGround;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#setBackGround(java.lang.String)
     */
    public void setBackGround(final String backGround) {
        this.backGround = backGround;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#getFontHeight()
     */
    public String getFontHeight() {
        return this.fontHeight;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#setFontHeight(java.lang.String)
     */
    public void setFontHeight(final String fontHeight) {
        this.fontHeight = fontHeight;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#getFontName()
     */
    public String getFontName() {
        return this.fontName;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#setFontName(java.lang.String)
     */
    public void setFontName(final String fontName) {
        this.fontName = fontName;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#getFontStyle()
     */
    public String getFontStyle() {
        return this.fontStyle;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#setFontStyle(java.lang.String)
     */
    public void setFontStyle(final String fontStyle) {
        this.fontStyle = fontStyle;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#getForeGround()
     */
    public String getForeGround() {
        return this.foreGround;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#setForeGround(java.lang.String)
     */
    public void setForeGround(final String foreGround) {
        this.foreGround = foreGround;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#getHeight()
     */
    public String getHeight() {
        return this.height;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#setHeight(java.lang.String)
     */
    public void setHeight(final String height) {
        this.height = height;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#getWidth()
     */
    public String getWidth() {
        return this.width;
    }

    /*
     * @see org.seasar.uruma.component.CommonAttributes#setWidth(java.lang.String)
     */
    public void setWidth(final String width) {
        this.width = width;
    }
}
