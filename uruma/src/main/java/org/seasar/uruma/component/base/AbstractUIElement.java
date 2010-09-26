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
package org.seasar.uruma.component.base;

import java.net.URL;

import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.UIElementVisitor;

/**
 * {@link UIElement} を表す基底クラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public abstract class AbstractUIElement implements UIElement {
    private URL url;

    private URL parentUrl;

    private String location;

    /*
     * @see org.seasar.uruma.component.UIElement#getParentURL()
     */
    public URL getParentURL() {
        return this.parentUrl;
    }

    /*
     * @see org.seasar.uruma.component.UIElement#getURL()
     */
    public URL getURL() {
        return this.url;
    }

    /*
     * @see org.seasar.uruma.component.UIElement#setParentURL(java.net.URL)
     */
    public void setParentURL(final URL parentUrl) {
        this.parentUrl = parentUrl;
    }

    /*
     * @see org.seasar.uruma.component.UIElement#setURL(java.net.URL)
     */
    public void setURL(final URL url) {
        this.url = url;
    }

    /*
     * @see org.seasar.uruma.component.UIElement#getLocation()
     */
    public String getLocation() {
        return this.location;
    }

    /*
     * @see org.seasar.uruma.component.UIElement#setLocation(java.lang.String)
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /*
     * @see org.seasar.uruma.component.UIElementVisitorAcceptor#accept(org.seasar.uruma.component.UIElementVisitor)
     */
    public void accept(final UIElementVisitor visitor) {
        visitor.visit(this);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return url != null ? url.toString() : "";
    }
}
