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
package org.seasar.uruma.util.resource;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.util.resource.impl.BundleResourceTraverser;
import org.seasar.uruma.util.resource.impl.FileResourceTraverser;

/**
 * {@link ResourceTraverser} のためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ResourceTraverserFactory {
    private static final List<ResourceTraverser> traversers = new ArrayList<ResourceTraverser>();

    static {
        addResourceTraverser(new FileResourceTraverser());
        addResourceTraverser(new BundleResourceTraverser());
    }

    private ResourceTraverserFactory() {

    }

    /**
     * {@link ResourceTraverser} を登録します。<br /> 同じプロトコルの
     * {@link ResourceTraverser} が既に登録されている場合、上書きします。<br />
     * 
     * @param traverser
     *            {@link ResourceTraverser}
     */
    public static void addResourceTraverser(final ResourceTraverser traverser) {
        ResourceTraverser known = getResourceTraverser(traverser.getProtocol());
        if (known != null) {
            traversers.remove(known);
        }
        traversers.add(traverser);
    }

    /**
     * 指定されたプロトコルに対応した {@link ResourceTraverser} を取得します。<br />
     * 
     * @param protocol
     *            プロトコル
     * @return {@link ResourceTraverser} オブジェクト。プロトコルに対応するものが存在しない場合は
     *         <code>null</code>。
     */
    public static ResourceTraverser getResourceTraverser(final String protocol) {
        if (protocol == null) {
            return null;
        }
        int size = traversers.size();
        for (int i = 0; i < size; i++) {
            ResourceTraverser traverser = traversers.get(i);
            if (protocol.equals(traverser.getProtocol())) {
                return traverser;
            }
        }
        return null;
    }

    /**
     * 指定された URL のプロトコルに対応した {@link ResourceTraverser} を取得します。<br />
     * 
     * @param url
     *            URL
     * @return {@link ResourceTraverser} オブジェクト。プロトコルに対応するものが存在しない場合は
     *         <code>null</code>。
     */
    public static ResourceTraverser getResourceTraverser(final URL url) {
        if (url == null) {
            return null;
        } else {
            return getResourceTraverser(url.getProtocol());
        }
    }
}
