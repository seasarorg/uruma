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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.StringUtil;

/**
 * パスに関するユーティリティメソッドを提供するクラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$
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
     * 与えられた URL のパスを基準として、{@code relPath} で与えられる相対パスを持つ URL を生成します。<br />
     * 本メソッドでは、相対パスの標準化は行いません。
     * 
     * @param parentUrl
     *        親 URL
     * @param relPath
     *        相対パス
     * @return 生成した URL
     */
    public static URL createURL(final URL parentUrl, final String relPath) {
        return createURL(parentUrl, relPath, false);
    }

    /**
     * 与えられた URL のパスを基準として、{@code relPath} で与えられる相対パスを持つ URL を生成します。<br />
     * 
     * @param parentUrl
     *        親 URL
     * @param relPath
     *        相対パス
     * @param normalizeRelPath
     *        {@code true} の場合、{@link #normalizeRelativePath(String)}
     *        によって相対パスを標準化します
     * @return 生成した URL
     */
    public static URL createURL(final URL parentUrl, final String relPath,
            final boolean normalizeRelPath) {
        String path = parentUrl.toExternalForm();
        if (!path.endsWith("/")) {
            path += "/";
        }
        path += relPath;

        if (normalizeRelPath) {
            path = normalizeRelativePath(path);
        }
        try {
            return new URL(path);
        } catch (MalformedURLException ex) {
            throw new IORuntimeException(ex);
        }
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
     * 指定された URL の親を表す URL を返します。<br />
     * 
     * @param url
     *        URL
     * @return 親を表す URL。引数が {@code null} の場合は {@code null}
     */
    public static URL getParentURL(final URL url) {
        if (url == null) {
            return null;
        }

        String externalForm = url.toExternalForm();
        try {
            return new URL(getParent(externalForm));
        } catch (MalformedURLException ex) {
            throw new IORuntimeException(ex);
        }
    }

    /**
     * 指定されたクラスの親を表す URL を返します。<br />
     * 
     * @param clazz
     *        クラスオブジェクト
     * @return 親を表す URL。引数が {@code null} の場合は {@code null}
     */
    public static URL getParentURL(final Class<?> clazz) {
        if (clazz == null) {
            return null;
        }

        String classFilePath = getClassFilePath(clazz);
        URL url = ResourceUtil.getResourceNoException(classFilePath);
        if (url != null) {
            return getParentURL(url);
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
     * @param refClass
     *        パッケージからパスを生成するための参照用クラスオブジェクト
     * @return 生成したパス。{@code clazz} が {@code null} の場合は空文字列
     */
    public static String getPackagePath(final Class<?> refClass) {
        if (refClass == null) {
            return "";
        }
        return refClass.getPackage().getName().replace('.', '/');
    }

    /**
     * 与えられたクラスオブジェクトのパッケージを親ディレクトリとするファイル名のパスを生成します。<br />
     * 
     * @param refClass
     *        親ディレクトリを生成するための参照用クラスオブジェクト
     * @param fileName
     *        ファイル名
     * @return 生成したパス
     */
    public static String getPath(final Class<?> refClass, final String fileName) {
        String path = getPackagePath(refClass);
        if (!("".equals(path))) {
            path += "/";
        }

        if (fileName != null) {
            path += fileName;
        }
        return path;
    }

    /**
     * 与えられたパスのうち、相対的に参照されている部分を相対パスを含まないパスに変換します。<br />
     * <p>
     * 本メソッドでは、以下のような処理を行います。
     * </p>
     * <dl>
     * <dt>相対パスの処理
     * <dd>たとえば、{@code /abc/def/../ghi} というパスは {@code /abc/ghi} というパスに変換します。
     * <dt>カレントパスの圧縮
     * <dd>たとえば、{@code abc/./def} のように途中にカレントディレクトリが含まれるパスは、圧縮して {@code abc/def}
     * のようにします。
     * <dt>重複したパス区切り文字の圧縮
     * <dd>たとえば、{@code abc//def} のようにパス区切り文字が重複している場合、圧縮して {@code abc/def}
     * のようにします。
     * </dl>
     * 
     * @param path
     *        変換対象パス
     * @return 変換結果
     */
    public static String normalizeRelativePath(final String path) {
        LinkedList<String> pathStack = new LinkedList<String>();
        String[] pathElements = path.split("/");
        for (String pathElement : pathElements) {
            if (".".equals(pathElement)) {
                continue;
            } else if ("..".equals(pathElement) && pathStack.size() > 0) {
                pathStack.removeLast();
            } else {
                pathStack.addLast(pathElement);
            }
        }

        StringBuilder buf = new StringBuilder();
        for (String pathElement : pathStack) {
            buf.append(pathElement);
            buf.append("/");
        }

        int length = buf.length();
        if (length > 0 && !path.endsWith("/")) {
            length--;
        }
        return buf.substring(0, length);
    }

    /**
     * 与えられたクラスオブジェクトに対応する {@code class} ファイルのパスを返します。<br />
     * <p>
     * たとえば、{@code org.seasar.uruma.util.PathUtil} クラスに対して {@code
     * org/seasar/uruma/util/PathUtil.class} という文字列を返します。<br />
     * </p>
     * 
     * @param clazz
     *        クラスオブジェクト
     * @return パス。引数が {@code null} の場合は空文字列。
     */
    public static String getClassFilePath(final Class<?> clazz) {
        if (clazz == null) {
            return "";
        }
        return clazz.getName().replace('.', '/') + ".class";
    }

    /**
     * 与えられたパスを連結します。<br />
     * 連結対象パス 1 の最後と連結対象パス 2 の最初にパス区切り文字があるかどうかにかかわらず、連結部のパス区切り文字は必ず 1 つになります。<br />
     * 引数の双方が {@code null} の場合、{@code null} を返します。<br />
     * 引数の双方が空文字列の場合、空文字列を返します。<br />
     * 引数の片方が {@code null} または空文字列の場合、もう一方の引数をそのまま返します。<br />
     * 
     * @param path1
     *        連結対象パス1
     * @param path2
     *        連結対象パス2
     * @return 連結結果
     */
    public static String concat(final String path1, final String path2) {
        if (path1 == null && path2 == null) {
            return null;
        }
        if ("".equals(path1) && "".equals(path2)) {
            return "";
        }
        if (path1 == null || "".equals(path1)) {
            return path2;
        }
        if (path2 == null || "".equals(path2)) {
            return path1;
        }

        StringBuilder result = new StringBuilder(path1.length() + path2.length() + 1);
        result.append(path1);
        if (!path1.endsWith("/")) {
            result.append("/");
        }

        if (path2.length() > 0) {
            if (path2.startsWith("/")) {
                result.append(path2.substring(1));
            } else {
                result.append(path2);
            }
        }
        return result.toString();
    }
}
