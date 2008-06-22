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

import java.lang.reflect.Method;

import org.eclipse.jface.viewers.StructuredViewer;
import org.seasar.uruma.annotation.AsyncMethod;
import org.seasar.uruma.binding.method.impl.OmissionArgumentsFilter;
import org.seasar.uruma.binding.method.impl.StructuredSelectionArgumentsFilter;
import org.seasar.uruma.binding.method.impl.TypedEventArgumentsFilter;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.rcp.core.UrumaBundleState;
import org.seasar.uruma.rcp.core.UrumaBundleState.BundleState;

/**
 * {@link MethodBinding} を生成するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class MethodBindingFactory {
    private MethodBindingFactory() {

    }

    /**
     * {@link MethodBinding} を生成します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param method
     *            ターゲットメソッド
     * @param handle
     *            呼び出し元となるウィジットを保持する {@link WidgetHandle} オブジェクト
     * @param asyncMethod
     *            非同期実行を行う場合はそのアノテーション
     * @return {@link MethodBinding} オブジェクト
     */
    public static MethodBinding createMethodBinding(final Object target,
            final Method method, final WidgetHandle handle,
            final AsyncMethod asyncMethod) {
        MethodBinding binding;
        if (asyncMethod == null
                || UrumaBundleState.getInstance().getUrumaBundleState() == BundleState.NOT_AVAILABLE) {
            binding = new MethodBinding(target, method, null);
        } else {
            binding = new AsyncMethodBinding(target, method, null);
            ((AsyncMethodBinding) binding).setTaskNameProperty(asyncMethod
                    .nameProperty());
            ((AsyncMethodBinding) binding).setCancelable(asyncMethod
                    .cancelable());
        }

        Class<?> widgetClass = handle.getWidgetClass();
        if (StructuredViewer.class.isAssignableFrom(widgetClass)) {
            binding.addArgumentsFilter(new StructuredSelectionArgumentsFilter(
                    method));
        } else {
            binding.addArgumentsFilter(new OmissionArgumentsFilter(method));
            binding.addArgumentsFilter(new TypedEventArgumentsFilter(method));
        }

        return binding;
    }
}
