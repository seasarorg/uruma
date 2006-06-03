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

import static org.seasar.jface.component.impl.ControlComponent.BACKGROUND_COLOR_PROP;
import static org.seasar.jface.component.impl.ControlComponent.ENABLED_PROP;
import static org.seasar.jface.component.impl.ControlComponent.FONT_PROP;
import static org.seasar.jface.component.impl.ControlComponent.FONT_SIZE_PROP;
import static org.seasar.jface.component.impl.ControlComponent.FONT_STYLE_PROP;
import static org.seasar.jface.component.impl.ControlComponent.FOREGROUND_COLOR_PROP;
import static org.seasar.jface.component.impl.ControlComponent.HEIGHT_PROP;
import static org.seasar.jface.component.impl.ControlComponent.TOOL_TIP_PROP;
import static org.seasar.jface.component.impl.ControlComponent.VISIBLE_PROP;
import static org.seasar.jface.component.impl.ControlComponent.WIDTH_PROP;
import static org.seasar.jface.component.impl.ControlComponent.X_PROP;
import static org.seasar.jface.component.impl.ControlComponent.Y_PROP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.Inheritance;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.layout.LayoutSupport;
import org.seasar.jface.layout.LayoutSupportFactory;
import org.seasar.jface.util.ClassUtil;
import org.seasar.jface.util.FontManager;
import org.seasar.jface.util.SWTUtil;

/**
 * @author y-komori
 * 
 */
public abstract class AbstractControlRenderer<CONTROL_TYPE extends Control>
        extends AbstractRenderer {

    public Widget render(UIComponent uiComponent, Composite parent,
            WindowContext context) {
        setContext(context);

        ControlComponent controlComponent = (ControlComponent) uiComponent;

        setupInheritance(controlComponent);
        inheritProperty(controlComponent);

        Control control = createControl(parent, getStyle(controlComponent));

        // TODO その他のプロパティに対応
        setLayoutData(control, controlComponent);
        setEnabled(control, controlComponent);
        setVisible(control, controlComponent);
        setLocation(control, controlComponent);
        setSize(control, controlComponent);
        setForeground(control, controlComponent);
        setBackground(control, controlComponent);
        setToolTipText(control, controlComponent);
        setFont(control, controlComponent);

        // TODO レンダリング中に発生したRuntimeExceptionのハンドリングが必要
        doRender(getControlType().cast(control), controlComponent);

        return (Widget) control;
    }

    public void renderAfter(Widget widget, UIComponent uiComponent,
            Composite parent, WindowContext context) {
        // do nothing.
    }

    /**
     * <code>Control</code> コンポーネントは子要素を持たないため、デフォルトで
     * <code>Inheritance.NONE</code> を返します。</br> <code>Composite</code>
     * コンポーネントでは、本メソッドをオーバーライドして、適切な <code>Inheritance</code> を返してください。
     * 
     * @see org.seasar.jface.renderer.Renderer#getDefaultInheritance()
     * @see Inheritance
     */
    public Inheritance getDefaultInheritance(final String propertyName) {
        return Inheritance.NONE;
    }

    protected void setEnabled(Control control, ControlComponent controlComponent) {
        Property prop = controlComponent.getProperty(ENABLED_PROP);
        if (prop != null) {
            control.setEnabled(prop.getBooleanValue());
        }
    }

    protected void setVisible(Control control, ControlComponent controlComponent) {
        Property prop = controlComponent.getProperty(VISIBLE_PROP);
        if (prop != null) {
            control.setVisible(prop.getBooleanValue());
        }
    }

    protected void setLocation(Control control,
            ControlComponent controlComponent) {
        Property xProp = controlComponent.getProperty(X_PROP);
        Property yProp = controlComponent.getProperty(Y_PROP);
        if ((xProp != null) && (yProp != null) && xProp.isValueExist()
                && yProp.isValueExist()) {
            control.setLocation(xProp.getIntValue(), yProp.getIntValue());
        }
    }

    protected void setSize(Control control, ControlComponent controlComponent) {
        Property widthProp = controlComponent.getProperty(WIDTH_PROP);
        Property heightProp = controlComponent.getProperty(HEIGHT_PROP);
        if ((widthProp != null) && (heightProp != null)
                && widthProp.isValueExist() && heightProp.isValueExist()) {
            control.setSize(widthProp.getIntValue(), heightProp.getIntValue());
        }
    }

    protected void setForeground(Control control,
            ControlComponent controlComponent) {
        String foreColor = controlComponent
                .getPropertyValue(FOREGROUND_COLOR_PROP);
        if (foreColor != null) {
            control.setForeground(SWTUtil.getColor(foreColor));
        }
    }

    protected void setBackground(Control control,
            ControlComponent controlComponent) {
        String backColor = controlComponent
                .getPropertyValue(BACKGROUND_COLOR_PROP);
        if (backColor != null) {
            control.setBackground(SWTUtil.getColor(backColor));
        }
    }

    protected void setToolTipText(Control control,
            ControlComponent controlComponent) {
        control
                .setToolTipText(controlComponent
                        .getPropertyValue(TOOL_TIP_PROP));
    }

    protected void setFont(Control control, ControlComponent controlComponent) {
        FontData fontData = control.getFont().getFontData()[0];

        String fontName = controlComponent.getPropertyValue(FONT_PROP);
        if (fontName == null) {
            fontName = fontData.getName();
        }

        String fontStyle = controlComponent.getPropertyValue(FONT_STYLE_PROP);
        int style;
        if (fontStyle != null) {
            style = SWTUtil.getStyle(fontStyle);
            style = (style == SWT.NONE) ? SWT.NORMAL : style;
        } else {
            style = fontData.getStyle();
        }

        Property heightProp = controlComponent.getProperty(FONT_SIZE_PROP);
        int height;
        if ((heightProp != null) && heightProp.isValueExist()) {
            height = heightProp.getIntValue();
        } else {
            height = fontData.getHeight();

        }

        control.setFont(FontManager.get(fontName, height, style));
    }

    protected void setLayoutData(Control control,
            ControlComponent controlComponent) {
        Composite parent = control.getParent();
        Layout layout = parent.getLayout();
        if (layout != null) {
            LayoutSupport support = LayoutSupportFactory
                    .getLayoutSupport(layout.getClass());
            if (support != null) {
                Object layoutData = support.createLayoutData(controlComponent);
                if (layoutData != null) {
                    control.setLayoutData(layoutData);
                }
            }
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

    protected abstract void doRender(CONTROL_TYPE control,
            ControlComponent controlComponent);

    protected abstract Class<CONTROL_TYPE> getControlType();
}
