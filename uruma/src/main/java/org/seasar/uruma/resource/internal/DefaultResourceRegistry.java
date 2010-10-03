/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
package org.seasar.uruma.resource.internal;

import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.seasar.uruma.resource.ResourceRegistry;

/**
 * {@link ResourceRegistry} の実装クラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class DefaultResourceRegistry implements ResourceRegistry {

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#getImage(java.lang.String)
     */
    public Image getImage(final String key) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#getImage(java.lang.String, java.net.URL)
     */
    public Image getImage(final String path, final URL parentUrl) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#getImageDescriptor(java.lang.String, java.net.URL)
     */
    public ImageDescriptor getImageDescriptor(final String path, final URL parentUrl) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /*
     * @see org.seasar.uruma.resource.ResourceRegistry#putImage(java.lang.String, org.eclipse.jface.resource.ImageDescriptor)
     */
    public void putImage(final String key, final ImageDescriptor descriptor) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /*
     * @see org.seasar.uruma.resource.internal.InternalResourceRegistry#dispose()
     */
    public void dispose() {
        // TODO 自動生成されたメソッド・スタブ

    }

    /*
     * @see org.seasar.uruma.resource.internal.InternalResourceRegistry#init(org.eclipse.swt.widgets.Display)
     */
    public void init(final Display display) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /*
     * @see org.seasar.uruma.resource.internal.InternalResourceRegistry#loadImages(java.lang.String)
     */
    public void loadImages(final String bundleBaseName) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /*
     * @see org.seasar.uruma.resource.internal.InternalResourceRegistry#loadImages(java.util.ResourceBundle)
     */
    public void loadImages(final ResourceBundle resourceBundle) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
