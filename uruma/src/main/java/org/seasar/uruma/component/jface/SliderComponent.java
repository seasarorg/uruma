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

import org.eclipse.swt.widgets.Slider;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * {@link Slider} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
@ComponentElement
public class SliderComponent extends ControlComponent {
    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("最大値")
    public String maximum;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("最小値")
    public String minimum;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("現在値")
    public String selection;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("増分値")
    public String increment;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("ページ増分値")
    public String pageIncrement;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("サムのサイズ")
    public String thumb;
}
