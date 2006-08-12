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
package org.seasar.jface.binding;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.EventListenerType;
import org.seasar.jface.container.EventListenerDef;
import org.seasar.jface.container.S2JFaceComponentDef;
import org.seasar.jface.events.ListenerFactory;
import org.seasar.jface.exception.NotFoundException;

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
     * <li>コンポーネントが見つかった場合、 そのコンポーネントで <code>EventListener</code>
     * アノテーションを持つメソッドを探します。
     * <li> 見つかった各メソッドに対して、メソッド名と同じ id を持つウィジットを <code>WindowContext</code>
     * から探します。
     * <li>ウィジットが見つかれば、リスナを生成してウィジット上でアクションが発生した際に、
     * アノテーションが付加されたメソッドを呼び出すように設定します。
     * </ul>
     * </p>
     * 
     * @param windowName
     *            ウィンドウ名称
     * @param context
     *            WindowContext
     * @see org.seasar.jface.annotation.EventListener
     * @see WindowContext
     */
    public static void createListeners(String windowName, WindowContext context) {
        S2JFaceComponentDef componentDef = getActionComponentDef(windowName);
        if (componentDef == null) {
            return;
        }
        Object action = componentDef.getComponent();

        Iterator<EventListenerDef> iter = componentDef
                .eventListenerDefIterator();
        while (iter.hasNext()) {
            EventListenerDef eventListenerDef = iter.next();
            String id = eventListenerDef.getEventListener().id();
            Widget widget = context.getComponent(id);
            if (widget != null) {
                Method targetMethod = eventListenerDef.getTargetMethod();
                MethodBinding methodBinding = new MethodBinding(action,
                        targetMethod);
                methodBinding.addArgumentsFilter(new OmissionArgumentsFilter(
                        targetMethod));
                methodBinding.addArgumentsFilter(new TypedEventArgumentsFilter(
                        targetMethod));

                EventListenerType listenerType = eventListenerDef
                        .getEventListener().type();

                Listener listener = ListenerFactory.getListener(context,
                        methodBinding);
                ListenerBinder.bindListener(listenerType, listener, widget);
            } else {
                throw new NotFoundException(NotFoundException.WIDGET, id);
            }
        }
    }

    protected static S2JFaceComponentDef getActionComponentDef(String windowName) {
        String actionComponentName = getActionComponentName(windowName);
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container.hasComponentDef(actionComponentName)) {
            return (S2JFaceComponentDef) container
                    .getComponentDef(actionComponentName);
        } else {
            return null;
        }
    }

    protected static String getActionComponentName(String windowName) {
        return StringUtil.decapitalize(windowName) + "Action";
    }
}
