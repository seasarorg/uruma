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

import org.seasar.jface.exception.NotFoundException;
import org.seasar.jface.util.AssertionUtil;

/**
 * レンダラ名称をキーにしてレンダラを取得するためのファクトリクラスです。</br>
 * 
 * @author y-komori
 * 
 */
public class RendererFactrory {
    private static final Map<String, Renderer> rendererMap = new HashMap<String, Renderer>();

    static {
        addRenderer(new ButtonRenderer());
        addRenderer(new LabelRenderer());
        addRenderer(new TextRenderer());
        addRenderer(new TextAreaRenderer());
        addRenderer(new BoxRenderer());
        addRenderer(new WindowRenderer());
        addRenderer(new ListRenderer());
        addRenderer(new GroupRenderer());
        addRenderer(new TreeRenderer());
        addRenderer(new TableRenderer());
    }

    /**
     * レンダラ名称をキーにして、レンダラを取得します。</br>
     * 
     * @param type
     *            レンダラ名称
     * @return レンダラオブジェクト
     * @throws NotFoundException
     *             レンダラが見つからなかった場合
     */
    public static Renderer getRenderer(final String type) {
        Renderer renderer = rendererMap.get(type);
        if (renderer != null) {
            return renderer;
        } else {
            throw new NotFoundException(NotFoundException.RENDERER, type);
        }
    }

    /**
     * レンダラを登録します。</br>
     * 
     * @param renderer
     *            レンダラオブジェクト
     */
    public static void addRenderer(final Renderer renderer) {
        AssertionUtil.assertNotNull("renderer", renderer);
        rendererMap.put(renderer.getRendererName(), renderer);
    }
}
