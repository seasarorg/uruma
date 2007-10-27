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

import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.component.CommonAttributes;
import org.seasar.uruma.component.LayoutDataInfo;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UICompositeComponent;
import org.seasar.uruma.component.UIControlComponent;
import org.seasar.uruma.component.impl.ControlComponent;
import org.seasar.uruma.component.impl.MenuComponent;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.exception.RenderException;
import org.seasar.uruma.renderer.RendererSupportUtil;
import org.seasar.uruma.renderer.layout.LayoutSupport;
import org.seasar.uruma.renderer.layout.LayoutSupportFactory;
import org.seasar.uruma.util.AnnotationUtil;

/**
 * {@link Control} のレンダリングを行うための基底クラスです。<br />
 * 
 * @param <COMPONENT_TYPE>
 *            レンダラに対応するコンポーネントの型
 * @param <CONTROL_TYPE>
 *            レンダラが生成するコントロールの型
 * @author y-komori
 */
public abstract class AbstractControlRenderer<COMPONENT_TYPE extends ControlComponent, CONTROL_TYPE extends Control>
        extends AbstractWidgetRenderer<COMPONENT_TYPE, CONTROL_TYPE> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#inherit(org.seasar.uruma.component.UIComponent)
     */
    @Override
    protected void inherit(final COMPONENT_TYPE uiComponent) {
        // 親コンポーネントの持つ共通属性を設定する
        setCommonAttributes(uiComponent);

        // レイアウトデータの一括指定
        inheritLayoutData(uiComponent);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRender(org.seasar.uruma.component.UIComponent,
     *      java.lang.Object)
     */
    @Override
    public final void doRender(final COMPONENT_TYPE uiComponent,
            final CONTROL_TYPE control) {
        ControlComponent controlComponent = uiComponent;

        setLayoutData(controlComponent, control);
        setLocation(controlComponent, control);
        setSize(controlComponent, control);
        setFont(controlComponent, control);
        setMenu(controlComponent, control);

        doRenderControl(uiComponent, control);

        // TODO 後で削除
        // addEnabledDepend(control, uiComponent);
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

    protected void setMenu(final ControlComponent controlComponent,
            final Control control) {
        String menuId = controlComponent.getMenu();
        if (!StringUtil.isEmpty(menuId)) {
            WidgetHandle handle = getContext().getWidgetHandle(menuId);
            if (handle != null) {
                if (handle.instanceOf(MenuManager.class)) {
                    MenuManager manager = handle.<MenuManager> getCastWidget();
                    Menu menu = manager.createContextMenu(control);
                    MenuComponent menuComponent = (MenuComponent) handle
                            .getUiComponent();
                    MenuManagerRenderer renderer = (MenuManagerRenderer) menuComponent
                            .getRenderer();
                    renderer.renderMenu(menuComponent, menu);

                    control.setMenu(menu);
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            menuId, MenuManager.class.getName());
                }
            } else {
                throw new NotFoundException(NotFoundException.UICOMPONENT,
                        menuId);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void setLayoutData(final UIControlComponent uiComponent,
            final Control control) {
        UIComponent parent = uiComponent.getParent();
        if (parent == null) {
            return;
        }

        if (!(parent instanceof UICompositeComponent)) {
            return;
        }

        LayoutInfo<?> layoutInfo = ((UICompositeComponent) parent)
                .getLayoutInfo();
        if (layoutInfo == null) {
            return;
        }

        LayoutSupport support = LayoutSupportFactory
                .getLayoutSupport((Class<? extends LayoutInfo<?>>) layoutInfo
                        .getClass());
        LayoutDataInfo layoutDataInfo = uiComponent.getLayoutDataInfo();
        if ((support != null) && (layoutDataInfo != null)) {
            Object layoutData = support.createLayoutData(uiComponent,
                    layoutDataInfo);
            if (layoutData != null) {
                control.setLayoutData(layoutData);
            }
        }
    }

    protected ControlComponent getParentComponent(
            final ControlComponent component) {
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
                            RenderingPolicy.class);

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

        LayoutInfo<?> layoutInfo = ((UICompositeComponent) parent)
                .getLayoutInfo();
        if (layoutInfo != null) {
            return layoutInfo.getCommonLayoutDataInfo();
        }
        return null;
    }

    protected abstract void doRenderControl(COMPONENT_TYPE controlComponent,
            CONTROL_TYPE control);
}
