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

import org.eclipse.swt.layout.GridLayout;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * {@link GridLayout} に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
@ComponentElement
public class GridLayoutInfo extends AbstractUIElement implements
        LayoutInfo<GridDataInfo> {
    private GridDataInfo commonGridDataInfo;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("horizontalSpacing 属性")
    public String horizontalSpacing;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("makeColumnsEqualWidth 属性")
    public String makeColumnsEqualWidth;

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

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("numColumns 属性")
    public String numColumns;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("verticalSpacing 属性")
    public String verticalSpacing;

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
