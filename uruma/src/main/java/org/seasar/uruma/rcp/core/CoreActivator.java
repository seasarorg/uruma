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

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Dictionary;
import java.util.Enumeration;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.rcp.UrumaApplication;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.impl.ApplicationElement;
import org.seasar.uruma.rcp.configuration.impl.RunElement;

/**
 * Uruma のための {@link BundleActivator} です。<br />
 * RCP 環境上で Uruma を利用する場合、config.ini ファイルで <code>org.eclipse.core.runtime</code>
 * よりも先に {@link CoreActivator} が起動するように設定してください。<br />
 * <br />
 * 【config.ini の 記述例】
 * 
 * <pre>
 * osgi.bundles=org.eclipse.equinox.common@2:start,
 *     org.eclipse.update.configurator@3:start,
 *     org.seasar.uruma.rcp.core@4:start,
 *     org.eclipse.core.runtime@start
 * </pre>
 * 
 * @author y-komori
 */
public class CoreActivator implements BundleActivator, ServiceTrackerCustomizer {
    private static final String REQUIRE_BUNDLE = "Require-Bundle";

    private BundleContext context;

    private BundleListener listener;

    public void start(final BundleContext context) throws Exception {
        System.err.println("UrumaCoreActivaterStart");

        // this.context = context;
        // ServiceTracker tracker = new ServiceTracker(context,
        // IExtensionRegistry.class.getName(), this);
        // tracker.open();
        //
        // this.listener = new UrumaBundleListener(context);
        // context.addBundleListener(listener);
    }

    public void stop(final BundleContext context) throws Exception {
        context.removeBundleListener(listener);
        System.err.println("UrumaCoreActivaterStop");
    }

    /*
     * @see org.osgi.util.tracker.ServiceTrackerCustomizer#addingService(org.osgi.framework.ServiceReference)
     */
    public Object addingService(final ServiceReference reference) {
        Object service = context.getService(reference);
        System.err.println("サービスが登録されます : " + service.getClass().getName());
        addAppContribution(context);
        return service;
    }

    /*
     * @see org.osgi.util.tracker.ServiceTrackerCustomizer#modifiedService(org.osgi.framework.ServiceReference,
     *      java.lang.Object)
     */
    public void modifiedService(final ServiceReference reference,
            final Object service) {
        // Do nothing.
    }

    /*
     * @see org.osgi.util.tracker.ServiceTrackerCustomizer#removedService(org.osgi.framework.ServiceReference,
     *      java.lang.Object)
     */
    public void removedService(final ServiceReference reference,
            final Object service) {
        // Do nothing.
    }

    protected void addAppContribution(final BundleContext context) {
        ServiceReference ref = context
                .getServiceReference(IExtensionRegistry.class.getName());
        IExtensionRegistry registry = (IExtensionRegistry) context
                .getService(ref);

        synchronized (registry) {
            System.err
                    .println("<=============================================>");
            System.err.println("BundleList start");
            Bundle[] bundles = context.getBundles();
            for (int i = 0; i < bundles.length; i++) {
                String bundleName = bundles[i].getSymbolicName();
                System.err.println(bundleName);
                if (checkUrumaApplication(bundles[i])) {
                    System.err.println("Urumaアプリです!");

                    String id = Long.toString(bundles[i].getBundleId());
                    String name = bundles[i].getSymbolicName();
                    IContributor contributor = new RegistryContributor(id,
                            name, null, null);
                    // // IContributor contributor = ContributorFactoryOSGi
                    // // .createContributor(bundles[i]);

                    StringWriter writer = new StringWriter(4096);

                    writer
                            .write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                    writer.write("<?eclipse version=\"3.2\"?>\n");
                    writer.write("<plugin>\n");

                    Extension extension = createApplication(UrumaApplication.class);
                    extension.writeConfiguration(writer);

                    writer.write("</plugin>\n");

                    String content = writer.getBuffer().toString();

                    ByteArrayInputStream is = null;
                    try {
                        is = new ByteArrayInputStream(content.getBytes("UTF-8"));
                    } catch (UnsupportedEncodingException ignore) {
                    }

                    Object token = ((ExtensionRegistry) registry)
                            .getTemporaryUserToken();
                    // Object token = null;
                    registry.addContribution(is, contributor, false, null,
                            null, token);
                }
            }

            System.err.println("BundleList end");
            System.err
                    .println("<=============================================>");
        }
    }

    protected boolean checkUrumaApplication(final Bundle bundle) {
        String required = getBundleManifestValue(bundle, REQUIRE_BUNDLE);
        if (required != null) {
            int pos = required
                    .indexOf(UrumaConstants.URUMA_BUNDLE_SYMBOLIC_NAME);
            if (pos > 0) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    protected String getBundleManifestValue(final Bundle bundle,
            final String key) {
        Dictionary<String, String> dic = bundle.getHeaders();
        Enumeration<String> keyEnum = dic.keys();
        while (keyEnum.hasMoreElements()) {
            String headerKey = keyEnum.nextElement();
            if (key.equals(headerKey)) {
                return dic.get(headerKey);
            }
        }
        return null;
    }

    protected Extension createApplication(final Class<?> runClass) {
        Extension extension = ExtensionFactory
                .createExtension(ExtensionPoints.APPLICATIONS);
        ApplicationElement application = new ApplicationElement();
        RunElement run = new RunElement();
        run.clazz = runClass.getName();
        application.addElement(run);
        extension.addConfigurationElement(application);
        return extension;
    }
}
