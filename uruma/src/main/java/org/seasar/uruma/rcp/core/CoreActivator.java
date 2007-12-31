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
package org.seasar.uruma.rcp.core;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.UrumaService;

/**
 * Uruma のための {@link BundleActivator} です。<br />
 * 
 * @author y-komori
 */
public class CoreActivator implements BundleActivator, UrumaConstants,
        UrumaMessageCodes {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(CoreActivator.class);

    public void start(final BundleContext context) throws Exception {
        logger.log(URUMA_BUNDLE_START);

        context.addBundleListener(new UrumaBundleListener());

        initUrumaService(context);
    }

    public void stop(final BundleContext context) throws Exception {
        logger.log(URUMA_BUNDLE_STOP);
    }

    @SuppressWarnings("unchecked")
    protected void initUrumaService(final BundleContext context) {
        List<String> appBundles = findUrumaApplications(context);
        Dictionary props = new Properties();
        props.put(URUMA_SERVICE_PROP_APPS, appBundles.toArray());

        UrumaServiceFactory factory = new UrumaServiceFactory();
        context.registerService(UrumaService.class.getName(), factory, props);
    }

    /**
     * インストールされているバンドルから Uruma に依存しているバンドルを探して登録します。
     * 
     * @param context
     *            {@link BundleContext} オブジェクト
     * @return Urumaに依存しているバンドルのシンボリックネームリスト
     */
    protected List<String> findUrumaApplications(final BundleContext context) {
        Bundle[] bundles = context.getBundles();
        List<String> appBundles = new ArrayList<String>();
        for (Bundle bundle : bundles) {
            if (isUrumaApplication(bundle)) {
                logger.log(URUMA_APPLICATION_FOUND, bundle.getSymbolicName());
                appBundles.add(bundle.getSymbolicName());
            }
        }
        return appBundles;
    }

    @SuppressWarnings("unchecked")
    protected boolean isUrumaApplication(final Bundle bundle) {
        logger.log(ANALYZING_BUNDLE, bundle.getSymbolicName());
        Dictionary<String, String> headers = bundle.getHeaders();
        String require = headers.get(REQUIRE_BUNDLE_HEADER);
        if (require != null) {
            if (require.indexOf(URUMA_BUNDLE_SYMBOLIC_NAME) > 0) {
                return true;
            }
        }
        return false;
    }
}
