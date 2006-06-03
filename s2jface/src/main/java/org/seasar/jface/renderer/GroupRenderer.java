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

import org.eclipse.swt.widgets.Group;
import org.seasar.jface.component.Inheritance;
import org.seasar.jface.component.impl.CompositeComponent;

/**
 * <code>Group</code>のレンダリングを行うクラスです。</br>
 * 
 * @author y-komori
 * 
 */
public class GroupRenderer extends AbstractCompositeRenderer<Group> {
    public static final String TEXT_PROP = "text";

    @Override
    protected void doRenderComposite(final Group group,
            final CompositeComponent component) {
        renderText(group, component);
    }

    public String getRendererName() {
        return "group";
    }

    protected void renderText(Group group, CompositeComponent compositeComponent) {
        String text = compositeComponent.getPropertyValue(TEXT_PROP);
        if (text != null) {
            group.setText(text);
        }
    }

    @Override
    protected Class<Group> getControlType() {
        return Group.class;
    }

    /**
     * <p>
     * <code>text</code> プロパティは <code>Inheritance.NONE</code> を返します。
     * </p>
     * <p>
     * それ以外については、<code>Inheritance.DESCENDANT_ONLY</code> を返します。
     * </p>
     * 
     * @see org.seasar.jface.renderer.Renderer#getDefaultInheritance()
     */
    @Override
    public Inheritance getDefaultInheritance(final String propertyName) {
        if (TEXT_PROP.equals(propertyName)) {
            return Inheritance.NONE;
        } else {
            return Inheritance.DESCENDANT_ONLY;
        }
    }
}
