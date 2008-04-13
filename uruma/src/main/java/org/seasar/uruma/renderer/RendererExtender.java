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

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.util.AssertionUtil;

/**
 * 拡張レンダラを管理するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class RendererExtender {
    private static List<RendererExtension> extensions = new ArrayList<RendererExtension>();

    /**
     * 拡張レンダラを登録します。<br />
     * Uruma のレンダリング処理を拡張したい場合、 {@link RendererExtension}
     * インターフェースの実装クラスを作成し、本メソッド使って登録してください。<br />
     * 
     * @param extension
     *            拡張レンダラオブジェクト
     */
    public static void addRendererExtension(final RendererExtension extension) {
        AssertionUtil.assertNotNull("extension", extension);

        extensions.add(extension);
    }

    /**
     * 登録された拡張レンダラをすべてクリアします。<br />
     */
    public static void clearRendererExtensions() {
        extensions.clear();
    }

    /**
     * 登録された拡張レンダラを順番に呼び出し、拡張レンダリングを実施します。<br />
     * 拡張レンダラは {@link #addRendererExtension(RendererExtension)}
     * メソッドで登録された順番に呼び出されます。<br />
     * (本メソッドは Uruma のレンダリングエンジンから呼び出されるメソッドです)
     * 
     * @param uiComponent
     *            レンダリング対象の情報を持つ {@link UIComponent} オブジェクト
     * @param handle
     *            レンダリング対象のウィジットを保持する {@link WidgetHandle} オブジェクト
     * @param context
     *            画面情報を収めた {@link PartContext} オブジェクト
     * @see RendererExtension#extRender(UIComponent, WidgetHandle, PartContext)
     */
    public static void doExtRender(final UIComponent uiComponent,
            final WidgetHandle handle, final PartContext context) {
        for (RendererExtension extension : extensions) {
            extension.extRender(uiComponent, handle, context);
        }
    }
}
