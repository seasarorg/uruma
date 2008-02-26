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
package org.seasar.uruma.component.base;

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.UIElementContainer;
import org.seasar.uruma.component.UIElementVisitor;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link UIElementContainer} のための基底クラスです。<br />
 * 
 * @author y-komori
 */
public class AbstractUIElementContainer extends AbstractUIElement implements
        UIElementContainer {
    private List<UIElement> children = new ArrayList<UIElement>();

    /*
     * @see org.seasar.uruma.component.UIElementContainer#addChild(org.seasar.uruma.component.UIElement)
     */
    public void addChild(final UIElement child) {
        AssertionUtil.assertNotNull("child", child);
        this.children.add(child);
    }

    /*
     * @see org.seasar.uruma.component.UIElementContainer#getChildren()
     */
    public List<UIElement> getChildren() {
        return children;
    }

    /*
     * @see org.seasar.uruma.component.base.AbstractUIElement#accept(org.seasar.uruma.component.UIElementVisitor)
     */
    @Override
    public void accept(final UIElementVisitor visitor) {
        visitor.visit(this);
    }
}
