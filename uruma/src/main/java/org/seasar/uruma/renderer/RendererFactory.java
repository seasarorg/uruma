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
package org.seasar.uruma.renderer;

import java.util.HashMap;
import java.util.Map;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.util.AssertionUtil;

/**
 * レンダラを取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class RendererFactory {
    private static final Map<Class<? extends UIComponent>, Renderer> rendererMap = new HashMap<Class<? extends UIComponent>, Renderer>();

    /**
     * {@link UIComponent} クラスをキーにして、レンダラを取得します。</br>
     * 
     * @param uiComponentClass
     *            レンダラに対応する {@link UIComponent} の {@link Class} オブジェクト
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
            throw new NotFoundException(UrumaMessageCodes.RENDERER_NOT_FOUND,
                    uiComponentClass.getName());
        }
    }

    /**
     * {@link UIComponent} クラスをキーとして、レンダラを登録します。</br>
     * 
     * @param uiComponentClass
     *            {@link UIComponent} クラスの {@link Class} オブジェクト
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
