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

import org.eclipse.swt.widgets.TableColumn;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.base.AbstractItemComponent;

/**
 * {@link TableColumn} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
@ComponentElement
public class TableColumnComponent extends AbstractItemComponent {

    @RenderingPolicy(conversionType = ConversionType.SWT_CONST)
    @ComponentAttribute
    @FieldDescription("アライメント")
    public String alignment;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("移動可不可状態")
    public String moveable;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("サイズ変更可不可状態")
    public String resizable;

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @ComponentAttribute
    @FieldDescription("ツールチップテキスト")
    public String toolTipText;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("幅")
    public String width;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("カラム位置")
    public int columnNo;
}
