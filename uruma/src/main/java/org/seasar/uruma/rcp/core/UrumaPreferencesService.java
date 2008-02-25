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
package org.seasar.uruma.rcp.core;

import java.util.Properties;

import org.eclipse.core.internal.preferences.exchange.IProductPreferencesService;
import org.eclipse.core.internal.preferences.legacy.ProductPreferencesService;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.seasar.uruma.core.UrumaConstants;

/**
 * {@link IProductPreferencesService} の実装クラスです。<br />
 * Eclipse デフォルト実装の {@link ProductPreferencesService} に差し替えて使用します。<br />
 * {@link ProductPreferencesService} では、 <code>plugin_customization.ini</code>
 * ファイルからカスタマイズ情報を読み込みますが、本クラスは Uruma 独自の設定情報を返します。<br />
 * 
 * @author y-komori
 */
public class UrumaPreferencesService implements IProductPreferencesService,
        UrumaConstants {

    /*
     * @see org.eclipse.core.internal.preferences.exchange.IProductPreferencesService#getProductCustomization()
     */
    public Properties getProductCustomization() {
        Properties props = new Properties();

        // キーコンフィグレーション(スキーム)を設定
        props.put("org.eclipse.ui/"
                + IWorkbenchPreferenceConstants.KEY_CONFIGURATION_ID,
                URUMA_APP_SCHEME_ID);

        return props;
    }

    /*
     * @see org.eclipse.core.internal.preferences.exchange.IProductPreferencesService#getProductTranslation()
     */
    public Properties getProductTranslation() {
        return new Properties();
    }
}
