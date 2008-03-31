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

import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * 単一項目を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class SimpleItemComponent extends AbstractUIElement {
    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("テキスト")
    public String text;

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.text;
    }
}
