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
package org.seasar.uruma.renderer.impl;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.component.CommonAttributes;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UICompositeComponent;
import org.seasar.uruma.component.impl.CompositeComponent;
import org.seasar.uruma.renderer.layout.LayoutSupport;
import org.seasar.uruma.renderer.layout.LayoutSupportFactory;

/**
 * {@link Composite} 用レンダラの基底クラスです。<br />
 * {@link Composite} のサブクラスに対するレンダリングを行い場合、本クラスを継承してください。<br />
 * 本クラスを継承することで、レイアウトに関するレンダリングは自動的に行われます。
 * 
 * @author y-komori
 * 
 * @param <COMPONENT_TYPE>
 *            レンダラに対応するコンポーネントの型
 * @param <COMPOSITE_TYPE>
 *            レンダラが生成するウィジットの型
 * @see org.eclipse.swt.widgets.Composite
 * @see org.eclipse.swt.widgets.Layout
 */
public abstract class AbstractCompositeRenderer<COMPONENT_TYPE extends CompositeComponent, COMPOSITE_TYPE extends Composite>
        extends AbstractControlRenderer<COMPONENT_TYPE, COMPOSITE_TYPE> {

    /**
     * サブクラスでのレンダリングを行います。</br>
     * <p>
     * {@link AbstractCompositeRenderer} のサブクラスは、本メソッドをオーバーライドしてレンダリングを行ってください。
     * </p>
     * 
     * @param compositeComponent
     *            コンポジットの情報を持つコンポーネント
     * @param composite
     *            レンダリング対象のコンポジット
     */
    abstract protected void doRenderComposite(
            COMPONENT_TYPE compositeComponent, COMPOSITE_TYPE composite);

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractControlRenderer#inherit(org.seasar.uruma.component.impl.ControlComponent)
     */
    @Override
    protected void inherit(final COMPONENT_TYPE uiComponent) {
        super.inherit(uiComponent);
        inheritCommonAttributes(uiComponent);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractControlRenderer#doRenderControl(org.seasar.uruma.component.impl.ControlComponent,
     *      org.eclipse.swt.widgets.Control)
     */
    @Override
    protected final void doRenderControl(
            final COMPONENT_TYPE compositeComponent,
            final COMPOSITE_TYPE control) {
        setLayout(compositeComponent, control);

        doRenderComposite(compositeComponent, control);
    }

    /**
     * レイアウトを設定します。<br />
     * <code>control</code> に対して <code>compositeComponent</code> の保持する
     * {@link LayoutInfo} から {@link Layout} を生成して設定します。<br />
     * 
     * @param compositeComponent
     *            レイアウト情報を保持する {@link CompositeComponent}
     * @param control
     *            レイアウトを設定する {@link Composite}
     */
    @SuppressWarnings("unchecked")
    protected void setLayout(final COMPONENT_TYPE compositeComponent,
            final COMPOSITE_TYPE control) {
        LayoutInfo<?> layoutInfo = compositeComponent.getLayoutInfo();
        if (layoutInfo != null) {
            LayoutSupport layoutSupport = LayoutSupportFactory
                    .getLayoutSupport((Class<? extends LayoutInfo<?>>) layoutInfo
                            .getClass());
            Layout layout = layoutSupport.createLayout(compositeComponent
                    .getLayoutInfo());
            control.setLayout(layout);
        }
    }

    /**
     * <code>compositeComponent</code> の親コンポーネントから共通属性をコピーします。<br />
     * 
     * @param compositeComponent
     *            自コンポーネント
     */
    protected void inheritCommonAttributes(
            final COMPONENT_TYPE compositeComponent) {
        UIComponent parent = compositeComponent.getParent();
        if (parent == null) {
            return;
        }

        if (!(parent instanceof UICompositeComponent)) {
            return;
        }

        CommonAttributes parentAttributes = ((UICompositeComponent) parent)
                .getCommonAttributes();
        if (parentAttributes == null) {
            return;
        }

        CommonAttributes commonAttributes = compositeComponent
                .getCommonAttributes();
        if (commonAttributes == null) {
            compositeComponent.setCommonAttributes(parentAttributes);
        } else {
            BeanDesc desc = BeanDescFactory.getBeanDesc(commonAttributes
                    .getClass());
            int size = desc.getPropertyDescSize();
            for (int i = 0; i < size; i++) {
                PropertyDesc pd = desc.getPropertyDesc(i);
                if (pd.getValue(commonAttributes) == null) {
                    Object value = pd.getValue(parentAttributes);
                    pd.setValue(commonAttributes, value);
                }
            }
        }
    }
}
