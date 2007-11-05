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
import java.util.List;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIContainer;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link UIContainer} の子要素を表す基底クラスです。<br />
 * 
 * @author bskuroneko
 */
public abstract class AbstractUIContainerItemComponent extends
        AbstractItemComponent implements UIContainer {

    private List<UIComponent> children = new ArrayList<UIComponent>();

    /*
     * @see org.seasar.uruma.component.UIContainer#addChild(org.seasar.uruma.component.UIComponent)
     */
    public void addChild(final UIComponent child) {
        children.add(child);
        child.setParent(getParent());
    }

    /*
     * @see org.seasar.uruma.component.UIContainer#getChildren()
     */
    public List<UIComponent> getChildren() {
        return children;
    }

    /**
     * 子コンポーネントを取得します。<br />
     * 子コンポーネントが複数存在する場合、最初の一個を返します。
     * 
     * @return 子コンポーネント。存在しない場合は <code>null</code>。
     */
    public UIComponent getChild() {
        if (children.size() > 0) {
            return children.get(0);
        } else {
            return null;
        }
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractUIComponent#doRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRender(final WidgetHandle parent, final PartContext context) {
        UIComponent content = getChild();
        if (content != null) {
            WidgetHandle parentHandle = context.getWidgetHandle(getParent()
                    .getId());
            content.render(parentHandle, context);
        }
    }
}
