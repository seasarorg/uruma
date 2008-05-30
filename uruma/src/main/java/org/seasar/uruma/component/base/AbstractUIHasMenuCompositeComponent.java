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

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.UIHasMenuCompositeComponent;
import org.seasar.uruma.component.jface.CompositeComponent;
import org.seasar.uruma.component.jface.MenuComponent;

/**
 * {@link AbstractUIHasMenuCompositeComponent} を表す基底クラスです。<br />
 * RCPに定義するメニューを利用するクラスの基底クラスとして使用します。<br />
 * 
 * @author y.sugigami
 */
public abstract class AbstractUIHasMenuCompositeComponent extends
        CompositeComponent implements UIHasMenuCompositeComponent {
    /*
     * @see org.seasar.uruma.component.UIHasMenuCompositeComponent#getMenus()
     */
    public List<MenuComponent> getMenus() {
        return getElements(MenuComponent.class);
    }

    protected <E> List<E> getElements(final Class<E> clazz) {
        List<E> result = new ArrayList<E>(getChildren().size());
        for (UIElement element : getChildren()) {
            if (clazz.isAssignableFrom(element.getClass())) {
                result.add(clazz.cast(element));
            }
        }
        return result;
    }
}
