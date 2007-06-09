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

/**
 * @author bskuroneko
 * 
 */
public class ProgressBarComponent extends ControlComponent {
    @ComponentAttribute(conversionType = ConversionType.INT)
    private String maximum;
    
    @ComponentAttribute(conversionType = ConversionType.INT)
    private String minimum;
    
    @ComponentAttribute(conversionType = ConversionType.INT)
    private String selection;

    public String getMaximum() {
        return this.maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return this.minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getSelection() {
        return this.selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
    
}
