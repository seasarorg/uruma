/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.renderer;

import java.util.HashMap;
import java.util.Map;

import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.ButtonComponent;
import org.seasar.jface.component.impl.ComboComponent;
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.component.impl.LabelComponent;
import org.seasar.jface.component.impl.MenuComponent;
import org.seasar.jface.component.impl.MenuItemComponent;
import org.seasar.jface.component.impl.TabFolderComponent;
import org.seasar.jface.component.impl.TabItemComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.exception.NotFoundException;
import org.seasar.jface.renderer.impl.ButtonRenderer;
import org.seasar.jface.renderer.impl.ComboRenderer;
import org.seasar.jface.renderer.impl.CompositeRenderer;
import org.seasar.jface.renderer.impl.LabelRenderer;
import org.seasar.jface.renderer.impl.MenuItemRenderer;
import org.seasar.jface.renderer.impl.MenuRenderer;
import org.seasar.jface.renderer.impl.TabFolderRenderer;
import org.seasar.jface.renderer.impl.TabItemRenderer;
import org.seasar.jface.renderer.impl.WindowRenderer;
import org.seasar.jface.util.AssertionUtil;

/**
 * レンダラを取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class RendererFactrory {
    private static final Map<Class<? extends UIComponent>, Renderer> rendererMap = new HashMap<Class<? extends UIComponent>, Renderer>();

    static {
        addRenderer(WindowComponent.class, new WindowRenderer());
        addRenderer(CompositeComponent.class, new CompositeRenderer());
        addRenderer(ComboComponent.class, new ComboRenderer());
        addRenderer(TabFolderComponent.class, new TabFolderRenderer());
        addRenderer(TabItemComponent.class, new TabItemRenderer());

        addRenderer(ButtonComponent.class, new ButtonRenderer());
        addRenderer(LabelComponent.class, new LabelRenderer());

        addRenderer(MenuComponent.class, new MenuRenderer());
        addRenderer(MenuItemComponent.class, new MenuItemRenderer());
    }

    /**
     * <code>UIComponent</code> クラスをキーにして、レンダラを取得します。</br>
     * 
     * @param type
     *            レンダラ名称
     * @return レンダラオブジェクト
     * @throws NotFoundException
     *             レンダラが見つからなかった場合
     */
    public static Renderer getRenderer(
            final Class<? extends UIComponent> uiComponentClass) {
        Renderer renderer = rendererMap.get(uiComponentClass);
        if (renderer != null) {
            return renderer;
        } else {
            throw new NotFoundException(NotFoundException.RENDERER,
                    uiComponentClass.getName());
        }
    }

    /**
     * <code>UIComponent</code> クラスをキーとして、レンダラを登録します。</br>
     * 
     * @param uiComponentClass
     *            <code>UIComponent</code> クラス
     * @param renderer
     *            レンダラオブジェクト
     */
    public static void addRenderer(
            final Class<? extends UIComponent> uiComponentClass,
            final Renderer renderer) {
        AssertionUtil.assertNotNull("uiComponentClass", uiComponentClass);
        AssertionUtil.assertNotNull("renderer", renderer);
        rendererMap.put(uiComponentClass, renderer);
    }
}
