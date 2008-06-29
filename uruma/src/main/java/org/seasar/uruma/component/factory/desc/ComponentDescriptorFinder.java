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
package org.seasar.uruma.component.factory.desc;

import java.io.InputStream;
import java.net.URL;

import org.seasar.uruma.util.resource.ResourceHandler;
import org.seasar.uruma.util.resource.ResourceTraverser;
import org.seasar.uruma.util.resource.ResourceTraverserFactory;
import org.seasar.uruma.util.resource.impl.ExtResourceFilter;

/**
 * コンポーネント・ディスクリプタを検索するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ComponentDescriptorFinder implements ResourceHandler {
    public void findComponentDescriptors(final ClassLoader loader) {
        URL root = loader.getResource("");
        URL origin = loader.getResource("components");
        ResourceTraverser traverser = ResourceTraverserFactory
                .getResourceTraverser(origin);
        ExtResourceFilter filter = new ExtResourceFilter("ucd");
        traverser.traverse(root, origin, this, filter);
    }

    public void handle(final String rootPath, final String path,
            final InputStream is) {
        System.out.println(rootPath + " " + path);
    }

    public static void main(final String[] args) {
        ComponentDescriptorFinder finder = new ComponentDescriptorFinder();
        finder.findComponentDescriptors(finder.getClass().getClassLoader());
    }

}
