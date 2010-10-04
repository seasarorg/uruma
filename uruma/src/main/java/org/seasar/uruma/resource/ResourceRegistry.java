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
package org.seasar.uruma.resource;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * 各種リソースを管理するためのインターフェースです。<br />
 * Uruma が管理するリソースは、本インターフェースを経由して取得してください。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public interface ResourceRegistry {
    /**
     * @param key
     * @return
     */
    public Image getImage(String key);

    /**
     * @param path
     * @param parentUrl
     * @return
     */
    public Image getImage(String path, URL parentUrl);

    /**
     * @param key
     * @return
     */
    public ImageDescriptor getImageDescriptor(String key);

    /**
     * @param path
     * @param parentUrl
     * @return
     */
    public ImageDescriptor getImageDescriptor(String path, URL parentUrl);

    /**
     * @param key
     * @param descriptor
     */
    public void putImage(String key, ImageDescriptor descriptor);
}
