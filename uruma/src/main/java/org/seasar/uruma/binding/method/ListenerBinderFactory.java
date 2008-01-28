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
package org.seasar.uruma.binding.method;

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.binding.method.impl.GenericActionListenerBinder;
import org.seasar.uruma.binding.method.impl.UrumaApplicationWindowListenerBinder;
import org.seasar.uruma.binding.method.impl.UrumaTreeViewerListenerBinder;
import org.seasar.uruma.binding.method.impl.ViewerListenerBinder;
import org.seasar.uruma.binding.method.impl.WidgetListenerBinder;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link ListenerBinder} のためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ListenerBinderFactory {
    private static ListenerBinder[] binders;

    static {
        List<ListenerBinder> binderList = new ArrayList<ListenerBinder>();

        binderList.add(new UrumaTreeViewerListenerBinder());
        binderList.add(new ViewerListenerBinder());
        binderList.add(new UrumaApplicationWindowListenerBinder());
        binderList.add(new WidgetListenerBinder());
        binderList.add(new GenericActionListenerBinder());

        binders = binderList.toArray(new ListenerBinder[binderList.size()]);
    }

    private ListenerBinderFactory() {

    }

    /**
     * {@link WidgetHandle} に対応する {@link ListenerBinder} を取得します。<br />
     * 
     * @param handle
     *            {@link WidgetHandle} オブジェクト
     * @return {@link ListenerBinder} オブジェクト
     */
    public static ListenerBinder getListenerBinder(final WidgetHandle handle) {
        for (int i = 0; i < binders.length; i++) {
            Class<?> clazz = binders[i].getTargetCLass();
            if (clazz.isAssignableFrom(handle.getWidgetClass())) {
                return binders[i];
            }
        }
        return null;
    }
}
