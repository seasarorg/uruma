/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
     * <li>基本動作として <code>basePath</code> と <code>relPath</code>
     * を連結した文字列を返します。
     * <li>この際、<code>basePath</code> が <code>/</code> で終了していない場合、<code>/</code>
     * を付加します。
     * <li><code>relPath</code> が <code>/</code> から始まる場合、<code>relPath</code>
     * が絶対パスを表していると見なして、<code>basePath</code> は無視されます。
     * <li><code>basePath</code> が <code>relPath</code> の先頭に含まれる場合、<code>basePath</code>
     * は無視されます。
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
        if (relPath.charAt(0) == '/') {
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
        return path;
    }

    protected static String replaceSeparator(final String path) {
        if (path != null) {
            return StringUtil.replace(path, "\\", "/");
        } else {
            return "";
        }
    }
}
