/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.uruma.renderer.layout;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.jface.FillLayoutInfo;
import org.seasar.uruma.component.jface.GridLayoutInfo;
import org.seasar.uruma.component.jface.RowLayoutInfo;
import org.seasar.uruma.renderer.layout.impl.GenericLayoutSupport;

/**
 * {@link LayoutSupport} の実装クラスを取得するためのファクトリです。</br>
 * 
 * @author y-komori
 */
public class LayoutSupportFactory {
    private static final Map<Class<? extends LayoutInfo<?>>, LayoutSupport> LAYOUT_SUPPORT_MAP = new HashMap<Class<? extends LayoutInfo<?>>, LayoutSupport>();

    static {
        addLayoutSupport(FillLayoutInfo.class, new GenericLayoutSupport(
                FillLayout.class, null));
        addLayoutSupport(RowLayoutInfo.class, new GenericLayoutSupport(
                RowLayout.class, RowData.class));
        addLayoutSupport(GridLayoutInfo.class, new GenericLayoutSupport(
                GridLayout.class, GridData.class));
    }

    /**
     * {@link LayoutInfo} クラスをキーにして、レイアウトサポートクラスを取得します。<br />
     * 
     * @param layoutInfo
     *            {@link LayoutInfo} クラス。
     * @return レイアウトサポートクラス。見つからない場合は <code>null</code>
     */
    public static LayoutSupport getLayoutSupport(
            final Class<? extends LayoutInfo<?>> layoutInfo) {
        return LAYOUT_SUPPORT_MAP.get(layoutInfo);
    }

    /**
     * レイアウトサポートクラスを追加します</br>
     * 
     * @param layoutSupport
     *            レイアウトサポートクラス
     */
    public static void addLayoutSupport(
            final Class<? extends LayoutInfo<?>> layoutInfo,
            final LayoutSupport layoutSupport) {
        LAYOUT_SUPPORT_MAP.put(layoutInfo, layoutSupport);
    }
}
