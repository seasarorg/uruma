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
import org.seasar.jface.annotation.component.ComponentAttribute.SetTiming;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;

/**
 * @author bskuroneko
 */
public class TextComponent extends ControlComponent {
    
    @ComponentAttribute(conversionType = ConversionType.TEXT)
    private String text;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String doubleClickEnabled;

    @ComponentAttribute(conversionType = ConversionType.CHAR)
    private String echoChar;
    
    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String editable;

    @ComponentAttribute(conversionType = ConversionType.SWT_CONST)
    private String orientation;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String selectionStart;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String selectionEnd;

    @ComponentAttribute(conversionType = ConversionType.INT)
    private String tabs;

    @ComponentAttribute(conversionType = ConversionType.INT)
    private String textLimit;

    @ComponentAttribute(conversionType = ConversionType.INT, setTiming = SetTiming.RENDER_AFTER)
    private String topIndex;

    public String getDoubleClickEnabled() {
        return this.doubleClickEnabled;
    }

    public void setDoubleClickEnabled(String doubleClickEnabled) {
        this.doubleClickEnabled = doubleClickEnabled;
    }

    public String getEchoChar() {
        return this.echoChar;
    }

    public void setEchoChar(String echoChar) {
        this.echoChar = echoChar;
    }

    public String getEditable() {
        return this.editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getSelectionEnd() {
        return this.selectionEnd;
    }

    public void setSelectionEnd(String selectionEnd) {
        this.selectionEnd = selectionEnd;
    }

    public String getSelectionStart() {
        return this.selectionStart;
    }

    public void setSelectionStart(String selectionStart) {
        this.selectionStart = selectionStart;
    }

    public String getTabs() {
        return this.tabs;
    }

    public void setTabs(String tabs) {
        this.tabs = tabs;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextLimit() {
        return this.textLimit;
    }

    public void setTextLimit(String textLimit) {
        this.textLimit = textLimit;
    }

    public String getTopIndex() {
        return this.topIndex;
    }

    public void setTopIndex(String topIndex) {
        this.topIndex = topIndex;
    }

    
}
