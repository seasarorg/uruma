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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;

/**
 * @author y-komori
 */
public class TreeItemComponent extends AbstractUIComponent {
    @ComponentAttribute(targetType = TargetType.NONE)
    // TreeItem には複数の setText() メソッドが存在するため、
    // 独自にレンダリングを行う
    private String text;

    @ComponentAttribute(conversionType = ConversionType.IMAGE)
    private String image;

    @ComponentAttribute(conversionType = ConversionType.COLOR)
    private String background;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private boolean checked;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private boolean expanded;

    @ComponentAttribute(conversionType = ConversionType.INT)
    private int fontHeight;

    @ComponentAttribute(conversionType = ConversionType.STRING)
    private String fontName;

    @ComponentAttribute(conversionType = ConversionType.STRING)
    private String fontStyle;

    @ComponentAttribute(conversionType = ConversionType.COLOR)
    private String foreground;

    @ComponentAttribute(conversionType = ConversionType.BOOLEAN)
    private boolean grayed;

    private TreeItemComponent parent;

    private List<TreeItemComponent> children = new ArrayList<TreeItemComponent>();

    @Override
    protected void doRender(Widget parent, WindowContext context) {
        renderChildren(getWidget(), context);
    }

    protected void renderChildren(Widget parent, WindowContext context) {
        for (TreeItemComponent child : children) {
            child.render(parent, context);
        }
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean getChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getExpanded() {
        return this.expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getFontHeight() {
        return this.fontHeight;
    }

    public void setFontHeight(int fontHeight) {
        this.fontHeight = fontHeight;
    }

    public String getFontName() {
        return this.fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontStyle() {
        return this.fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getForeground() {
        return this.foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

    public boolean getGrayed() {
        return this.grayed;
    }

    public void setGrayed(boolean grayed) {
        this.grayed = grayed;
    }

    public TreeItemComponent getParentTreeItem() {
        return this.parent;
    }

    public void setParentTreeItem(TreeItemComponent parent) {
        this.parent = parent;
    }

    public void addTreeItem(TreeItemComponent treeItem) {
        children.add(treeItem);
    }

    public List<TreeItemComponent> getTreeItems() {
        return this.children;
    }
}
