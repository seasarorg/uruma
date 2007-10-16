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
package org.seasar.uruma.binding.method;

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.binding.method.impl.OmissionArgumentsFilter;
import org.seasar.uruma.binding.method.impl.TypedEventArgumentsFilter;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.desc.PartActionDescFactory;
import org.seasar.uruma.exception.WidgetNotFoundException;

/**
 * メソッドバインディングの生成をサポートするクラスです。</br>
 * 
 * @author y-komori
 */
public class MethodBindingSupport {
    /**
     * 指定された名前のウィンドウに対して、メソッドバインディングを行います。<br>
     * <p>
     * 本メソッドでは、以下の手順でメソッドバインディングを行います。
     * <ul>
     * <li>ウィンドウに対応するアクションコンポーネント(「<i>ウィンドウ名</i>Action」
     * という名前のコンポーネント)をコンテナから検索します。
     * <li>コンポーネントが見つかった場合、 そのコンポーネントで {@link EventListener}
     * アノテーションを持つメソッドを探します。
     * <li> 見つかった各メソッドに対して、メソッド名と同じ id を持つウィジットを {@link PartContext}> から探します。
     * <li>ウィジットが見つかれば、リスナを生成してウィジット上でアクションが発生した際に、
     * アノテーションが付加されたメソッドを呼び出すように設定します。
     * </ul>
     * </p>
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void createListeners(final PartContext context) {
        Object actionObj = context.getPartActionObject();
        if (actionObj != null) {
            PartActionDesc actionDesc = PartActionDescFactory
                    .getPartActionDesc(actionObj.getClass());

            List<EventListenerDef> defList = actionDesc
                    .getEventListenerDefList();
            for (EventListenerDef eventListenerDef : defList) {
                createListener(context, eventListenerDef);
            }
        }
    }

    private static void createListener(final PartContext context,
            final EventListenerDef eventListenerDef) {
        Method targetMethod = eventListenerDef.getTargetMethod();
        MethodBinding methodBinding = new MethodBinding(context
                .getPartActionObject(), targetMethod);
        methodBinding.addArgumentsFilter(new OmissionArgumentsFilter(
                targetMethod));
        methodBinding.addArgumentsFilter(new TypedEventArgumentsFilter(
                targetMethod));

        Listener listener = new GenericListener(context, methodBinding);
        String[] ids = eventListenerDef.getEventListener().id();
        for (String id : ids) {
            WidgetHandle handle = context.getWidgetHandle(id);
            if (handle != null) {
                Widget widget = handle.<Widget> getCastWidget();
                widget.addListener(eventListenerDef.getEventListener().type()
                        .getSWTEventType(), listener);
            } else {
                String className = targetMethod.getDeclaringClass().getName();
                throw new WidgetNotFoundException(id, className);
            }
        }
    }
}
