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
package org.seasar.uruma.component.impl;

import org.eclipse.swt.layout.GridLayout;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.LayoutInfo;

/**
 * {@link GridLayout} に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class GridLayoutInfo extends AbstractUIElement implements
        LayoutInfo<GridDataInfo> {
    private GridDataInfo commonGridDataInfo;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("horizontalSpacing 属性")
    private String horizontalSpacing;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("makeColumnsEqualWidth 属性")
    private String makeColumnsEqualWidth;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("marginBottom 属性")
    private String marginBottom;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("marginHeight 属性")
    private String marginHeight;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("marginLeft 属性")
    private String marginLeft;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("marginRight 属性")
    private String marginRight;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("marginTop 属性")
    private String marginTop;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("marginWidth 属性")
    private String marginWidth;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("numColumns 属性")
    private String numColumns;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("verticalSpacing 属性")
    private String verticalSpacing;

    /**
     * horizontalSpacing 属性を取得します。<br />
     * 
     * @return horizontalSpacing 属性
     */
    public String getHorizontalSpacing() {
        return this.horizontalSpacing;
    }

    /**
     * horizontalSpacing 属性を設定します。<br />
     * 
     * @param horizontalSpacing
     *            horizontalSpacing 属性
     */
    public void setHorizontalSpacing(final String horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    /**
     * makeColumnsEqualWidth 属性を取得します。<br />
     * 
     * @return makeColumnsEqualWidth 属性
     */
    public String getMakeColumnsEqualWidth() {
        return this.makeColumnsEqualWidth;
    }

    /**
     * makeColumnsEqualWidth 属性を設定します。<br />
     * 
     * @param makeColumnsEqualWidth
     *            makeColumnsEqualWidth 属性
     */
    public void setMakeColumnsEqualWidth(final String makeColumnsEqualWidth) {
        this.makeColumnsEqualWidth = makeColumnsEqualWidth;
    }

    /**
     * marginBottom 属性を取得します。<br />
     * 
     * @return marginBottom 属性
     */
    public String getMarginBottom() {
        return this.marginBottom;
    }

    /**
     * marginBottom 属性を設定します。<br />
     * 
     * @param marginBottom
     *            marginBottom 属性
     */
    public void setMarginBottom(final String marginBottom) {
        this.marginBottom = marginBottom;
    }

    /**
     * marginHeight 属性を取得します。<br />
     * 
     * @return marginHeight 属性
     */
    public String getMarginHeight() {
        return this.marginHeight;
    }

    /**
     * marginHeight 属性を設定します。<br />
     * 
     * @param marginHeight
     *            marginHeight 属性
     */
    public void setMarginHeight(final String marginHeight) {
        this.marginHeight = marginHeight;
    }

    /**
     * marginLeft 属性を取得します。<br />
     * 
     * @return marginLeft 属性
     */
    public String getMarginLeft() {
        return this.marginLeft;
    }

    /**
     * marginLeft 属性を設定します。<br />
     * 
     * @param marginLeft
     *            marginLeft 属性
     */
    public void setMarginLeft(final String marginLeft) {
        this.marginLeft = marginLeft;
    }

    /**
     * marginRight 属性を取得します。<br />
     * 
     * @return marginRight 属性
     */
    public String getMarginRight() {
        return this.marginRight;
    }

    /**
     * marginRight 属性を設定します。<br />
     * 
     * @param marginRight
     *            marginRight 属性
     */
    public void setMarginRight(final String marginRight) {
        this.marginRight = marginRight;
    }

    /**
     * marginTop 属性を取得します。<br />
     * 
     * @return marginTop 属性
     */
    public String getMarginTop() {
        return this.marginTop;
    }

    /**
     * marginTop 属性を設定します。<br />
     * 
     * @param marginTop
     *            marginTop 属性
     */
    public void setMarginTop(final String marginTop) {
        this.marginTop = marginTop;
    }

    /**
     * marginWidth 属性を取得します。<br />
     * 
     * @return marginWidth 属性
     */
    public String getMarginWidth() {
        return this.marginWidth;
    }

    /**
     * marginWidth 属性を設定します。<br />
     * 
     * @param marginWidth
     *            marginWidth 属性
     */
    public void setMarginWidth(final String marginWidth) {
        this.marginWidth = marginWidth;
    }

    /**
     * numColumns 属性を取得します。<br />
     * 
     * @return numColumns 属性
     */
    public String getNumColumns() {
        return this.numColumns;
    }

    /**
     * numColumns 属性を設定します。<br />
     * 
     * @param numColumns
     *            numColumns 属性
     */
    public void setNumColumns(final String numColumns) {
        this.numColumns = numColumns;
    }

    /**
     * verticalSpacing 属性を取得します。<br />
     * 
     * @return verticalSpacing 属性
     */
    public String getVerticalSpacing() {
        return this.verticalSpacing;
    }

    /**
     * verticalSpacing 属性を設定します。<br />
     * 
     * @param verticalSpacing
     *            verticalSpacing 属性
     */
    public void setVerticalSpacing(final String verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    /*
     * @see org.seasar.uruma.component.LayoutInfo#getCommonLayoutDataInfo()
     */
    public GridDataInfo getCommonLayoutDataInfo() {
        return commonGridDataInfo;
    }

    /*
     * @see org.seasar.uruma.component.LayoutInfo#setCommonLayoutDataInfo(org.seasar.uruma.component.LayoutDataInfo)
     */
    public void setCommonLayoutDataInfo(final GridDataInfo layoutDataInfo) {
        this.commonGridDataInfo = layoutDataInfo;
    }
}
