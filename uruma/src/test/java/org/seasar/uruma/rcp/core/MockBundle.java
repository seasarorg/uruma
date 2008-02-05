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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

/**
 * テスト用の {@link Bundle} オブジェクトです。<br />
 * 
 * @author y-komori
 */
public class MockBundle implements Bundle {
    static final String SYMBOLIC_NAME = "org.seasar.uruma.rcp.core";

    protected MockBundleContext context;

    public Enumeration<?> findEntries(final String path,
            final String filePattern, final boolean recurse) {
        URL url = getClass().getClassLoader().getResource(
                getClass().getName() + ".class");
        if (url != null) {
            ArrayList<URL> entries = new ArrayList<URL>();
            entries.add(url);
            return Collections.enumeration(entries);
        } else {
            return getEmptyEnumeration();
        }
    }

    public BundleContext getBundleContext() {
        if (context == null) {
            context = new MockBundleContext(this);
        }
        return context;
    }

    public long getBundleId() {
        return 0;
    }

    public URL getEntry(final String path) {
        return null;
    }

    public Enumeration<?> getEntryPaths(final String path) {
        return getEmptyEnumeration();
    }

    public Dictionary<?, ?> getHeaders() {
        return new Properties();
    }

    public Dictionary<?, ?> getHeaders(final String locale) {
        return new Properties();
    }

    public long getLastModified() {
        return 0;
    }

    public String getLocation() {
        return null;
    }

    public ServiceReference[] getRegisteredServices() {
        return null;
    }

    public URL getResource(final String name) {
        return null;
    }

    public Enumeration<?> getResources(final String name) throws IOException {
        return getEmptyEnumeration();
    }

    public ServiceReference[] getServicesInUse() {
        return null;
    }

    public int getState() {
        return 0;
    }

    public String getSymbolicName() {
        return SYMBOLIC_NAME;
    }

    public boolean hasPermission(final Object permission) {
        return true;
    }

    public Class<?> loadClass(final String name) throws ClassNotFoundException {
        return Class.forName(name);
    }

    public void start() throws BundleException {
        // Do nothing.
    }

    public void start(final int options) throws BundleException {
        // Do nothing.
    }

    public void stop() throws BundleException {
        // Do nothing.
    }

    public void stop(final int options) throws BundleException {
        // Do nothing.
    }

    public void uninstall() throws BundleException {
        // Do nothing.
    }

    public void update() throws BundleException {
        // Do nothing.
    }

    public void update(final InputStream in) throws BundleException {
        // Do nothing.
    }

    @SuppressWarnings("unchecked")
    protected Enumeration<?> getEmptyEnumeration() {
        return Collections.enumeration(Collections.EMPTY_LIST);
    }
}
