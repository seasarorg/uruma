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
package org.seasar.uruma.util.resource.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.osgi.framework.internal.core.BundleURLConnection;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.resource.ResourceFilter;
import org.seasar.uruma.util.resource.ResourceHandler;
import org.seasar.uruma.util.resource.ResourceTraverser;

/**
 * バンドルリソースをたどるための {@link ResourceTraverser} です。<br />
 * 
 * @author y-komori
 */
public class BundleResourceTraverser implements ResourceTraverser,
        UrumaMessageCodes {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(BundleResourceTraverser.class);

    private static final String PROTOCOL = "bundleresource";

    private static final String FILE_PREFIX = "file:";

    /*
     * @see org.seasar.uruma.util.resource.ResourceTraverser#getProtocol()
     */
    public String getProtocol() {
        return PROTOCOL;
    }

    /*
     * @see
     * org.seasar.uruma.util.resource.ResourceTraverser#traverse(java.net.URL,
     * java.net.URL, org.seasar.uruma.util.resource.ResourceHandler,
     * org.seasar.uruma.util.resource.ResourceFilter)
     */
    public void traverse(final URL root, final URL origin,
            final ResourceHandler handler, final ResourceFilter filter) {
        AssertionUtil.assertNotNull("origin", origin);
        AssertionUtil.assertNotNull("handler", handler);

        try {
            BundleURLConnection cnct = (BundleURLConnection) origin
                    .openConnection();
            String localPath = cnct.getLocalURL().getPath();
            String jarFilePath = getJarFilePath(localPath);
            String jarLocalPath = getJarFileLocalPath(localPath);
            String rootPath = getRootPath(localPath);

            JarFile jarFile = new JarFile(jarFilePath);
            JarEntry rootEntry = jarFile.getJarEntry(jarLocalPath);
            if (rootEntry == null) {
                return;
            }

            Enumeration<JarEntry> enume = jarFile.entries();
            while (enume.hasMoreElements()) {
                JarEntry entry = enume.nextElement();
                if (entry.getName().startsWith(jarLocalPath)) {
                    traverseJarEntry(jarFile, rootPath, entry, handler, filter);
                }
            }
        } catch (IOException ex) {
            logger.log(EXCEPTION_OCCURED, ex);
        }
    }

    /*
     * @see
     * org.seasar.uruma.util.resource.ResourceTraverser#traverse(java.net.URL,
     * java.net.URL, org.seasar.uruma.util.resource.ResourceHandler)
     */
    public void traverse(final URL root, final URL origin,
            final ResourceHandler handler) {
        traverse(root, origin, handler, null);
    }

    protected void traverseJarEntry(final JarFile file, final String rootPath,
            final JarEntry entry, final ResourceHandler handler,
            final ResourceFilter filter) {
        if ((filter != null) && filter.accept(entry.getName())) {
            InputStream is = null;
            try {
                is = file.getInputStream(entry);
                handler.handle(rootPath, entry.getName(), is);
            } catch (Throwable ex) {
                logger.log(EXCEPTION_OCCURED, ex);
            } finally {
                InputStreamUtil.close(is);
            }
        }
    }

    protected String getJarFilePath(final String path) {
        if (path.startsWith(FILE_PREFIX)) {
            int pos = path.indexOf('!');
            if (pos >= 0) {
                return path.substring(FILE_PREFIX.length(), pos);
            }
        }
        return null;
    }

    protected String getJarFileLocalPath(final String path) {
        int pos = path.indexOf('!');
        if (pos > -1) {
            String localPath = path.substring(pos + 1);
            if (localPath.charAt(0) == '/') {
                return localPath.substring(1);
            } else {
                return localPath;
            }
        } else {
            return null;
        }
    }

    protected String getRootPath(final String path) {
        int pos = path.indexOf("!/");
        if (pos > -1) {
            return path.substring(0, pos + 2).replace('\\', '/');
        } else {
            return null;
        }
    }
}
