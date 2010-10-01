/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
package org.seasar.uruma.resource.internal;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.PathUtil;

/**
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class UrumaImageDescriptor extends ImageDescriptor {
    protected URL parentUrl;

    protected String path;

    protected URL url;

    /**
     * {@link UrumaImageDescriptor} を構築します。<br />
     * 
     * @param parentUrl
     *        親URL
     * @param path
     *        イメージを読み込むためのパス。{@code null} であってはいけません。
     */
    public UrumaImageDescriptor(final URL parentUrl, final String path) {
        AssertionUtil.assertNotNull("path", path);
        this.parentUrl = parentUrl;
        this.path = path;
    }

    protected URL[] createURLs(final URL parentUrl, final String path) {
        int index = 0;
        URL[] urls = new URL[3];

        // パスが / で始まっている場合はクラスパスルートからのパスと見なす
        if (path.startsWith("/")) {
            String realPath = path.substring(1, path.length());
            URL url = ResourceUtil.getResourceNoException(realPath);
            if (url != null) {
                urls[index] = url;
                index++;
            }
        }

        // パスを親URLからの相対パスと見なす
        if (parentUrl != null) {
            try {
                URL url = PathUtil.createURL(parentUrl, path, true);
                if (url != null) {
                    urls[index] = url;
                    index++;
                }
            } catch (Exception ignore) {
            }
        }

        // パス自体をURLと見なす
        try {
            URL url = new URL(path);
            urls[index] = url;
            index++;
        } catch (MalformedURLException ignore) {
        }

        if (index == urls.length) {
            return urls;
        } else {
            URL[] result = new URL[index];
            System.arraycopy(urls, 0, result, 0, index);
            return result;
        }
    }

    /*
     * @see org.eclipse.jface.resource.ImageDescriptor#getImageData()
     */
    @Override
    public ImageData getImageData() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return path.hashCode();
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UrumaImageDescriptor other = (UrumaImageDescriptor) obj;
        if (this.path == null) {
            if (other.path != null)
                return false;
        } else if (!this.path.equals(other.path))
            return false;
        return true;
    }

}
