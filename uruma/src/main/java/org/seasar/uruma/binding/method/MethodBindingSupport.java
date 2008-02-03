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

import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.desc.PartActionDescFactory;
import org.seasar.uruma.exception.UnsupportedClassException;
import org.seasar.uruma.exception.WidgetNotFoundException;
import org.seasar.uruma.log.UrumaLogger;

/**
 * メソッドバインディングの生成をサポートするクラスです。</br>
 * 
 * @author y-komori
 */
public class MethodBindingSupport implements UrumaMessageCodes {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(MethodBindingSupport.class);

    /**
     * 指定された {@link WindowContext} 配下のすべての {@link PartContext} に対して、
     * {@link #createListeners(PartContext)} メソッドを実行します。<br />
     * 
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    public static void createListeners(final WindowContext context) {
        for (PartContext partContext : context.getPartContextList()) {
            createListeners(partContext);
        }
    }

    /**
     * 指定された {@link PartContext} のパートアクションコンポーネントに対して、メソッドバインディングを生成します。<br />
     * <p>
     * 本メソッドでは、以下の手順でメソッドバインディングを行います。
     * <ol>
     * <li>ウィンドウに対応するアクションコンポーネント(「<i>ウィンドウ名</i>Action」
     * という名前のコンポーネント)をコンテナから検索します。
     * <li>コンポーネントが見つかった場合、 そのコンポーネントで {@link EventListener}
     * アノテーションを持つメソッドを探します。
     * <li> 見つかった各メソッドに対して、メソッド名と同じ id を持つウィジットを {@link PartContext} から探します。
     * <li>ウィジットが見つかれば、リスナを生成してウィジット上でアクションが発生した際に、
     * アノテーションが付加されたメソッドを呼び出すように設定します。
     * </ol>
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

            List<EventListenerDef> eDefList = actionDesc
                    .getEventListenerDefList();
            for (EventListenerDef def : eDefList) {
                createListener(context, def);
            }
        }
    }

    private static void createListener(final PartContext context,
            final EventListenerDef def) {
        Method targetMethod = def.getTargetMethod();

        String[] ids = def.getId();
        for (String id : ids) {
            // すべてのコンテクストから id にマッチする WidgetHandle を検索
            List<WidgetHandle> handles = context.getWindowContext()
                    .findWidgetHandles(id);

            if (handles.size() > 0) {
                // 見つかった各 WidgetHandle に対してリスナを設定
                for (WidgetHandle handle : handles) {
                    ListenerBinder binder = ListenerBinderFactory
                            .getListenerBinder(handle, def.getType());

                    bindMethod(binder, id, handle, context, def);
                }
            } else {
                String className = targetMethod.getDeclaringClass().getName();
                throw new WidgetNotFoundException(id, className);
            }
        }
    }

    private static void bindMethod(final ListenerBinder binder,
            final String id, final WidgetHandle handle,
            final PartContext context, final EventListenerDef def) {
        if (binder != null) {
            MethodBinding binding = MethodBindingFactory.createMethodBinding(
                    context.getPartActionObject(), def.getTargetMethod(),
                    handle);
            Class<?> listenerClass = binder.bindListener(handle, context,
                    binding, def);

            if (logger.isDebugEnabled()) {
                logger.log(CREATE_METHOD_BINDING, id, def.getType(), binding,
                        listenerClass.getName());
            }
        } else {
            throw new UnsupportedClassException(handle.getWidgetClass());
        }

    }

}
