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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.CommonAttributes;
import org.seasar.jface.component.LayoutDataInfo;
import org.seasar.jface.component.LayoutInfo;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UICompositeComponent;

/**
 * @author y-komori
 */
public class CompositeComponent extends ControlComponent implements
        UICompositeComponent {
    private List<UIComponent> children = new ArrayList<UIComponent>();

    private LayoutInfo layoutInfo;

    private LayoutDataInfo childLayoutDataInfo;

    private CommonAttributes commonAttributes;

    @Override
    public void render(Composite parent, WindowContext context) {
        super.render(parent, context);
        renderChild((Composite) getWidget(), context);
    }

    protected void renderChild(final Composite parent,
            final WindowContext context) {
        for (UIComponent child : children) {
            child.render(parent, context);
        }
    }

    public void addChild(final UIComponent child) {
        this.children.add(child);
        child.setParent(this);
    }

    public List<UIComponent> getChildren() {
        return children;
    }

    public LayoutInfo getLayoutInfo() {
        return this.layoutInfo;
    }

    public void setLayoutInfo(LayoutInfo layoutInfo) {
        this.layoutInfo = layoutInfo;
    }

    public LayoutDataInfo getChildLayoutDataInfo() {
        return this.childLayoutDataInfo;
    }

    public void setChildLayoutDataInfo(LayoutDataInfo childLayoutDataInfo) {
        this.childLayoutDataInfo = childLayoutDataInfo;
    }

    public CommonAttributes getCommonAttributes() {
        return commonAttributes;
    }

    public void setCommonAttributes(CommonAttributes commonAttributes) {
        this.commonAttributes = commonAttributes;
    }
}
