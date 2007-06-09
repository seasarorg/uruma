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

import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;

/**
 * @author bskuroneko
 * 
 */
public class CTabFolderComponent extends CompositeComponent {
    
    private GradientInfo selectionBackgroundGradient;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String selection;

    @ComponentAttribute(conversionType = ConversionType.INT, targetType = TargetType.FIELD)
    private String marginHeight;

    @ComponentAttribute(conversionType = ConversionType.INT, targetType = TargetType.FIELD)
    private String marginWidth;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String borderVisible;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String maximized;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String maximizeVisible;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String minimized;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String minimizeVisible;

    @ComponentAttribute(conversionType = ConversionType.INT)
    private String minimumCharacters;
    
    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String mruVisible;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String selectionBackground;

    @ComponentAttribute(conversionType = ConversionType.IMAGE)
    private String selectionBackgroundImage;

    @ComponentAttribute(conversionType = ConversionType.COLOR)
    private String selectionForeground;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String simple;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String single;

    @ComponentAttribute(conversionType = ConversionType.INT)
    private String tabHeight;
    
    @ComponentAttribute(conversionType = ConversionType.INT)
    private String tabPosition;
    
    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String unselectedCloseVisible;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String unselectedImageVisible;

    public String getSelection() {
        return this.selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getBorderVisible() {
        return this.borderVisible;
    }

    public void setBorderVisible(String borderVisible) {
        this.borderVisible = borderVisible;
    }

    public String getMarginHeight() {
        return this.marginHeight;
    }

    public void setMarginHeight(String marginHeight) {
        this.marginHeight = marginHeight;
    }

    public String getMarginWidth() {
        return this.marginWidth;
    }

    public void setMarginWidth(String marginWidth) {
        this.marginWidth = marginWidth;
    }

    public String getMaximized() {
        return this.maximized;
    }

    public void setMaximized(String maximized) {
        this.maximized = maximized;
    }

    public String getMaximizeVisible() {
        return this.maximizeVisible;
    }

    public void setMaximizeVisible(String maximizeVisible) {
        this.maximizeVisible = maximizeVisible;
    }

    public String getMinimized() {
        return this.minimized;
    }

    public void setMinimized(String minimized) {
        this.minimized = minimized;
    }

    public String getMinimizeVisible() {
        return this.minimizeVisible;
    }

    public void setMinimizeVisible(String minimizeVisible) {
        this.minimizeVisible = minimizeVisible;
    }

    public String getMinimumCharacters() {
        return this.minimumCharacters;
    }

    public void setMinimumCharacters(String minimumCharacters) {
        this.minimumCharacters = minimumCharacters;
    }

    public String getMruVisible() {
        return this.mruVisible;
    }

    public void setMruVisible(String mruVisible) {
        this.mruVisible = mruVisible;
    }

    public String getSelectionBackground() {
        return this.selectionBackground;
    }

    public void setSelectionBackground(String selectionBackground) {
        this.selectionBackground = selectionBackground;
    }

    public String getSelectionBackgroundImage() {
        return this.selectionBackgroundImage;
    }

    public void setSelectionBackgroundImage(String selectionBackgroundImage) {
        this.selectionBackgroundImage = selectionBackgroundImage;
    }

    public String getSelectionForeground() {
        return this.selectionForeground;
    }

    public void setSelectionForeground(String selectionForeground) {
        this.selectionForeground = selectionForeground;
    }

    public String getSimple() {
        return this.simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public String getSingle() {
        return this.single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getTabHeight() {
        return this.tabHeight;
    }

    public void setTabHeight(String tabHeight) {
        this.tabHeight = tabHeight;
    }

    public String getTabPosition() {
        return this.tabPosition;
    }

    public void setTabPosition(String tabPosition) {
        this.tabPosition = tabPosition;
    }

    public String getUnselectedCloseVisible() {
        return this.unselectedCloseVisible;
    }

    public void setUnselectedCloseVisible(String unselectedCloseVisible) {
        this.unselectedCloseVisible = unselectedCloseVisible;
    }

    public String getUnselectedImageVisible() {
        return this.unselectedImageVisible;
    }

    public void setUnselectedImageVisible(String unselectedImageVisible) {
        this.unselectedImageVisible = unselectedImageVisible;
    }

    public GradientInfo getSelectionBackgroundGradient() {
        return this.selectionBackgroundGradient;
    }

    public void setSelectionBackgroundGradient(
            GradientInfo selectionBackgroundGradient) {
        this.selectionBackgroundGradient = selectionBackgroundGradient;
    }

}
