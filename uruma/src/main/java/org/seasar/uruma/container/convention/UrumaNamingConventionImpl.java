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
package org.seasar.uruma.container.convention;

import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.convention.impl.NamingConventionImpl;
import org.seasar.framework.util.Disposable;

/**
 * {@link NamingConvention}の実装クラスです。
 * 
 * @author y.sugigami
 */
public class UrumaNamingConventionImpl extends NamingConventionImpl implements
        NamingConvention, Disposable {

    /**
     * {@link NamingConventionImpl}を作成します。
     */
    public UrumaNamingConventionImpl() {
        super();
    }

    /**
     * 存在チェッカの配列を作成します。
     * 
     * @param rootPackageName
     *            ルートパッケージ名
     * @return 存在チェッカの配列
     */
    // TODO S2Container の更新に伴い、コンパイルが通らなくなったため暫定コメントアウト
    // @Override
    // @SuppressWarnings("unchecked")
    // protected ExistChecker[] createExistCheckerArray(
    // final String rootPackageName) {
    // if (StringUtil.isEmpty(rootPackageName)) {
    // return new ExistChecker[0];
    // }
    // final String s = rootPackageName.replace('.', '/');
    // final List<ExistChecker> list = new ArrayList<ExistChecker>();
    // for (final Iterator<URL> it = ClassLoaderUtil.getResources(this
    // .getClass(), s); it.hasNext();) {
    // final URL url = it.next();
    // final String protocol = URLUtil.toCanonicalProtocol(url
    // .getProtocol());
    // if ("file".equals(protocol)) {
    // list.add(new FileExistChecker(url));
    // } else if ("jar".equals(protocol)) {
    // list.add(new JarExistChecker(url, rootPackageName));
    // } else if ("zip".equals(protocol)) {
    // list.add(new ZipExistChecker(url, rootPackageName));
    // } else if ("code-source".equals(protocol)) {
    // list.add(new CodeSourceExistChecker(url, rootPackageName));
    // } else if ("bundleresource".equals(protocol)) {
    // list.add(new BundleExistChecker(url));
    // }
    // }
    // return list.toArray(new ExistChecker[list.size()]);
    // }

    /**
     * バンドル用の存在チェッカです。
     * 
     */
    // protected static class BundleExistChecker implements ExistChecker {
    // private File rootFile;
    //
    // /**
    // * インスタンスを作成します。
    // *
    // * @param rootUrl
    // * ルートURL
    // */
    // protected BundleExistChecker(final URL rootUrl) {
    // URL fileLocatorUrl = null;
    // try {
    // fileLocatorUrl = FileLocator.toFileURL(rootUrl);
    // } catch (IOException ex) {
    // throw new IORuntimeException(ex);
    // }
    // if (fileLocatorUrl != null) {
    // rootFile = new File(fileLocatorUrl.getPath());
    // }
    //
    // }
    //
    // public boolean isExist(final String lastClassName) {
    // final File file = new File(rootFile, getPathName(lastClassName));
    // return file.exists();
    // }
    //
    // public void close() {
    // }
    // }

}
