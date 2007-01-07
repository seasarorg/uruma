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

import java.lang.reflect.Field;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.WindowContext;

/**
 * アクションクラスに対してウィジットバインディングを実行するためのクラスです。
 * 
 * @author y-komori
 */
public class WidgetBinder {
    /**
     * 指定されたオブジェクトに対して、ウィジットバインディングを行います。
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param context
     *            {@link WindowContext}
     */
    public static void bindWidgets(final Object target,
            final WindowContext context) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());
        int fieldNum = beanDesc.getFieldSize();
        for (int i = 0; i < fieldNum; i++) {
            Field field = beanDesc.getField(i);
            Class<?> fieldType = field.getType();
            if (Widget.class.isAssignableFrom(fieldType)) {
                Widget widget = context.getComponent(field.getName());
                if ((widget != null)
                        && (fieldType.isAssignableFrom(widget.getClass()))) {
                    FieldUtil.set(field, target, widget);
                }
            } else if (IStatusLineManager.class.isAssignableFrom(fieldType)) {
                // IStatusLineMnager のバインディング
                FieldUtil.set(field, target, context.getStatusLineManager());
            }
        }
    }
}
