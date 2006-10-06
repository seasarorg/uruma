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
public class TableComponent extends CompositeComponent {

    @ComponentAttribute(conversionType = ConversionType.INT_ARRAY, setTiming = SetTiming.RENDER_AFTER)
    private String columnOrder;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String headerVisible;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private String linesVisible;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String selection;

    @ComponentAttribute(conversionType = ConversionType.INT, setTiming = SetTiming.RENDER_AFTER)
    private String topIndex;

    public String getColumnOrder() {
        return this.columnOrder;
    }

    public void setColumnOrder(String columnOrder) {
        this.columnOrder = columnOrder;
    }

    public String getHeaderVisible() {
        return this.headerVisible;
    }

    public void setHeaderVisible(String headerVisible) {
        this.headerVisible = headerVisible;
    }

    public String getLinesVisible() {
        return this.linesVisible;
    }

    public void setLinesVisible(String linesVisible) {
        this.linesVisible = linesVisible;
    }

    public String getSelection() {
        return this.selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getTopIndex() {
        return this.topIndex;
    }

    public void setTopIndex(String topIndex) {
        this.topIndex = topIndex;
    }
}
