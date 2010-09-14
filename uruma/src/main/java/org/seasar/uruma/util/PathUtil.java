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
package org.seasar.uruma.util;

import org.seasar.framework.util.StringUtil;

/**
 * パスに関するユーティリティメソッドを提供するクラスです。<br />
 * 
 * @author y-komori
 */
public class PathUtil {
    private PathUtil() {

    }

    private static final String YEN_SIGN = "\\";

    private static final String SLASH = "/";

    private static final char SLASH_CHAR = '/';

    private static final String PERIOD = ".";

    private static final String NULL_STRING = "";

    /**
     * 与えられた基準パスと相対パスから絶対パスを生成します。</br>
     * <ul>
     * <li>パス中の {@code \} はすべて {@code /} に変換します。
     * <li>基本動作として {@code basePath} と {@code relPath} を連結した文字列を返します。
     * <li>この際、{@code basePath} が {@code /} で終了していない場合、{@code /} を付加します。
     * <li>{@code relPath} が {@code /} から始まる場合、{@code relPath} が絶対パスを表していると見なして、
     * {@code basePath} は無視されます。
     * <li>{@code basePath} が {@code relPath} の先頭に含まれる場合、 {@code basePath}
     * は無視されます。
     * </ul>
     * 
     * @param basePath
     *        基準パス
     * @param relPath
     *        相対パス
     * @return 生成したパス
     */
    public static String createPath(String basePath, String relPath) {
        basePath = replaceSeparator(basePath);
        relPath = replaceSeparator(relPath);
        String path = "";
        if (relPath.charAt(0) == SLASH_CHAR) {
            basePath = NULL_STRING;
        }
        if (!StringUtil.isEmpty(basePath)) {
            if (!relPath.startsWith(basePath)) {
                path += basePath;
                if (!path.endsWith(SLASH)) {
                    path += SLASH;
                }
            }
        }
        path += relPath;
        return path;
    }

    /**
     * セパレータを「\」から「/」へ変換します。<br />
     * 
     * @param path
     *        パス
     * @return 変換後のパス
     */
    public static String replaceSeparator(final String path) {
        if (path != null) {
            return StringUtil.replace(path, YEN_SIGN, SLASH);
        } else {
            return NULL_STRING;
        }
    }

    /**
     * 与えられたパスのベースパスを基準とした相対パスを返します。<br />
     * 
     * @param basePath
     *        ベースパス
     * @param path
     *        パス
     */
    public static String getRelativePath(final String basePath, final String path) {
        AssertionUtil.assertNotNull("basePath", basePath);
        AssertionUtil.assertNotNull("path", path);

        if (path.startsWith(basePath)) {
            return path.substring(basePath.length(), path.length());
        } else {
            return path;
        }
    }

    /**
     * 指定されたパスの親ディレクトリ部分を返します。<br />
     * セパレータは \ と / の両方を認識します。<br />
     * 混在している場合は、より後ろの方を区切りとします。<br />
     * 
     * @param path
     *        パス
     * @return 親ディレクトリ部分
     */
    public static String getParent(final String path) {
        if (path != null) {
            int slashIndex = path.lastIndexOf(SLASH);
            int yenIndex = path.lastIndexOf(YEN_SIGN);
            int pos = (slashIndex > yenIndex) ? slashIndex : yenIndex;
            if (pos >= 0) {
                return path.substring(0, pos);
            } else {
                return path;
            }
        } else {
            return null;
        }
    }

    /**
     * 指定されたパスのファイル名部分を返します。<br />
     * セパレータは \ と / の両方を認識します。<br />
     * 混在している場合は、より後ろの方を区切りとします。<br />
     * 
     * @param path
     *        パス
     * @return ファイル名部分
     */
    public static String getFileName(final String path) {
        if (path != null) {
            int slashIndex = path.lastIndexOf(SLASH);
            int yenIndex = path.lastIndexOf(YEN_SIGN);
            int pos = (slashIndex > yenIndex) ? slashIndex : yenIndex;
            return path.substring(pos + 1, path.length());
        } else {
            return null;
        }
    }

    /**
     * ファイル名から拡張子を除いた部分を返します。<br />
     * 
     * @param fileName
     *        フィル名
     * @return 拡張子を除いた部分
     */
    public static String getBaseName(final String fileName) {
        if (fileName != null) {
            return StringUtil.substringFromLast(fileName, PERIOD);
        } else {
            return null;
        }
    }

    /**
     * パスの拡張子の部分(最後に登場するピリオド以降)を返します。<br />
     * 
     * @param path
     *        パス
     * @return 拡張子
     */
    public static String getExt(final String path) {
        if (path != null) {
            if (path.indexOf(PERIOD) > 0) {
                return StringUtil.substringToLast(path, PERIOD);
            } else {
                return NULL_STRING;
            }
        } else {
            return null;
        }
    }

    /**
     * 与えられたクラスオブジェクトのパッケージに対応するパスを生成します。<br />
     * 
     * @param clazz
     *        パッケージからパスを生成するクラスオブジェクト
     * @return 生成したパス。{@code clazz} が {@code null} の場合は空文字列
     */
    public static String getPackagePath(final Class<?> clazz) {
        if (clazz == null) {
            return "";
        }
        return clazz.getPackage().getName().replace('.', '/');
    }
}
