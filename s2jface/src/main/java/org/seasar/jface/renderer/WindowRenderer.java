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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.util.GeometryUtil;
import org.seasar.jface.util.ImageManager;
import org.seasar.jface.util.PathUtil;

/**
 * @author y-komori
 * 
 */
public class WindowRenderer implements Renderer {
    public static final String ATTR_DEFAULT_BUTTON = "defaultButton";

    public Widget render(final UIComponent uiComponent, final Composite parent,
            final WindowContext context) {
        configureShell((WindowComponent) uiComponent, parent.getShell());

        return parent;
    }

    public void renderAfter(final Widget widget, final UIComponent uiComponent,
            final Composite parent, final WindowContext context) {
        setDefaultButton((Shell) widget, uiComponent, context);
    }

    public int getShellStyle(final WindowComponent uiComponent) {
        int minButton = uiComponent.isMinButton() ? SWT.MIN : 0;
        int maxButton = uiComponent.isMaxButton() ? SWT.MAX : 0;
        int closeButton = uiComponent.isCloseButton() ? SWT.CLOSE : 0;
        int resizeButton = uiComponent.isResizable() ? SWT.RESIZE : 0;
        return SWT.TITLE | minButton | maxButton | closeButton | resizeButton;
    }

    protected void configureShell(final WindowComponent window,
            final Shell shell) {
        shell.setText(window.getTitle());

        if ((window.getWidth() != null) && (window.getHeight() != null)) {
            shell.setSize(calcWidth(window), calcHeight(window));
            shell.setLocation(calcX(window), calcY(window));
        }

        String img = window.getImage();
        if (img != null) {
            img = PathUtil.createPath(window.getBasePath(), img);
            shell.setImage(ImageManager.getInstance().getImage(img,
                    Display.getCurrent()));
        }
    }

    protected int calcWidth(final WindowComponent window) {
        return GeometryUtil.calcSize(window.getWidth(), Display.getCurrent()
                .getClientArea().width);
    }

    protected int calcHeight(final WindowComponent window) {
        return GeometryUtil.calcSize(window.getHeight(), Display.getCurrent()
                .getClientArea().height);
    }

    protected int calcX(final WindowComponent window) {
        return GeometryUtil.calcPosition(window.getX(), Display.getCurrent()
                .getClientArea().width, calcWidth(window));
    }

    protected int calcY(final WindowComponent window) {
        return GeometryUtil.calcPosition(window.getY(), Display.getCurrent()
                .getClientArea().height, calcHeight(window));
    }

    protected void setDefaultButton(final Shell shell,
            final UIComponent uiComponent, final WindowContext context) {
        Widget defaultButton = context.getComponent(uiComponent
                .getPropertyValue(ATTR_DEFAULT_BUTTON));
        if (defaultButton instanceof Button) {
            shell.setDefaultButton((Button) defaultButton);
        }

    }

    public String getRendererName() {
        return "window";
    }

}
