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
package org.seasar.jface.renderer.impl;

import java.util.List;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.component.CommonAttributes;
import org.seasar.jface.component.LayoutDataInfo;
import org.seasar.jface.component.LayoutInfo;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UICompositeComponent;
import org.seasar.jface.component.UIControlComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.RendererSupportUtil;
import org.seasar.jface.renderer.layout.LayoutSupport;
import org.seasar.jface.renderer.layout.LayoutSupportFactory;
import org.seasar.jface.util.AnnotationUtil;

/**
 * <code>Control</code> のレンダリングを行うための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractControlRenderer<COMPONENT_TYPE extends ControlComponent, CONTROL_TYPE extends Control>
        extends AbstractWidgetRenderer<COMPONENT_TYPE, CONTROL_TYPE> {

    @Override
    protected void inherit(COMPONENT_TYPE uiComponent) {
        // 親コンポーネントの持つ共通属性を設定する
        setCommonAttributes(uiComponent);

        // レイアウトデータの一括指定
        inheritLayoutData((UIControlComponent) uiComponent);
    }

    @Override
    public final void doRender(COMPONENT_TYPE uiComponent, CONTROL_TYPE control) {
        ControlComponent controlComponent = (ControlComponent) uiComponent;

        setLayoutData(controlComponent, control);
        setLocation(controlComponent, control);
        setSize(controlComponent, control);
        setFont(controlComponent, control);

        doRenderControl(uiComponent, control);
    }

    protected void setLocation(final ControlComponent controlComponent,
            final Control control) {
        String xStr = controlComponent.getX();
        String yStr = controlComponent.getY();
        if ((xStr != null) && (yStr != null)) {
            control.setLocation(Integer.parseInt(xStr), Integer.parseInt(yStr));
        }
    }

    protected void setSize(final ControlComponent controlComponent,
            final Control control) {
        String widthStr = controlComponent.getWidth();
        String heightStr = controlComponent.getHeight();
        if ((widthStr != null) && (heightStr != null)) {
            control.setSize(Integer.parseInt(widthStr), Integer
                    .parseInt(heightStr));
        }
    }

    protected void setFont(final ControlComponent controlComponent,
            final Control control) {
        if (controlComponent.getFontName() == null
                && controlComponent.getFontStyle() == null
                && controlComponent.getFontHeight() == null) {
            return;
        }
        Font font = RendererSupportUtil.getFont(control.getFont(),
                controlComponent.getFontName(),
                controlComponent.getFontStyle(), controlComponent
                        .getFontHeight());
        control.setFont(font);
    }

    protected void setLayoutData(final UIControlComponent uiComponent,
            final Control control) {
        UIComponent parent = uiComponent.getParent();
        if (parent == null) {
            return;
        }

        if (!(parent instanceof UICompositeComponent)) {
            return;
        }

        LayoutInfo layoutInfo = ((UICompositeComponent) parent).getLayoutInfo();
        if (layoutInfo == null) {
            return;
        }

        LayoutSupport support = LayoutSupportFactory
                .getLayoutSupport(layoutInfo.getClass());
        LayoutDataInfo layoutDataInfo = uiComponent.getLayoutDataInfo();
        if ((support != null) && (layoutDataInfo != null)) {
            Object layoutData = support.createLayoutData(uiComponent,
                    layoutDataInfo);
            if (layoutData != null) {
                control.setLayoutData(layoutData);
            }
        }
    }

    protected ControlComponent getParentComponent(ControlComponent component) {
        UIComponent parentUI = component.getParent();
        if (parentUI != null && parentUI instanceof ControlComponent) {
            return (ControlComponent) parentUI;
        } else {
            return null;
        }
    }

    protected void setCommonAttributes(final UIComponent uiComponent) {
        UIComponent parent = uiComponent.getParent();
        if (parent == null) {
            return;
        }

        if (!(parent instanceof UICompositeComponent)) {
            return;
        }

        CommonAttributes commonAttributes = ((UICompositeComponent) parent)
                .getCommonAttributes();
        if (commonAttributes == null) {
            return;
        }

        BeanDesc commonDesc = BeanDescFactory.getBeanDesc(commonAttributes
                .getClass());
        BeanDesc uiDesc = BeanDescFactory.getBeanDesc(uiComponent.getClass());
        int size = commonDesc.getPropertyDescSize();
        for (int i = 0; i < size; i++) {
            PropertyDesc commonPd = commonDesc.getPropertyDesc(i);
            PropertyDesc uiPd = uiDesc.getPropertyDesc(commonPd
                    .getPropertyName());
            // 未設定の属性のみ設定する
            if (uiPd.getValue(uiComponent) == null) {
                uiPd.setValue(uiComponent, commonPd.getValue(commonAttributes));
            }
        }
    }

    protected void inheritLayoutData(final UIControlComponent uiComponent) {
        LayoutDataInfo parentLayoutDataInfo = getParentLayoutDataInfo(uiComponent);

        // 親が一括指定すべきレイアウトデータを持っていない場合は何もしない
        if (parentLayoutDataInfo == null) {
            return;
        }

        LayoutDataInfo layoutDataInfo = uiComponent.getLayoutDataInfo();
        if (layoutDataInfo == null) {
            // LayoutDataInfo が未設定の場合はそのまま設定する
            uiComponent.setLayoutDataInfo(parentLayoutDataInfo);
        } else {
            // 既に設定されている場合は未設定の属性のみ設定する
            BeanDesc parentDesc = BeanDescFactory
                    .getBeanDesc(parentLayoutDataInfo.getClass());
            List<PropertyDesc> pdList = AnnotationUtil
                    .getAnnotatedPropertyDescs(layoutDataInfo.getClass(),
                            ComponentAttribute.class);

            for (PropertyDesc pd : pdList) {
                if (pd.getValue(layoutDataInfo) == null) {
                    PropertyDesc parentPd = parentDesc.getPropertyDesc(pd
                            .getPropertyName());
                    pd.setValue(layoutDataInfo, parentPd
                            .getValue(parentLayoutDataInfo));
                }
            }
        }
    }

    protected LayoutDataInfo getParentLayoutDataInfo(
            final UIComponent uiComponent) {
        UIComponent parent = uiComponent.getParent();
        if (parent == null) {
            return null;
        }

        if (!(parent instanceof UICompositeComponent)) {
            return null;
        }

        LayoutInfo layoutInfo = ((UICompositeComponent) parent).getLayoutInfo();
        if (layoutInfo != null) {
            return layoutInfo.getCommonLayoutDataInfo();
        }
        return null;
    }

    protected abstract void doRenderControl(COMPONENT_TYPE controlComponent,
            CONTROL_TYPE control);
}
