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
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.component.LayoutInfo;

/**
 * @author y-komori
 * 
 */
public class GridLayoutInfo extends AbstractUIElement implements
        LayoutInfo<GridDataInfo> {
    private GridDataInfo commonGridDataInfo;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String horizontalSpacing;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    private String makeColumnsEqualWidth;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String marginBottom;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String marginHeight;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String marginLeft;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String marginRight;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String marginTop;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String marginWidth;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String numColumns;

    @ComponentAttribute(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    private String verticalSpacing;

    public String getHorizontalSpacing() {
        return this.horizontalSpacing;
    }

    public void setHorizontalSpacing(String horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public String getMakeColumnsEqualWidth() {
        return this.makeColumnsEqualWidth;
    }

    public void setMakeColumnsEqualWidth(String makeColumnsEqualWidth) {
        this.makeColumnsEqualWidth = makeColumnsEqualWidth;
    }

    public String getMarginBottom() {
        return this.marginBottom;
    }

    public void setMarginBottom(String marginBottom) {
        this.marginBottom = marginBottom;
    }

    public String getMarginHeight() {
        return this.marginHeight;
    }

    public void setMarginHeight(String marginHeight) {
        this.marginHeight = marginHeight;
    }

    public String getMarginLeft() {
        return this.marginLeft;
    }

    public void setMarginLeft(String marginLeft) {
        this.marginLeft = marginLeft;
    }

    public String getMarginRight() {
        return this.marginRight;
    }

    public void setMarginRight(String marginRight) {
        this.marginRight = marginRight;
    }

    public String getMarginTop() {
        return this.marginTop;
    }

    public void setMarginTop(String marginTop) {
        this.marginTop = marginTop;
    }

    public String getMarginWidth() {
        return this.marginWidth;
    }

    public void setMarginWidth(String marginWidth) {
        this.marginWidth = marginWidth;
    }

    public String getNumColumns() {
        return this.numColumns;
    }

    public void setNumColumns(String numColumns) {
        this.numColumns = numColumns;
    }

    public String getVerticalSpacing() {
        return this.verticalSpacing;
    }

    public void setVerticalSpacing(String verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    public GridDataInfo getCommonLayoutDataInfo() {
        return commonGridDataInfo;
    }

    public void setCommonLayoutDataInfo(GridDataInfo layoutDataInfo) {
        this.commonGridDataInfo = layoutDataInfo;
    }
}
