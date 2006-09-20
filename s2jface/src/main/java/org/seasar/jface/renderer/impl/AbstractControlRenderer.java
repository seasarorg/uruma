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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.component.CommonAttributes;
import org.seasar.jface.component.LayoutDataInfo;
import org.seasar.jface.component.LayoutInfo;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UICompositeComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.RendererSupportUtil;
import org.seasar.jface.renderer.layout.LayoutSupport;
import org.seasar.jface.renderer.layout.LayoutSupportFactory;
import org.seasar.jface.util.AnnotationUtil;
import org.seasar.jface.util.ClassUtil;
import org.seasar.jface.util.FontManager;
import org.seasar.jface.util.SWTUtil;

/**
 * <code>Control</code> のレンダリングを行うための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractControlRenderer<COMPONENT_TYPE extends ControlComponent, CONTROL_TYPE extends Control>
        extends AbstractRenderer {

    public Widget render(UIComponent uiComponent, Composite parent,
            WindowContext context) {
        setContext(context);

        ControlComponent controlComponent = (ControlComponent) uiComponent;

        // 親コンポーネントの持つ共通属性を設定する
        setCommonAttributes(uiComponent);

        // レイアウトデータの一括指定
        inheritLayoutData(uiComponent);

        Control control = createControl(parent, getStyle(controlComponent));

        RendererSupportUtil.setAttributes(controlComponent, control);
        setLayoutData(controlComponent, control);
        setLocation(controlComponent, control);
        setSize(controlComponent, control);
        setFont(controlComponent, control);

        // TODO レンダリング中に発生したRuntimeExceptionのハンドリングが必要
        doRender((COMPONENT_TYPE) controlComponent, getControlType().cast(
                control));

        control.setData(this);

        return (Widget) control;
    }

    public void renderAfter(Widget widget, UIComponent uiComponent,
            Composite parent, WindowContext context) {
        // do nothing.
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
        FontData fontData = control.getFont().getFontData()[0];

        String fontName = controlComponent.getFontName();
        if (fontName == null) {
            fontName = fontData.getName();
        }

        String fontStyle = controlComponent.getFontStyle();
        int style;
        if (fontStyle != null) {
            style = SWTUtil.getStyle(fontStyle);
            style = (style == SWT.NONE) ? SWT.NORMAL : style;
        } else {
            style = fontData.getStyle();
        }

        String heightStr = controlComponent.getFontHeight();
        int height;
        if (heightStr != null) {
            height = Integer.parseInt(heightStr);
        } else {
            height = fontData.getHeight();

        }

        control.setFont(FontManager.get(fontName, height, style));
    }

    protected void setLayoutData(final UIComponent uiComponent,
            final Control control) {
        UICompositeComponent parent = uiComponent.getParent();
        if (parent != null) {
            LayoutInfo layoutInfo = parent.getLayoutInfo();
            if (layoutInfo != null) {
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
        }
    }

    protected int getStyle(final ControlComponent controlComponent) {
        return SWTUtil.getStyle(controlComponent.getStyle(), getDefaultStyle());
    }

    protected int getDefaultStyle() {
        return SWT.NONE;
    }

    protected Control createControl(Composite parent, int style) {
        Class<? extends Control> controlClass = getControlType();
        Control control = ClassUtil.<Control> newInstance(controlClass, parent,
                style);
        return control;
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
        UICompositeComponent parent = uiComponent.getParent();
        if (parent == null) {
            return;
        }

        CommonAttributes commonAttributes = parent.getCommonAttributes();
        if (commonAttributes != null) {
            BeanDesc commonDesc = BeanDescFactory.getBeanDesc(commonAttributes
                    .getClass());
            BeanDesc uiDesc = BeanDescFactory.getBeanDesc(uiComponent
                    .getClass());
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
    }

    protected void inheritLayoutData(final UIComponent uiComponent) {
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
        UICompositeComponent parent = uiComponent.getParent();
        if (parent != null) {
            LayoutInfo layoutInfo = parent.getLayoutInfo();
            if (layoutInfo != null) {
                return layoutInfo.getCommonLayoutDataInfo();
            }
        }
        return null;
    }

    protected abstract void doRender(COMPONENT_TYPE controlComponent,
            CONTROL_TYPE control);

    protected abstract Class<CONTROL_TYPE> getControlType();
}
