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

import static org.seasar.jface.renderer.info.WindowInfo.DEFAULT_BUTTON_PROP;
import static org.seasar.jface.renderer.info.WindowInfo.HEIGHT_PROP;
import static org.seasar.jface.renderer.info.WindowInfo.IMAGE_PROP;
import static org.seasar.jface.renderer.info.WindowInfo.TITLE_PROP;
import static org.seasar.jface.renderer.info.WindowInfo.WIDTH_PROP;
import static org.seasar.jface.renderer.info.WindowInfo.X_PROP;
import static org.seasar.jface.renderer.info.WindowInfo.Y_PROP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.WindowInfo;
import org.seasar.jface.util.GeometryUtil;
import org.seasar.jface.util.ImageManager;
import org.seasar.jface.util.PathUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * ウィンドウのレンダリングを行うクラスです。</br>
 * 
 * @author y-komori
 */
public class WindowRenderer extends AbstractCompositeRenderer<Composite> {

    @Override
    public Widget render(UIComponent uiComponent, Composite parent,
            WindowContext context) {
        configureShell((WindowComponent) uiComponent, (Shell) context
                .getComponent(WindowContext.SHELL_ID));
        return super.render(uiComponent, parent, context);
    }

    @Override
    protected void doRenderComposite(final Composite composite,
            final CompositeComponent compositeComponent) {
        // Do nothing.
    }

    @Override
    protected void setLocation(Control control,
            ControlComponent controlComponent) {
        // Do nothing.
    }

    @Override
    protected void setSize(Control control, ControlComponent controlComponent) {
        // Do nothing.
    }

    public void renderAfter(final Widget widget, final UIComponent uiComponent,
            final Composite parent, final WindowContext context) {
        setDefaultButton(uiComponent, context);
    }

    public int getShellStyle(final WindowComponent uiComponent) {
        String styleString = uiComponent.getStyle();
        if (styleString != null) {
            return SWTUtil.getStyle(styleString);
        } else {
            return getDefaultStyle();
        }
    }

    public String getRendererName() {
        return "window";
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return WindowInfo.class;
    }

    @Override
    protected Class<Composite> getControlType() {
        return Composite.class;
    }

    protected void configureShell(final WindowComponent window,
            final Shell shell) {
        Property titleProperty = window.getProperty(TITLE_PROP);
        if (titleProperty != null) {
            shell.setText(titleProperty.getValue());
        }

        if ((window.getProperty(WIDTH_PROP) != null)
                && (window.getProperty(HEIGHT_PROP) != null)) {
            shell.setSize(calcWidth(window), calcHeight(window));
            shell.setLocation(calcX(window), calcY(window));
        }

        String img = window.getPropertyValue(IMAGE_PROP);
        if (!StringUtil.isEmpty(img)) {
            img = PathUtil.createPath(window.getBasePath(), img);
            shell.setImage(ImageManager.getImage(img));
        }
    }

    protected int calcWidth(final WindowComponent window) {
        return GeometryUtil.calcSize(window.getPropertyValue(WIDTH_PROP),
                Display.getCurrent().getClientArea().width);
    }

    protected int calcHeight(final WindowComponent window) {
        return GeometryUtil.calcSize(window.getPropertyValue(HEIGHT_PROP),
                Display.getCurrent().getClientArea().height);
    }

    protected int calcX(final WindowComponent window) {
        return GeometryUtil.calcPosition(window.getPropertyValue(X_PROP),
                Display.getCurrent().getClientArea().width, calcWidth(window));
    }

    protected int calcY(final WindowComponent window) {
        return GeometryUtil
                .calcPosition(window.getPropertyValue(Y_PROP), Display
                        .getCurrent().getClientArea().height,
                        calcHeight(window));
    }

    protected void setDefaultButton(final UIComponent uiComponent,
            final WindowContext context) {
        Widget defaultButton = context.getComponent(uiComponent
                .getPropertyValue(DEFAULT_BUTTON_PROP));
        if (defaultButton instanceof Button) {
            Shell shell = (Shell) context.getComponent(WindowContext.SHELL_ID);
            if (shell != null) {
                shell.setDefaultButton((Button) defaultButton);
            }
        }
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.SHELL_TRIM;
    }
}
