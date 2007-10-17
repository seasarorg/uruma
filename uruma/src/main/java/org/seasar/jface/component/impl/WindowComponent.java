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
package org.seasar.jface.component.impl;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;

/**
 * <code>Window</code> のコンポーネント情報を保持するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class WindowComponent extends CompositeComponent {

    @ComponentAttribute(targetType = TargetType.NONE)
    private String title;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String minimumWidth;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String minimumHeight;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String width;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String height;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String x = "center";

    @ComponentAttribute(targetType = TargetType.NONE)
    private String y = "middle";

    @ComponentAttribute(targetType = TargetType.NONE)
    private String image;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String defaultButtonId;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String defaultFocusId;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String statusLine;

    @Override
    public void render(Widget parent, WindowContext context) {
        if (parent instanceof Shell) {
            context.putComponent(WindowContext.SHELL_ID, parent);
        }

        super.render(parent, context);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMinimumHeight() {
        return this.minimumHeight;
    }

    public void setMinimumHeight(String minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    public String getMinimumWidth() {
        return this.minimumWidth;
    }

    public void setMinimumWidth(String minimumWidth) {
        this.minimumWidth = minimumWidth;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getX() {
        return this.x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return this.y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getDefaultButtonId() {
        return this.defaultButtonId;
    }

    public void setDefaultButtonId(String defaultButtonId) {
        this.defaultButtonId = defaultButtonId;
    }

    public String getDefaultFocusId() {
        return this.defaultFocusId;
    }

    public void setDefaultFocusId(String defaultFocusId) {
        this.defaultFocusId = defaultFocusId;
    }

    public String getStatusLine() {
        return this.statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }
}
