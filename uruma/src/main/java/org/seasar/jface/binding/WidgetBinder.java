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
package org.seasar.jface.binding;

import java.lang.reflect.Field;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.WindowContext;

/**
 * 任意のオブジェクトに対してウィジットバインディングを実行するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class WidgetBinder {
    /**
     * 指定されたオブジェクトに対して、ウィジットバインディングを行います。<br />
     * <p>
     * <code>target</code> で指定されたオブジェクトに対して、<code>context</code> で指定された
     * {@link WindowContext} オブジェクトに登録されている {@link Widget} をバインドします。<br />
     * 具体的には、<code>target</code> で定義されるフィールドのうち、{@link Widget}
     * のサブクラスであるフィールドに対し、そのフィールド名と名前が一致するウィジットを
     * {@link WindowContext#getComponent(String)} メソッドで取得し、見つかればそのフィールドにセットします。<br />
     * {@link WindowContext} に {@link Viewer} が登録されている場合でも、名前が一致していれば
     * {@link Viewer} が保持する {@link Control} を取得してバインドします。<br />
     * この際、フィールドは <code>private</code> でも構いません。セッターメソッドも不要です。
     * </p>
     * <p>
     * また、フィールドが {@link Viewer} のサブクラスである場合、フィールド名と名前が一致する {@link Viewer} を
     * {@link WindowContext#getViewerComponent(String)} メソッドによって取得し、バインドします。<br />
     * </p>
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    public static void bindWidgets(final Object target,
            final WindowContext context) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());
        int fieldNum = beanDesc.getFieldSize();
        for (int i = 0; i < fieldNum; i++) {
            Field field = beanDesc.getField(i);
            Class<?> fieldType = field.getType();
            String fieldName = field.getName();

            if (Widget.class.isAssignableFrom(fieldType)) {
                Widget widget = context.getComponent(fieldName);
                if ((widget != null)
                        && (fieldType.isAssignableFrom(widget.getClass()))) {
                    FieldUtil.set(field, target, widget);
                } else {
                    Viewer viewer = context.getViewerComponent(fieldName);
                    if (viewer != null) {
                        Control control = viewer.getControl();
                        if (fieldType.isAssignableFrom(control.getClass())) {
                            FieldUtil.set(field, target, control);
                        }
                    }
                }
            } else if (Viewer.class.isAssignableFrom(fieldType)) {
                Viewer viewer = context.getViewerComponent(fieldName);
                if ((viewer != null)
                        && (fieldType.isAssignableFrom(viewer.getClass()))) {
                    FieldUtil.set(field, target, viewer);
                }

            } else if (IStatusLineManager.class.isAssignableFrom(fieldType)) {
                // IStatusLineMnager のバインディング
                FieldUtil.set(field, target, context.getStatusLineManager());
            }
        }
    }
}
