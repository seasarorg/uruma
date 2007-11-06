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
package org.seasar.uruma.component.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link UIComponentContainer} のための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractUIContainer extends AbstractUIComponent implements
        UIComponentContainer {
    protected List<UIElement> children = new ArrayList<UIElement>();

    /*
     * @see org.seasar.uruma.component.UIElementContainer#addChild(org.seasar.uruma.component.UIElement)
     */
    public void addChild(final UIElement child) {
        AssertionUtil.assertNotNull("child", child);
        children.add(child);

        if (child instanceof UIComponent) {
            ((UIComponent) child).setParent(this);
        }
    }

    /*
     * @see org.seasar.uruma.component.UIElementContainer#getChildren()
     */
    public List<UIElement> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractUIComponent#doPreRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.WindowContext)
     */
    @Override
    protected void doPreRender(final WidgetHandle parent,
            final WindowContext context) {
        for (UIElement child : children) {
            if (child instanceof UIComponent) {
                ((UIComponent) child).preRender(parent, context);
            }
        }
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractUIComponent#doRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRender(final WidgetHandle parent, final PartContext context) {
        for (UIElement child : children) {
            if (child instanceof UIComponent) {
                ((UIComponent) child).render(parent, context);
            }
        }
    }

}
