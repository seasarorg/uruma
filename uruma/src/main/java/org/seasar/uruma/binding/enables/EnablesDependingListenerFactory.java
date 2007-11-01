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
package org.seasar.uruma.binding.enables;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.seasar.uruma.binding.enables.impl.ViewerEnablesDependingListener;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.exception.EnablesDependingException;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.ClassUtil;

/**
 * {@link EnablesDependingListener} を取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class EnablesDependingListenerFactory {

    private static final Map<Class<?>, Class<? extends EnablesDependingListener>> listenerMap = new HashMap<Class<?>, Class<? extends EnablesDependingListener>>();

    static {
        addListener(TableViewer.class, ViewerEnablesDependingListener.class);
        addListener(TreeViewer.class, ViewerEnablesDependingListener.class);
        addListener(ComboViewer.class, ViewerEnablesDependingListener.class);
    }

    private EnablesDependingListenerFactory() {

    }

    /**
     * <code>target</code> のもつウィジットに対応する {@link EnablesDependingListener}
     * のインスタンスを取得します。<br />
     * 
     * @param target
     *            ターゲットウィジットをもつ {@link WidgetHandle}
     * @param enabled
     *            イネーブル状態を変化させるウィジットをもつ {@link WidgetHandle}
     * @param type
     *            選択状態を表す {@link EnablesForType}
     * @return {@link EnablesDependingListener} のインスタンス
     */
    public static EnablesDependingListener getListener(
            final WidgetHandle target, final WidgetHandle enabled,
            final EnablesForType type) {
        Class<? extends EnablesDependingListener> listenerType = listenerMap
                .get(target.getWidgetClass());

        if (listenerType != null) {
            return ClassUtil.newInstance(listenerType, target, enabled, type);
        } else {
            throw new EnablesDependingException(target.getWidgetClass());
        }
    }

    /**
     * <code>clazz</code> に対応する {@link EnablesDependingListener}
     * の型マッピングを追加します。<br />
     * 
     * @param clazz
     *            ターゲットの {@link Class} オブジェクト
     * @param listenerType
     *            {@link EnablesDependingListener} の型
     */
    public static void addListener(final Class<?> clazz,
            final Class<? extends EnablesDependingListener> listenerType) {
        AssertionUtil.assertNotNull("clazz", clazz);
        AssertionUtil.assertNotNull("listenerType", listenerType);

        listenerMap.put(clazz, listenerType);
    }
}
