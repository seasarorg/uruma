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

import java.io.File;
import java.io.InputStream;
import java.util.Dictionary;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.seasar.uruma.util.AssertionUtil;

/**
 * テスト用の {@link BundleContext} オブジェクトです。<br />
 * 
 * @author y-komori
 */
public class MockBundleContext implements BundleContext {
    protected Bundle bundle;

    /**
     * {@link MockBundleContext} を構築します。<br />
     */
    public MockBundleContext(final MockBundle bundle) {
        AssertionUtil.assertNotNull("bundle", bundle);
        this.bundle = bundle;
    }

    public void addBundleListener(final BundleListener listener) {
        // Do nothing.
    }

    public void addFrameworkListener(final FrameworkListener listener) {
        // Do nothing.
    }

    public void addServiceListener(final ServiceListener listener) {
        // Do nothing.
    }

    public void addServiceListener(final ServiceListener listener,
            final String filter) throws InvalidSyntaxException {
        // Do nothing.
    }

    public Filter createFilter(final String filter)
            throws InvalidSyntaxException {
        return null;
    }

    public ServiceReference[] getAllServiceReferences(final String clazz,
            final String filter) throws InvalidSyntaxException {
        return null;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public Bundle getBundle(final long id) {
        return bundle;
    }

    public Bundle[] getBundles() {
        return new Bundle[] { bundle };
    }

    public File getDataFile(final String filename) {
        return null;
    }

    public String getProperty(final String key) {
        return null;
    }

    public Object getService(final ServiceReference reference) {
        return null;
    }

    public ServiceReference getServiceReference(final String clazz) {
        return null;
    }

    public ServiceReference[] getServiceReferences(final String clazz,
            final String filter) throws InvalidSyntaxException {
        return null;
    }

    public Bundle installBundle(final String location) throws BundleException {
        return null;
    }

    public Bundle installBundle(final String location, final InputStream input)
            throws BundleException {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @SuppressWarnings("unchecked")
    public ServiceRegistration registerService(final String[] clazzes,
            final Object service, final Dictionary properties) {
        return null;
    }

    @SuppressWarnings("unchecked")
    public ServiceRegistration registerService(final String clazz,
            final Object service, final Dictionary properties) {
        return null;
    }

    public void removeBundleListener(final BundleListener listener) {
        // Do nothing.
    }

    public void removeFrameworkListener(final FrameworkListener listener) {
        // Do nothing.
    }

    public void removeServiceListener(final ServiceListener listener) {
        // Do nothing.
    }

    public boolean ungetService(final ServiceReference reference) {
        return false;
    }

}
