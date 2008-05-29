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
package org.seasar.uruma.component.rcp;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.part.ViewPart;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.CompositeComponent;
import org.seasar.uruma.component.jface.MenuComponent;
import org.seasar.uruma.rcp.ui.GenericViewPart;

/**
 * {@link ViewPart} のコンポーネント情報を保持するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ViewPartComponent extends CompositeComponent {
    /**
     * ビュータイトルです。<br />
     */
    public String title;

    /**
     * {@link ViewPart} クラスの名称です。<br />
     */
    public String clazz = GenericViewPart.class.getName();

    /**
     * カテゴリ名称です。<br />
     */
    public String category;

    /**
     * イメージを指定するパスです。<br />
     */
    public String image;

    /**
     * 複数のオープンを許可するかどうかのフラグです。<br />
     */
    public String allowMultiple;

    /**
     * {@link ViewPartComponent} が保持する {@link ViewPartComponent} のリストを返します。<br
     * />
     * 
     * @return {@link MenuComponent} のリスト
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
