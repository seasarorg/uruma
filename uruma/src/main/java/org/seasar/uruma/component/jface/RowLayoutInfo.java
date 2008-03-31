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
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
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
@ComponentElement
public class RowLayoutInfo extends AbstractUIElement implements
        LayoutInfo<RowDataInfo> {
    private RowDataInfo commonRowDataInfo;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("fill 属性")
    public String fill;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("justify 属性")
    public String justify;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("marginBottom 属性")
    public String marginBottom;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("marginHeight 属性")
    public String marginHeight;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("marginLeft 属性")
    public String marginLeft;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("marginRight 属性")
    public String marginRight;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("marginTop 属性")
    public String marginTop;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("marginWidth 属性")
    public String marginWidth;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("pack 属性")
    public String pack;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("spacing 属性")
    public String spacing;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @ComponentAttribute
    @FieldDescription("conversionType 属性")
    public String conversionType;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @ComponentAttribute
    @FieldDescription("type 属性")
    public String type;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("wrap 属性")
    public String wrap;

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
