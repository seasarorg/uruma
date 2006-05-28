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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 * 
 */
public class ControlComponent extends UIComponentBase {
    public static final int NULL = -1;

    private String x;

    private String y;

    private int order = NULL;

    private String width;

    private String height;

    private String style;

    private String foregroundColor;

    private String backgroundColor;

    private boolean enabled = true;

    private boolean visible = true;

    private String text;

    private String textAlign;

    private String font;

    private int fontSize = NULL;

    private String fontStyle;

    private String toolTip;

    private String accessKey;

    private int focusOrder;

    private Map<String, String> layoutData = new HashMap<String, String>();

    private String action;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getFocusOrder() {
        return focusOrder;
    }

    public void setFocusOrder(int focusOrder) {
        this.focusOrder = focusOrder;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void addLayoutData(final String name, final String value) {
        layoutData.put(name, value);
    }

    public String getLayoutData(final String name) {
        return layoutData.get(name);
    }

    public int getLayoutDataNum() {
        return layoutData.size();
    }

    public Iterator<String> layoutDataNameIterator() {
        return layoutData.keySet().iterator();
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void render(final Composite parent, final WindowContext context) {
        AssertionUtil.assertNotNull("renderer", renderer);
        Widget widget = renderer.render(this, parent, context);

        if (getId() != null) {
            context.putComponent(getId(), widget);
        }
    }

    @Override
    public void setParent(UIComponent parent) {
        super.setParent(parent);
        inheritAttributes();
    }

    /**
     * 荀・ontrol・vヒ・P舊･・・雌・w㈲冷 w痃蝉殼痃#・障q8・q・
     */
    protected void inheritAttributes() {
        if (parent instanceof ControlComponent) {
            ControlComponent parentControl = (ControlComponent) parent;

            String foregroundColor = parentControl.getForegroundColor();
            if (foregroundColor != null) {
                this.foregroundColor = foregroundColor;
            }

            String backgroundColor = parentControl.getBackgroundColor();
            if (backgroundColor != null) {
                this.backgroundColor = backgroundColor;
            }

            String textAlign = parentControl.getTextAlign();
            if (textAlign != null) {
                this.textAlign = textAlign;
            }

            String font = parentControl.getFont();
            if (font != null) {
                this.font = font;
            }

            int fontSize = parentControl.getFontSize();
            if (fontSize != NULL) {
                this.fontSize = fontSize;
            }

            String fontStyle = parentControl.getFontStyle();
            if (fontStyle != null) {
                this.fontStyle = fontStyle;
            }
        }

        if (parent instanceof UIComponentBase) {
            UIComponentBase parentBase = (UIComponentBase) parent;
            attribute.putAll(parentBase.getAttributes());
        }
    }
}
