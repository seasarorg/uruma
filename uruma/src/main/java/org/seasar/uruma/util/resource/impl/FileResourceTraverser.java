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

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.FileUtil;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.resource.ResourceFilter;
import org.seasar.uruma.util.resource.ResourceHandler;
import org.seasar.uruma.util.resource.ResourceTraverser;

/**
 * ファイルシステムをたどるための {@link ResourceTraverser} です。<br />
 * 
 * @author y-komori
 */
public class FileResourceTraverser implements ResourceTraverser {
    protected static final String PROTOCOL = "file";

    /*
     * @see org.seasar.uruma.util.resource.ResourceTraverser#getProtocol()
     */
    public String getProtocol() {
        return PROTOCOL;
    }

    /*
     * @see org.seasar.uruma.util.resource.ResourceTraverser#traverse(java.net.URL,
     *      java.net.URL, org.seasar.uruma.util.resource.ResourceHandler,
     *      org.seasar.uruma.util.resource.ResourceFilter)
     */
    public void traverse(final URL root, final URL origin,
            final ResourceHandler handler, final ResourceFilter filter) {
        AssertionUtil.assertNotNull("origin", origin);
        AssertionUtil.assertNotNull("handler", handler);

        String path = origin.getPath();
        if (StringUtil.isNotBlank(path)) {
            File file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    File rootDir = getRootDir(root, origin);
                    traverseFileSystem(rootDir, file, handler, filter);
                }
            }

        }
    }

    /*
     * @see org.seasar.uruma.util.resource.ResourceTraverser#traverse(java.net.URL,
     *      java.net.URL, org.seasar.uruma.util.resource.ResourceHandler)
     */
    public void traverse(final URL root, final URL origin,
            final ResourceHandler handler) {
        traverse(root, origin, handler, null);
    }

    protected void traverseFileSystem(final File baseDir, final File dir,
            final ResourceHandler handler, final ResourceFilter filter) {
        File[] files = dir.listFiles();
        String basePath = baseDir.getAbsolutePath().replace('\\', '/');

        for (int i = 0; i < files.length; ++i) {
            File file = files[i];
            if (file.isDirectory()) {
                traverseFileSystem(baseDir, file, handler, filter);
            } else {
                int pos = -1;
                if (baseDir != null) {
                    pos = FileUtil.getCanonicalPath(baseDir).length();
                }
                String filePath = FileUtil.getCanonicalPath(file);
                String resourcePath = filePath.substring(pos + 1).replace('\\',
                        '/');

                if ((filter != null) && !filter.accept(resourcePath)) {
                    continue;
                }

                InputStream is = FileInputStreamUtil.create(file);
                try {
                    handler.handle(basePath, resourcePath, is);
                } finally {
                    InputStreamUtil.close(is);
                }
            }
        }
    }

    protected File getRootDir(final URL root, final URL origin) {
        if (root != null) {
            String path = root.getPath();
            if (path != null) {
                File rootDir = new File(path);
                if (rootDir.exists()) {
                    return rootDir;
                }
            }
        }

        String path = origin.getPath();
        return new File(path);
    }
}
