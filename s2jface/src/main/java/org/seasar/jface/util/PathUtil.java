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
package org.seasar.jface.util;

import org.seasar.framework.util.StringUtil;

/**
 * @author y-komori
 * 
 */
public class PathUtil {
    /**
     * 与えられた基準パスと相対パスから絶対パスを生成します。</br>
     * <ul>
     * <li>パス中の <code>\</code> はすべて <code>/</code> に変換します。
     * <li><code>relPath</code> に <code>/</code> が含まれる場合(ただし、<code>relPath</code>
     * が <code>../</code> で始まる場合は除く)、<code>basePath</code>は無視されます。
     * <li><code>basePath</code> が <code>relPath</code> の先頭に含まれる場合、<code>basePath</code>
     * は無視されます。
     * <li><code>basePath</code> が <code>\</code> または <code>/</code>
     * で終わっていない場合、<code>/</code> を付加して <code>relPath</code> と結合します。
     * </ul>
     * 
     * @param basePath
     *            基準パス
     * @param relPath
     *            相対パス
     * @return 生成したパス
     */
    public static String createPath(String basePath, String relPath) {
        basePath = replaceSeparator(basePath);
        relPath = replaceSeparator(relPath);
        String path = "";
        if (relPath.indexOf("/") >= 0 && !relPath.startsWith("../")) {
            basePath = "";
        }
        if (!StringUtil.isEmpty(basePath)) {
            if (!relPath.startsWith(basePath)) {
                path += basePath;
                if (!path.endsWith("/")) {
                    path += "/";
                }
            }
        }
        path += relPath;
        return StringUtil.replace(path, "\\", "/");
    }

    protected static String replaceSeparator(final String path) {
        if (path != null) {
            return StringUtil.replace(path, "\\", "/");
        } else {
            return "";
        }
    }
}
