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
package org.seasar.jface.renderer;

import static org.seasar.jface.component.Inheritance.CHILD;
import static org.seasar.jface.component.Inheritance.CHILD_ONLY;
import static org.seasar.jface.component.Inheritance.DESCENDANT;
import static org.seasar.jface.component.Inheritance.DESCENDANT_ONLY;
import static org.seasar.jface.component.Inheritance.NONE;
import static org.seasar.jface.component.Inheritance.NULL;

import org.seasar.jface.WindowContext;
import org.seasar.jface.component.Inheritance;
import org.seasar.jface.component.LayoutData;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfoAccessor;

/**
 * @author y-komori
 * 
 */
public abstract class AbstractRenderer implements Renderer {
    private WindowContext context;

    /**
     * 自コンポーネントの持つプロパティのうち、inheritance 属性が未設定のものに対して、レンダラの提供するデフォルト値を設定します。
     * 
     * @param uiComponent
     *            コンポーネント
     */
    protected void setupInheritance(final UIComponent uiComponent) {
        Renderer renderer = uiComponent.getRenderer();
        for (Property property : uiComponent.getProperties()) {
            Inheritance inheritance = property.getInheritance();
            if (inheritance == NULL) {
                property.setInheritance(ComponentInfoAccessor.getInheritance(
                        renderer.getComponentInfo(), property.getName()));
            }
        }
    }

    /**
     * 親コンポーネントからプロパティ属性を引き継ぎます。</br>
     * <p>
     * 自コンポーネントに同名のプロパティが存在する場合は、引き継ぎを行いません。
     * </p>
     * <p>
     * 引き継ぎを行う場合は、以下のように inheritance 属性を書き換えます。</br>
     * </p>
     * <dl>
     * <dt>CHILDの場合
     * <dd>NONEに書き換える
     * <dt>CHILD_ONLYの場合
     * <dd>NONEに書き換える
     * <dt>DESCENDANTの場合
     * <dd>書き換えない(DESCENDANTのまま)
     * <dt>DESCENDANT_ONLYの場合
     * <dd>DESCENDANTに書き換える
     * </dl>
     * 
     * @param uiComponent
     *            自コンポーネント
     */
    protected void inheritProperty(final UIComponent uiComponent) {
        UIComponent parent = uiComponent.getParent();
        for (Property property : parent.getProperties()) {
            if (uiComponent.getProperty(property.getName()) == null) {
                Inheritance inheritance = property.getInheritance();
                if (inheritance == CHILD) {
                    uiComponent.addProperty(property.cloneProperty(NONE));
                } else if (inheritance == CHILD_ONLY) {
                    uiComponent.addProperty(property.cloneProperty(NONE));
                } else if (inheritance == DESCENDANT) {
                    uiComponent.addProperty(property.cloneProperty(DESCENDANT));
                } else if (inheritance == DESCENDANT_ONLY) {
                    uiComponent.addProperty(property.cloneProperty(DESCENDANT));
                }
            }
        }
    }

    /**
     * 親コンポーネントからLayoutData属性を引き継ぎます。</br>
     * <p>
     * 自コンポーネントに同名のLayoutDataが存在する場合は、引き継ぎを行いません。
     * </p>
     * <p>
     * 引き継ぎを行う場合は、以下のように inheritance 属性を書き換えます。</br>
     * </p>
     * <dl>
     * <dt>CHILDの場合
     * <dd>NONEに書き換える
     * <dt>CHILD_ONLYの場合
     * <dd>NONEに書き換える
     * <dt>DESCENDANTの場合
     * <dd>書き換えない(DESCENDANTのまま)
     * <dt>DESCENDANT_ONLYの場合
     * <dd>DESCENDANTに書き換える
     * </dl>
     * 
     * @param component
     *            自コンポーネント
     */
    protected void inheritLayoutData(final ControlComponent component) {
        UIComponent parent = component.getParent();
        if (parent instanceof ControlComponent) {
            ControlComponent parentControl = (ControlComponent) parent;

            for (LayoutData layoutData : parentControl
                    .getLayoutDataCollection()) {
                if (component.getLayoutData(layoutData.getName()) == null) {
                    Inheritance inheritance = layoutData.getInheritance();
                    if (inheritance == CHILD) {
                        component.addLayoutData(layoutData
                                .cloneLayoutData(NONE));
                    } else if (inheritance == CHILD_ONLY) {
                        component.addLayoutData(layoutData
                                .cloneLayoutData(NONE));
                    } else if (inheritance == DESCENDANT) {
                        component.addLayoutData(layoutData
                                .cloneLayoutData(DESCENDANT));
                    } else if (inheritance == DESCENDANT_ONLY) {
                        component.addLayoutData(layoutData
                                .cloneLayoutData(DESCENDANT));
                    }
                }
            }
        }
    }

    protected WindowContext getContext() {
        return this.context;
    }

    protected void setContext(WindowContext context) {
        this.context = context;
    }
}
