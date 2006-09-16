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
import org.seasar.jface.component.LayoutInfo;

/**
 * {@link org.eclipse.swt.layout.FillLayout} に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class FillLayoutInfo extends AbstractUIElement implements LayoutInfo {
    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String marginHeight;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String marginWidth;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String spacing;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    private String conversionType;

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

    public String getSpacing() {
        return this.spacing;
    }

    public void setSpacing(String spacing) {
        this.spacing = spacing;
    }

    public String getType() {
        return this.conversionType;
    }

    public void setType(String type) {
        this.conversionType = type;
    }

    public LayoutDataInfo getCommonLayoutDataInfo() {
        return null;
    }

    public void setCommonLayoutDataInfo(LayoutDataInfo layoutDataInfo) {
        // Do nothing.
    }
}
