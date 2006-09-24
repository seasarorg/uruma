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
import org.seasar.jface.component.LayoutDataInfo;
import org.seasar.jface.component.UIControlComponent;

/**
 * @author y-komori
 */
public abstract class ControlComponent extends AbstractUIComponent implements
        UIControlComponent {
    private LayoutDataInfo layoutDataInfo;

    @ComponentAttribute(conversionType = ConversionType.COLOR)
    private String background;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String backgroundImage;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String enabled;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontHeight;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontName;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String fontStyle;

    @ComponentAttribute(conversionType = ConversionType.COLOR)
    private String foreground;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String height;

    @ComponentAttribute(conversionType = ConversionType.STRING)
    private String toolTipText;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String visible;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String width;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String x;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String y;

    public String getBackground() {
        return this.background;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public String getFontHeight() {
        return this.fontHeight;
    }

    public String getFontName() {
        return this.fontName;
    }

    public String getFontStyle() {
        return this.fontStyle;
    }

    public String getForeground() {
        return this.foreground;
    }

    public String getHeight() {
        return this.height;
    }

    public String getToolTipText() {
        return this.toolTipText;
    }

    public String getVisible() {
        return this.visible;
    }

    public String getWidth() {
        return this.width;
    }

    public String getX() {
        return this.x;
    }

    public String getY() {
        return this.y;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public void setFontHeight(String fontHeight) {
        this.fontHeight = fontHeight;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public LayoutDataInfo getLayoutDataInfo() {
        return this.layoutDataInfo;
    }

    public void setLayoutDataInfo(LayoutDataInfo layoutDataInfo) {
        this.layoutDataInfo = layoutDataInfo;
    }

}
