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

import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.util.ClassUtil;
import org.seasar.jface.util.FontManager;
import org.seasar.jface.util.PropertyUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * @author y-komori
 * 
 */
public abstract class AbstractControlRenderer<CONTROL_TYPE extends Control>
        implements Renderer {
    private WindowContext context;

    public Widget render(UIComponent uiComponent, Composite parent,
            WindowContext context) {
        this.context = context;

        ControlComponent controlComponent = (ControlComponent) uiComponent;
        Control control = createControl(parent, getStyle(controlComponent));

        // TODO その他のプロパティに対応
        setEnabled(control, controlComponent);
        setVisible(control, controlComponent);
        setLocation(control, controlComponent);
        setSize(control, controlComponent);
        setForeground(control, controlComponent);
        setBackground(control, controlComponent);
        setToolTipText(control, controlComponent);
        setFont(control, controlComponent);
        setLayoutData(control, controlComponent);
        
        // TODO レンダリング中に発生したRuntimeExceptionのハンドリングが必要
        doRender(getControlType().cast(control), controlComponent);

        return (Widget) control;
    }

    protected void setEnabled(Control control, ControlComponent controlComponent) {
        control.setEnabled(controlComponent.isEnabled());
    }

    protected void setVisible(Control control, ControlComponent controlComponent) {
        control.setVisible(controlComponent.isVisible());
    }

    protected void setLocation(Control control,
            ControlComponent controlComponent) {
        String xStr = controlComponent.getX();
        String yStr = controlComponent.getY();
        if ((xStr != null) && (yStr != null)) {
            int x = Integer.parseInt(xStr);
            int y = Integer.parseInt(yStr);
            control.setLocation(x, y);
        }
    }

    protected void setSize(Control control, ControlComponent controlComponent) {
        String widthStr = controlComponent.getWidth();
        String heightStr = controlComponent.getHeight();
        if ((widthStr != null) && (heightStr != null)) {
            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);
            control.setSize(width, height);
        }
    }

    protected void setForeground(Control control,
            ControlComponent controlComponent) {
        String foreColor = controlComponent.getForegroundColor();

        if (foreColor == null) {
            ControlComponent parent = getParentComponent(controlComponent);
            if (parent != null) {
                foreColor = parent.getForegroundColor();
            }
        }

        if (foreColor != null) {
            control.setForeground(SWTUtil.getColor(foreColor));
        }
    }

    protected void setBackground(Control control,
            ControlComponent controlComponent) {
        String backColor = controlComponent.getBackgroundColor();

        if (backColor == null) {
            ControlComponent parent = getParentComponent(controlComponent);
            if (parent != null) {
                backColor = parent.getBackgroundColor();
            }
        }

        if (backColor != null) {
            control.setBackground(SWTUtil.getColor(backColor));
        }
    }

    protected void setToolTipText(Control control,
            ControlComponent controlComponent) {
        control.setToolTipText(controlComponent.getToolTip());
    }

    protected void setFont(Control control, ControlComponent controlComponent) {
        FontData fontData = control.getFont().getFontData()[0];

        String fontName = controlComponent.getFont();
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

        int height = controlComponent.getFontSize();
        if (height == ControlComponent.NULL) {
            height = fontData.getHeight();
        }

        control.setFont(FontManager.get(fontName, height, style));
    }

    protected void setLayoutData(Control control,
            ControlComponent controlComponent) {
        Composite parent = control.getParent();
        Layout layout = parent.getLayout();
        if ((controlComponent.getLayoutDataNum() > 0) && (layout != null)) {
            Object layoutData = LayoutDataFactory.getLayoutData(layout
                    .getClass());

            for (Iterator<String> iter = controlComponent
                    .layoutDataNameIterator(); iter.hasNext();) {
                String name = iter.next();
                String value = controlComponent.getLayoutData(name);
                int constant = SWTUtil.getSWTConstant(value);
                if (constant != SWT.NULL) {
                    PropertyUtil.setField(layoutData, name, constant);
                } else {
                    PropertyUtil.setField(layoutData, name, value);
                }
            }

            control.setLayoutData(layoutData);
        }
    }

    protected int getStyle(ControlComponent controlComponent) {
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

    protected WindowContext getContext() {
        return this.context;
    }

    protected abstract void doRender(CONTROL_TYPE control,
            ControlComponent controlComponent);

    protected abstract Class<CONTROL_TYPE> getControlType();
}
