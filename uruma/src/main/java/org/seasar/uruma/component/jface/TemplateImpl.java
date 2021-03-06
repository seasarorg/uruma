/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.component.jface;

import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.component.UIElementVisitor;
import org.seasar.uruma.component.base.AbstractUIElement;

/**
 * {@link Template} の実装クラスです。<br />
 * 
 * @author y-komori
 */
@ComponentElement("template")
public class TemplateImpl extends AbstractUIElement implements Template {
    private UIComponentContainer rootComponent;

    private String extendsPath;

    /*
     * @see org.seasar.uruma.component.Template#getRootComponent()
     */
    public UIComponentContainer getRootComponent() {
        return this.rootComponent;
    }

    /*
     * @see org.seasar.uruma.component.Template#setRootComponent(org.seasar.uruma.component.UIContainer)
     */
    public void setRootComponent(final UIComponentContainer rootComponent) {
        this.rootComponent = rootComponent;
    }

    /*
     * @see org.seasar.uruma.component.Template#getExtends()
     */
    public String getExtends() {
        return this.extendsPath;
    }

    /*
     * @see org.seasar.uruma.component.Template#setExtends(java.lang.String)
     */
    public void setExtends(final String extendsPath) {
        this.extendsPath = extendsPath;
    }

    /*
     * @see org.seasar.uruma.component.base.AbstractUIElement#accept(org.seasar.uruma.component.UIElementVisitor)
     */
    @Override
    public void accept(final UIElementVisitor visitor) {
        visitor.visit(this);
    }
}
