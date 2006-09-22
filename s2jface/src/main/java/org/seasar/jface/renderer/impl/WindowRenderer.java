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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.util.GeometryUtil;
import org.seasar.jface.util.ImageManager;
import org.seasar.jface.util.PathUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * <code>Window</code> のレンダリングを行うためのクラスです。<br />
 * 
 * @author y-komori
 */
public class WindowRenderer extends
        AbstractCompositeRenderer<WindowComponent, Composite> {
    public int getShellStyle(final WindowComponent uiComponent) {
        String styleString = uiComponent.getStyle();
        if (styleString != null) {
            return SWTUtil.getStyle(styleString);
        } else {
            return getDefaultStyle();
        }
    }

    @Override
    public Widget render(UIComponent uiComponent, Composite parent,
            WindowContext context) {
        configureShell((WindowComponent) uiComponent, (Shell) context
                .getComponent(WindowContext.SHELL_ID));
        return super.render(uiComponent, parent, context);
    }

    public void renderAfter(final Widget widget, final UIComponent uiComponent,
            final Composite parent, final WindowContext context) {
        // デフォルトボタンの設定
        setDefaultButton((WindowComponent) uiComponent, context);
    }

    protected void configureShell(final WindowComponent window,
            final Shell shell) {
        // タイトルの設定
        String title = window.getTitle();
        if (title != null) {
            shell.setText(title);
        }

        // ウィンドウサイズの設定
        String width = window.getWidth();
        String height = window.getHeight();
        if ((width != null) && (height != null)) {
            shell.setSize(calcWidth(width), calcHeight(height));
            shell.setLocation(calcX(window), calcY(window));
        }

        // 最小ウィンドウサイズの設定
        setMinimumSize(window, shell);

        // アイコンの設定
        String img = window.getImage();
        if (!StringUtil.isEmpty(img)) {
            img = PathUtil.createPath(window.getBasePath(), img);
            shell.setImage(ImageManager.loadImage(img));
        }
    }

    protected int calcWidth(final String width) {
        return GeometryUtil.calcSize(width, Display.getCurrent()
                .getClientArea().width);
    }

    protected int calcHeight(final String height) {
        return GeometryUtil.calcSize(height, Display.getCurrent()
                .getClientArea().height);
    }

    protected int calcX(final WindowComponent window) {
        return GeometryUtil.calcPosition(window.getX(), Display.getCurrent()
                .getClientArea().width, calcWidth(window.getWidth()));
    }

    protected int calcY(final WindowComponent window) {
        return GeometryUtil.calcPosition(window.getY(), Display.getCurrent()
                .getClientArea().height, calcHeight(window.getHeight()));
    }

    protected void setDefaultButton(final WindowComponent windowComponent,
            final WindowContext context) {
        Widget defaultButtonWidget = context.getComponent(windowComponent
                .getDefaultButtonId());
        if (defaultButtonWidget instanceof Button) {
            Button defaultButton = (Button) defaultButtonWidget;
            Shell shell = (Shell) context.getComponent(WindowContext.SHELL_ID);
            if (shell != null) {
                shell.setDefaultButton(defaultButton);
                defaultButton.forceFocus();
            }
        }
    }

    protected void setMinimumSize(final WindowComponent windowComponent,
            final Shell shell) {
        String minWidth = windowComponent.getMinimumWidth();
        String minHeight = windowComponent.getMinimumHeight();
        if ((minWidth != null) && (minHeight != null)) {
            shell.setMinimumSize(calcWidth(minWidth), calcHeight(minHeight));
        }
    }

    @Override
    protected Class<Composite> getControlType() {
        return Composite.class;
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.SHELL_TRIM;
    }

    @Override
    protected void doRenderComposite(WindowComponent compositeComponent,
            Composite composite) {
        // Do nothing.
    }

    @Override
    protected void setLocation(ControlComponent controlComponent,
            Control control) {
        // Do nothing.
    }

    @Override
    protected void setSize(ControlComponent controlComponent, Control control) {
        // Do nothing.
    }
}
