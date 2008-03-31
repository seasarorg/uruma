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

import org.eclipse.swt.layout.GridData;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.LayoutDataInfo;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * {@link GridData} に関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
@ComponentElement
public class GridDataInfo extends AbstractUIElement implements LayoutDataInfo {
    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("exclude 属性")
    public String exclude;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("grabExcessHorizontalSpace 属性")
    public String grabExcessHorizontalSpace;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("grabExcessVerticalSpace 属性")
    public String grabExcessVerticalSpace;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("heightHint 属性")
    public String heightHint;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @ComponentAttribute
    @FieldDescription("horizontalAlignment 属性")
    public String horizontalAlignment;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("horizontalIndent 属性")
    public String horizontalIndent;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("horizontalSpan 属性")
    public String horizontalSpan;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("minimumHeight 属性")
    public String minimumHeight;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("minimumWidth 属性")
    public String minimumWidth;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.SWT_CONST)
    @ComponentAttribute
    @FieldDescription("verticalAlignment 属性")
    public String verticalAlignment;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("verticalIndent 属性")
    public String verticalIndent;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("verticalSpan 属性")
    public String verticalSpan;

    @RenderingPolicy(targetType = TargetType.FIELD, conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("widthHint 属性")
    public String widthHint;
}
