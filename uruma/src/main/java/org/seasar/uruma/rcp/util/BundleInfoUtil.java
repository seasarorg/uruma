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
package org.seasar.uruma.rcp.util;

import java.util.Dictionary;

import org.osgi.framework.Bundle;

/**
 * OSGi {@link Bundle} のメタ情報を取得するためのユーティリティクラスです。
 * 
 * @author y-komori
 */
public class BundleInfoUtil {
    /**
     * {@value} を表す定数です。
     */
    public static final String ACTIVATION_POLICY = "Bundle-ActivationPolicy";

    /**
     * {@value} を表す定数です。
     */
    public static final String ACTIVATOR = "Bundle-Activator";

    /**
     * {@value} を表す定数です。
     */
    public static final String CATEGORY = "Bundle-Category";

    /**
     * {@value} を表す定数です。
     */
    public static final String CLASSPATH = "Bundle-Classpath";

    /**
     * {@value} を表す定数です。
     */
    public static final String CONTACT_ADDRESS = "Bundle-ContactAddress";

    /**
     * {@value} を表す定数です。
     */
    public static final String COPYRIGHT = "Bundle-Copyright";

    /**
     * {@value} を表す定数です。
     */
    public static final String DESCRIPTION = "Bundle-Description";

    /**
     * {@value} を表す定数です。
     */
    public static final String DOC_URL = "Bundle-DocURL";

    /**
     * {@value} を表す定数です。
     */
    public static final String LOCALIZATION = "Bundle-Localization";

    /**
     * {@value} を表す定数です。
     */
    public static final String MANIFEST_VERSION = "Bundle-ManifestVersion";

    /**
     * {@value} を表す定数です。
     */
    public static final String NAME = "Bundle-Name";

    /**
     * {@value} を表す定数です。
     */
    public static final String NATIVE_CODE = "Bundle-NativeCore";

    /**
     * {@value} を表す定数です。
     */
    public static final String REQUIRED_EXECUTION_ENVIRONMENT = "Bundle-RequiredExecutionEnvironment";

    /**
     * {@value} を表す定数です。
     */
    public static final String SYMBOLIC_NAME = "Bundle-SymbolicName";

    /**
     * {@value} を表す定数です。
     */
    public static final String UPDATE_LOCATION = "Bundle-UpdateLocation";

    /**
     * {@value} を表す定数です。
     */
    public static final String VENDOR = "Bundle-Vendor";

    /**
     * {@value} を表す定数です。
     */
    public static final String VERSION = "Bundle-Version";

    /**
     * {@value} を表す定数です。
     */
    public static final String DYNAMIC_IMPORT_PACKAGE = "DynamicImport-Package";

    /**
     * {@value} を表す定数です。
     */
    public static final String EXPORT_PACKAGE = "Export-Package";

    /**
     * {@value} を表す定数です。
     */
    public static final String EXPORT_SERVICE = "Export-Service";

    /**
     * {@value} を表す定数です。
     */
    public static final String FRAGMENT_HOST = "Fragment-Host";

    /**
     * {@value} を表す定数です。
     */
    public static final String IMPORT_PACKAGE = "Import-Package";

    /**
     * {@value} を表す定数です。
     */
    public static final String IMPORT_SERVICE = "Import-Service";

    /**
     * {@value} を表す定数です。
     */
    public static final String REQUIRE_BUNDLE = "Require-Bundle";

    private BundleInfoUtil() {

    }

    /**
     * 指定したバンドルのマニフェスト情報からエントリを取得します。
     * 
     * @param bundle
     *            {@link Bundle} オブジェクト
     * @param header
     *            ヘッダ名称 (本クラスの定数を利用して指定できます)
     * @return 取得できた値
     */
    @SuppressWarnings("unchecked")
    public static String getHeader(final Bundle bundle, final String header) {
        Dictionary headers = bundle.getHeaders();
        if (headers != null) {
            return (String) headers.get(header);
        }
        return null;
    }
}
