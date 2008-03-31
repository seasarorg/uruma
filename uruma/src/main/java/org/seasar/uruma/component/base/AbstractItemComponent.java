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
package org.seasar.uruma.component.base;

import org.eclipse.swt.widgets.Item;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * {@link Item} を表す基底クラスです。<br />
 * 
 * @author bskuroneko
 */
public abstract class AbstractItemComponent extends AbstractUIComponent {

    @RenderingPolicy(conversionType = ConversionType.IMAGE)
    @ComponentAttribute
    @FieldDescription("イメージパス")
    public String image;

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @ComponentAttribute
    @FieldDescription("テキスト")
    public String text;
}
