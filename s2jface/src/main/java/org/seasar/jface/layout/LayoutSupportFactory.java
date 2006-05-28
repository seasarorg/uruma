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
package org.seasar.jface.layout;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Layout;
import org.seasar.jface.exception.NotFoundException;

/**
 * レイアウトサポートクラスを取得するためのファクトリです。</br>
 * 
 * @author y-komori
 * @see org.seasar.jface.layout.LayoutSupport
 */
public class LayoutSupportFactory {
    private static final Map<String, LayoutSupport> LAYOUT_SUPPORT_MAP_BY_NAME = new HashMap<String, LayoutSupport>();

    private static final Map<Class<? extends Layout>, LayoutSupport> LAYOUT_SUPPORT_MAP_BY_TYPE = new HashMap<Class<? extends Layout>, LayoutSupport>();

    static {
        addLayoutSupport(new FillLayoutSupport());
        addLayoutSupport(new RowLayoutSupport());
        addLayoutSupport(new GridLayoutSupport());
    }

    /**
     * レイアウト名をキーにしてレイアウトサポートクラスを取得します。</br>
     * 
     * @param layoutName
     *            レイアウト名
     * @return レイアウトサポートクラス
     * @throws NotFoundException
     *             指定されたレイアウトサポートクラスが見つからない場合
     */
    public static LayoutSupport getLayoutSupport(String layoutName) {
        LayoutSupport layoutSupport = LAYOUT_SUPPORT_MAP_BY_NAME
                .get(layoutName);
        if (layoutSupport != null) {
            return layoutSupport;
        } else {
            throw new NotFoundException(NotFoundException.LAYOUT, layoutName);
        }
    }

    /**
     * レイアウトクラスをキーにして、レイアウトサポートクラスを取得します</br>
     * 
     * @param layoutType
     *            レイアウトのクラスオブジェクト
     * @return レイアウトサポートクラス。見つからない場合は<code>null</code>
     */
    public static LayoutSupport getLayoutSupport(
            Class<? extends Layout> layoutType) {
        LayoutSupport layoutSupport = LAYOUT_SUPPORT_MAP_BY_TYPE
                .get(layoutType);
        if (layoutSupport != null) {
            return layoutSupport;
        } else {
            return null;
        }
    }

    /**
     * レイアウトサポートクラスを追加します</br>
     * 
     * @param layoutSupport
     *            レイアウトサポートクラス
     */
    public static void addLayoutSupport(
            LayoutSupport<? extends Layout, ?> layoutSupport) {
        LAYOUT_SUPPORT_MAP_BY_NAME.put(layoutSupport.getLayoutName(),
                layoutSupport);
        LAYOUT_SUPPORT_MAP_BY_TYPE.put(layoutSupport.getLayoutType(),
                layoutSupport);
    }
}