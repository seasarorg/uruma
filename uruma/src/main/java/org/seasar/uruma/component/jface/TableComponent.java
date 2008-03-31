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

import org.eclipse.swt.widgets.Table;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link Table} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
@ComponentElement
public class TableComponent extends CompositeComponent {

    @RenderingPolicy(conversionType = ConversionType.INT_ARRAY, setTiming = SetTiming.RENDER_AFTER)
    @ComponentAttribute
    @FieldDescription("カラム順序")
    public String columnOrder;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("ヘッダ表示状態")
    public String headerVisible = "true";

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("罫線表示状態")
    public String linesVisible = "true";

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("選択状態")
    public String selection;

    @RenderingPolicy(conversionType = ConversionType.INT, setTiming = SetTiming.RENDER_AFTER)
    @ComponentAttribute
    @FieldDescription("最上位表示項目")
    public String topIndex;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("カラム数")
    public int columnCount;
}
