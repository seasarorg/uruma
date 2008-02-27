/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import org.eclipse.swt.layout.RowLayout;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * {@link RowLayout} に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class RowLayoutInfo extends AbstractUIElement implements
        LayoutInfo<RowDataInfo> {
    private RowDataInfo commonRowDataInfo;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("fill 属性")
    private String fill;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("justify 属性")
    private String justify;

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

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("pack 属性")
    private String pack;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @FieldDescription("spacing 属性")
    private String spacing;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @FieldDescription("conversionType 属性")
    private String conversionType;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @FieldDescription("type 属性")
    private String type;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @FieldDescription("wrap 属性")
    private String wrap;

    /**
     * commonRowDataInfo を取得します。<br />
     * 
     * @return commonRowDataInfo
     */
    public RowDataInfo getCommonRowDataInfo() {
        return this.commonRowDataInfo;
    }

    /**
     * commonRowDataInfo を設定します。<br />
     * 
     * @param commonRowDataInfo
     *            <code>commonRowDataInfo</code> オブジェクト
     */
    public void setCommonRowDataInfo(final RowDataInfo commonRowDataInfo) {
        this.commonRowDataInfo = commonRowDataInfo;
    }

    /**
     * fill 属性を取得します。<br />
     * 
     * @return fill 属性
     */
    public String getFill() {
        return this.fill;
    }

    /**
     * fill 属性を設定します。<br />
     * 
     * @param fill
     *            fill 属性
     */
    public void setFill(final String fill) {
        this.fill = fill;
    }

    /**
     * justify 属性を取得します。<br />
     * 
     * @return justify 属性
     */
    public String getJustify() {
        return this.justify;
    }

    /**
     * justify 属性を設定します。<br />
     * 
     * @param justify
     *            justify 属性
     */
    public void setJustify(final String justify) {
        this.justify = justify;
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
     * pack 属性を取得します。<br />
     * 
     * @return pack 属性
     */
    public String getPack() {
        return this.pack;
    }

    /**
     * pack 属性を設定します。<br />
     * 
     * @param pack
     *            pack 属性
     */
    public void setPack(final String pack) {
        this.pack = pack;
    }

    /**
     * spacing 属性を取得します。<br />
     * 
     * @return spacing 属性
     */
    public String getSpacing() {
        return this.spacing;
    }

    /**
     * spacing 属性を設定します。<br />
     * 
     * @param spacing
     *            spacing 属性
     */
    public void setSpacing(final String spacing) {
        this.spacing = spacing;
    }

    /**
     * conversionType 属性を取得します。<br />
     * 
     * @return conversionType 属性
     */
    public String getConversionType() {
        return this.conversionType;
    }

    /**
     * conversionType 属性を設定します。<br />
     * 
     * @param conversionType
     *            conversionType 属性
     */
    public void setConversionType(final String conversionType) {
        this.conversionType = conversionType;
    }

    /**
     * type 属性を取得します。<br />
     * 
     * @return type 属性
     */
    public String getType() {
        return this.type;
    }

    /**
     * type 属性を設定します。<br />
     * 
     * @param type
     *            type 属性
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * wrap 属性を取得します。<br />
     * 
     * @return wrap 属性
     */
    public String getWrap() {
        return this.wrap;
    }

    /**
     * wrap 属性を設定します。<br />
     * 
     * @param wrap
     *            wrap 属性
     */
    public void setWrap(final String wrap) {
        this.wrap = wrap;
    }

    /*
     * @see org.seasar.jface.component.LayoutInfo#getCommonLayoutDataInfo()
     */
    public RowDataInfo getCommonLayoutDataInfo() {
        return commonRowDataInfo;
    }

    /*
     * @see org.seasar.jface.component.LayoutInfo#setCommonLayoutDataInfo(org.seasar.jface.component.LayoutDataInfo)
     */
    public void setCommonLayoutDataInfo(final RowDataInfo layoutDataInfo) {
        this.commonRowDataInfo = layoutDataInfo;
    }
}
