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
package org.seasar.uruma.util.resource.impl;

import org.seasar.uruma.util.resource.ResourceFilter;

/**
 * 任意の拡張子にマッチする {@link ResourceFilter} です。<br />
 * 
 * @author y-komori
 */
public class ExtResourceFilter implements ResourceFilter {
    private static final String PERIOD = ".";

    private static final String NULL_STRING = "";

    private String suffix;

    private String ext;

    private String match = NULL_STRING;

    /**
     * {@link ExtResourceFilter} を構築します。<br />
     * <code>ext</code> で指定した拡張子にマッチします。<br />
     * たとえば、<code>xml</code> という文字列を指定した場合、<code>.xml</code>
     * で終わるパスにマッチします。<br />
     * 
     * @param ext
     *            拡張子
     */
    public ExtResourceFilter(final String ext) {
        this.ext = ext;

        createMatchString();
    }

    /**
     * {@link ExtResourceFilter} を構築します。<br />
     * ファイル名部分が <code>suffix</code> で終了し、拡張子 <code>ext</code> をもつパスにマッチします。<br />
     * たとえば、 <code>suffix</code> に <code>View</code>、<code>ext</code> に
     * <code>xml</code> を指定すると、 <code>*View.xml</code> にマッチします。
     * 
     * @param suffix
     *            ファイル名サフィックス
     * @param ext
     *            拡張子
     */
    public ExtResourceFilter(final String suffix, final String ext) {
        this.ext = ext;
        this.suffix = suffix;
        createMatchString();
    }

    private void createMatchString() {
        StringBuilder builder = new StringBuilder();
        if (suffix != null) {
            builder.append(suffix);
            builder.append(PERIOD);
        }

        if (ext != null) {
            builder.append(ext);
        }

        match = builder.toString();
    }

    /*
     * @see org.seasar.uruma.util.resource.ResourceFilter#accept(java.lang.String)
     */
    public boolean accept(final String path) {
        if (path != null) {
            return path.endsWith(match) ? true : false;
        } else {
            return false;
        }
    }
}
