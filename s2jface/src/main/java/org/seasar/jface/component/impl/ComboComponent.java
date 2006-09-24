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
 * 
 */
public class ComboComponent extends SimpleItemCompositeComponent {

    @ComponentAttribute(conversionType = ConversionType.SWT_CONST)
    private String orientation;

    @ComponentAttribute(conversionType = ConversionType.STRING)
    private String text;

    @ComponentAttribute(conversionType = ConversionType.INT)
    private String textLimit;

    @ComponentAttribute(conversionType = ConversionType.INT)
    private String visibleItemCount;

    public String getOrientation() {
        return this.orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
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

    public String getVisibleItemCount() {
        return this.visibleItemCount;
    }

    public void setVisibleItemCount(String visibleItemCount) {
        this.visibleItemCount = visibleItemCount;
    }

}
