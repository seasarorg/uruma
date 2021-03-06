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

import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.internal.preferences.exchange.IProductPreferencesService;
import org.eclipse.core.internal.preferences.legacy.ProductPreferencesService;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.UrumaAppInitException;
import org.seasar.uruma.exception.UrumaAppNotFoundException;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.rcp.core.UrumaBundleState.BundleState;
import org.seasar.uruma.rcp.util.BundleInfoUtil;
import org.seasar.uruma.rcp.util.BundleUtil;
import org.seasar.uruma.ui.dialogs.UrumaErrorDialog;
import org.seasar.uruma.util.MessageUtil;

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

        try {
            context.addBundleListener(new UrumaBundleListener());

            List<Bundle> activated = prepareUrumaService(context);
            if (activated.size() == 0) {
                throw new UrumaAppNotFoundException();
            }

            registerProductPreferenceService(context);

            UrumaBundleState.getInstance().setUrumaBundleState(
                    BundleState.AVAILABLE);
        } catch (Throwable ex) {
            Display display = new Display();
            Shell shell = new Shell(display);
            String msg = MessageUtil.getMessageWithBundleName(
                    URUMA_MESSAGE_BASE, "RCP_START_FAILED");
            UrumaErrorDialog dialog = new UrumaErrorDialog(shell, "Uruma", msg,
                    ex);
            dialog.open();
            shell.dispose();
            display.dispose();
        }
    }

    public void stop(final BundleContext context) throws Exception {
        UrumaBundleState.getInstance().setUrumaBundleState(
                BundleState.NOT_AVAILABLE);
        logger.log(URUMA_BUNDLE_STOP);
    }

    /**
     * Uruma アプリケーションを検索し、アクティベートします。<br />
     * 
     * @param context
     *            Uruma の {@link BundleContext}
     * @return アクティベートに成功した Uruma アプリケーションバンドルのリスト
     */
    @SuppressWarnings("unchecked")
    protected List<Bundle> prepareUrumaService(final BundleContext context) {
        List<Bundle> successedBundleList = new ArrayList<Bundle>();
        String serviceName = UrumaService.class.getName();
        List<Bundle> appBundles = findUrumaApplications(context);
        if (appBundles.size() == 0) {
            throw new UrumaAppNotFoundException();
        }

        Dictionary props = new Properties();
        props.put(URUMA_SERVICE_PROP_APPS, getBundleSymbolicNames(appBundles));

        UrumaServiceFactory factory = new UrumaServiceFactory();
        context.registerService(serviceName, factory, props);

        // Urumaアプリケーションのバンドルに対応する UrumaService を取得することで、
        // Urumaアプリケーションをアクティベートする
        // @see UrumaServiceFactory
        // @see UrumaServiceImpl#initialize()
        for (Bundle bundle : appBundles) {
            setupLog4jConfig(bundle);
            BundleContext appContext = bundle.getBundleContext();
            if (appContext == null) {
                logger.log(INVALID_URUMA_APP_BUNDLE, bundle.getSymbolicName(),
                        BundleUtil.getBundleState(bundle));
                continue;
            }
            ServiceReference ref = appContext
                    .getServiceReference(UrumaService.class.getName());
            UrumaServiceImpl service = (UrumaServiceImpl) appContext
                    .getService(ref);
            if (service != null
                    && UrumaBundleState.getInstance().getAppBundleState() == BundleState.AVAILABLE) {
                service.registerExtensions();
                successedBundleList.add(bundle);
            } else {
                Throwable ex = UrumaBundleState.getInstance()
                        .getUrumaAppInitializingException();
                throw new UrumaAppInitException(bundle, ex, ex.getMessage());
            }
        }
        return successedBundleList;
    }

    /**
     * インストールされているバンドルから Uruma に依存しているバンドルを探して登録します。
     * 
     * @param context
     *            {@link BundleContext} オブジェクト
     * @return Urumaアプリケーションバンドルのリスト
     */
    protected List<Bundle> findUrumaApplications(final BundleContext context) {
        Bundle[] bundles = context.getBundles();
        List<Bundle> appBundles = new ArrayList<Bundle>();
        for (Bundle bundle : bundles) {
            if (isUrumaApplication(bundle)) {
                logger.log(URUMA_APPLICATION_FOUND, bundle.getSymbolicName());
                appBundles.add(bundle);
            }
        }
        return appBundles;
    }

    @SuppressWarnings("unchecked")
    protected boolean isUrumaApplication(final Bundle bundle) {
        logger.log(ANALYZING_BUNDLE, bundle.getSymbolicName());
        String require = BundleInfoUtil.getHeader(bundle,
                BundleInfoUtil.REQUIRE_BUNDLE);
        if (require != null) {
            if (require.indexOf(URUMA_BUNDLE_SYMBOLIC_NAME) > 0) {
                return true;
            }
        }
        return false;
    }

    protected String[] getBundleSymbolicNames(final List<Bundle> bundles) {
        String[] names = new String[bundles.size()];
        for (int i = 0; i < bundles.size(); i++) {
            names[i] = bundles.get(i).getSymbolicName();
        }
        return names;
    }

    protected void setupLog4jConfig(final Bundle bundle) {
        URL xmlConfig = bundle.getResource(DEFAULT_LOG_XML);
        if (xmlConfig != null) {
            logger.addXmlConfig(xmlConfig);
        } else {
            URL propConfig = bundle.getResource(DEFAULT_LOG_PROPERTIES);
            if (propConfig != null) {
                logger.addPropertyConfig(propConfig);
            }
        }
    }

    /**
     * {@link UrumaPreferencesService} を {@link IProductPreferencesService}
     * としてサービス登録します。<br />
     * {@link Constants#SERVICE_RANKING} プロパティを 1 に設定することで、 Eclipse の提供する
     * {@link ProductPreferencesService} クラスよりも高い優先度で使用されるようにしています。
     * 
     * @param context
     *            {@link BundleContext} オブジェクト
     */
    protected void registerProductPreferenceService(final BundleContext context) {
        IProductPreferencesService service = new UrumaPreferencesService();
        Dictionary<String, Integer> prop = new Hashtable<String, Integer>();
        prop.put(Constants.SERVICE_RANKING, 1);
        context.registerService(IProductPreferencesService.class.getName(),
                service, prop);
    }
}
