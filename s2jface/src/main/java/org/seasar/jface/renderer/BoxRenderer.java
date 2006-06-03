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

import org.eclipse.swt.widgets.Composite;
import org.seasar.jface.component.Inheritance;
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * <code>Composite</code>のレンダリングを行うクラスです。
 * 
 * @author y-komori
 * @see org.eclipse.swt.widgets.Composite
 * 
 */
public class BoxRenderer extends AbstractCompositeRenderer<Composite> {

    public String getRendererName() {
        return "box";
    }

    @Override
    protected void doRenderComposite(final Composite composite,
            final CompositeComponent compositeComponent) {
        // 追加のレンダリングは行わない
    }

    @Override
    protected Class<Composite> getControlType() {
        return Composite.class;
    }

    /**
     * <p>
     * <code>backgroundColor</code> プロパティは <code>Inheritance.DECENDANT</code>
     * を返します。
     * </p>
     * <p>
     * それ以外については、<code>Inheritance.DESCENDANT_ONLY</code> を返します。
     * </p>
     * 
     * @see org.seasar.jface.renderer.Renderer#getDefaultInheritance()
     */
    @Override
    public Inheritance getDefaultInheritance(final String propertyName) {
        if (ControlComponent.BACKGROUND_COLOR_PROP.equals(propertyName)) {
            return Inheritance.DESCENDANT;
        } else {
            return Inheritance.DESCENDANT_ONLY;
        }
    }
}
