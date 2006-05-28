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
package org.seasar.jface.component.impl;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;

/**
 * @author y-komori
 * 
 */
public class WindowComponent extends AbstractCompositeComponent {
    private String title = "";

    private String image;

    private String width;

    private String height;

    private String x = "center";

    private String y = "middle";

    private boolean toolBar;

    private boolean menuBar;

    private boolean coolBar;

    private boolean statusLine;

    private boolean minButton = true;

    private boolean maxButton = true;

    private boolean closeButton = true;

    private boolean resizable = true;

    private boolean saveState;

    public boolean isCoolBar() {
        return coolBar;
    }

    public void setCoolBar(boolean coolBar) {
        this.coolBar = coolBar;
    }

    public boolean isCloseButton() {
        return closeButton;
    }

    public void setCloseButton(boolean closeButton) {
        this.closeButton = closeButton;
    }

    public boolean isMaxButton() {
        return maxButton;
    }

    public void setMaxButton(boolean maxButton) {
        this.maxButton = maxButton;
    }

    public boolean isMinButton() {
        return minButton;
    }

    public void setMinButton(boolean minButton) {
        this.minButton = minButton;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String img) {
        this.image = img;
    }

    public boolean isMenuBar() {
        return menuBar;
    }

    public void setMenuBar(boolean menuBar) {
        this.menuBar = menuBar;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isStatusLine() {
        return statusLine;
    }

    public void setStatusLine(boolean statusLine) {
        this.statusLine = statusLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isToolBar() {
        return toolBar;
    }

    public void setToolBar(boolean toolBar) {
        this.toolBar = toolBar;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public boolean isSaveState() {
        return saveState;
    }

    public void setSaveState(boolean saveState) {
        this.saveState = saveState;
    }

    public void render(final Composite parent, final WindowContext context) {
        Widget widget = null;
        if (renderer != null) {
            widget = renderer.render(this, parent, context);
            setWidget(widget);

            if (getId() != null) {
                context.putComponent(getId(), widget);
            }
        }

        renderChild((Composite) widget, context);
    }

}
