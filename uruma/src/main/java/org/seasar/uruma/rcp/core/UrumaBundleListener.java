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
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.SynchronousBundleListener;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.rcp.UrumaApplication;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.impl.ApplicationElement;
import org.seasar.uruma.rcp.configuration.impl.RunElement;

/**
 * OSGi バンドルのライフサイクルを監視する {@link BundleListener} です。<br />
 * 
 * @author y-komori
 */
public class UrumaBundleListener implements SynchronousBundleListener {
    private static final String REQUIRE_BUNDLE = "Require-Bundle";

    private BundleContext context;

    /**
     * {@link UrumaBundleListener} を構築します。<br />
     * 
     * @param context
     */
    public UrumaBundleListener(final BundleContext context) {
        this.context = context;
    }

    /*
     * @see org.osgi.framework.BundleListener#bundleChanged(org.osgi.framework.BundleEvent)
     */
    public void bundleChanged(final BundleEvent event) {
        int eventType = event.getType();

        switch (eventType) {
        case BundleEvent.RESOLVED:
            resolvedBundle(event);
            break;
        case BundleEvent.STARTING:
            startingBundle(event);
            break;
        case BundleEvent.STARTED:
            startedBundle(event);
            break;
        case BundleEvent.STOPPING:
            stoppingBundle(event);
            break;
        case BundleEvent.STOPPED:
            stoppedBundle(event);
            break;
        default:
        }
    }

    protected void resolvedBundle(final BundleEvent event) {
        System.err.println("Resolved : " + event.getBundle().getSymbolicName());
    }

    protected void startingBundle(final BundleEvent event) {
        Bundle bundle = event.getBundle();
        System.err.println("STARTING = " + bundle.getSymbolicName());
        String required = getBundleManifestValue(bundle, REQUIRE_BUNDLE);
        if (required != null) {
            int pos = required
                    .indexOf(UrumaConstants.URUMA_BUNDLE_SYMBOLIC_NAME);
            if (pos > 0) {
                System.err.println("Urumaアプリです!");
            }

        }
    }

    protected void startedBundle(final BundleEvent event) {
        try {
            System.err.println("Started : "
                    + event.getBundle().getSymbolicName());

            if ("org.eclipse.equinox.registry".equals(event.getBundle()
                    .getSymbolicName())) {
                BundleContext context = event.getBundle().getBundleContext();
                System.err.println("捕まえた！");
                // addAppContribution(context);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    protected void stoppingBundle(final BundleEvent event) {
        // Do nothing.
    }

    protected void stoppedBundle(final BundleEvent event) {
        // Do nothing.
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

    protected void addAppContribution(final BundleContext context) {
        System.err.println("<=============================================>");
        ServiceReference ref = context
                .getServiceReference(IExtensionRegistry.class.getName());
        IExtensionRegistry registry = (IExtensionRegistry) context
                .getService(ref);
        // System.err.println("IExtensionRegistry : "
        // + UrumaLogger.getObjectDescription(registry));

        System.err.println("BundleList start");
        Bundle[] bundles = context.getBundles();
        for (int i = 0; i < bundles.length; i++) {
            String bundleName = bundles[i].getSymbolicName();
            System.err.println(bundleName);
            if (checkUrumaApplication(bundles[i])) {
                System.err.println("Urumaアプリです!");

                String id = Long.toString(bundles[i].getBundleId());
                String name = bundles[i].getSymbolicName();
                IContributor contributor = new RegistryContributor(id, name,
                        null, null);
                // // IContributor contributor = ContributorFactoryOSGi
                // // .createContributor(bundles[i]);

                StringWriter writer = new StringWriter(4096);

                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
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

                System.err.println(content);

                Object token = ((ExtensionRegistry) registry)
                        .getTemporaryUserToken();
                // Object token = null;
                registry.addContribution(is, contributor, false, null, null,
                        token);
            }
        }

        System.err.println("BundleList end");
        System.err.println("<=============================================>");
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
}
