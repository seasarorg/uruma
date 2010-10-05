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

import static org.seasar.uruma.core.UrumaMessageCodes.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.ImageData;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.PathUtil;

/**
 * 様々なパスからイメージを検索できる {@link ImageDescriptor} です。<br />
 * 本クラスを利用する際は、インスタンスを直接生成してください。<br />
 * 本クラスでは、以下の順番でイメージを検索します。<br />
 * <ol>
 * <li>コンストラクタで与えられた {@code path} が「/」で始まる場合、クラスパス上の絶対パスと見なしてイメージを読み込みます。
 * <li>コンストラクタで {@code parentUrl} が与えられている場合、{@code path}
 * をそのURLからの相対パスと見なしてイメージを読み込みます。
 * <li>コンストラクタで与えられた {@code path} 自体を URL と見なしてイメージを読み込みます。
 * </ol>
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class UrumaImageDescriptor extends ImageDescriptor {
    private static UrumaLogger LOGGER = UrumaLogger.getLogger(UrumaImageDescriptor.class);

    protected URL parentUrl;

    protected String path;

    protected String id;

    protected URL resolevedUrl;

    protected boolean resolved;

    /**
     * {@link UrumaImageDescriptor} を構築します。<br />
     * 
     * @param parentUrl
     *        親 URL
     * @param path
     *        イメージを読み込むためのパス。{@code null} であってはいけません。
     */
    public UrumaImageDescriptor(final URL parentUrl, final String path) {
        AssertionUtil.assertNotNull("path", path);
        this.parentUrl = parentUrl;
        this.path = path;
        this.id = createId(parentUrl, path);
    }

    /**
     * パス解決済みの URL を返します。<br />
     * 
     * @return パス解決済みのURL。解決できていない場合は {@code null}
     */
    public URL getResolvedURL() {
        return resolevedUrl;
    }

    protected URL resolve() {
        resolevedUrl = doResolve(parentUrl, path);
        if (resolevedUrl == null) {
            logImageResourceLoadFailed(parentUrl, path);
        }
        resolved = true;
        return resolevedUrl;
    }

    protected URL doResolve(final URL parentUrl, final String path) {
        URL[] candidates = createUrlCandidates(parentUrl, path);
        for (URL url : candidates) {
            InputStream is = null;
            try {
                is = url.openStream();
                return url;
            } catch (IOException ignore) {
            } finally {
                InputStreamUtil.closeSilently(is);
            }
        }
        return null;
    }

    protected URL[] createUrlCandidates(final URL parentUrl, final String path) {
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

    protected InputStream getStream() {
        if (resolevedUrl != null) {
            try {
                return new BufferedInputStream(resolevedUrl.openStream());
            } catch (IOException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    /*
     * @see org.eclipse.jface.resource.ImageDescriptor#getImageData()
     */
    @Override
    public ImageData getImageData() {
        if (!resolved) {
            resolve();
        }

        ImageData result = null;
        InputStream is = getStream();
        if (is != null) {
            try {
                result = new ImageData(is);
            } catch (SWTException ex) {
                if (ex.code != SWT.ERROR_INVALID_IMAGE) {
                    throw ex;
                }
            } finally {
                InputStreamUtil.closeSilently(is);
            }
        } else {
            result = getMissingImageDescriptor().getImageData();
        }
        return result;
    }

    /**
     * {@link UrumaImageDescriptor} を識別する文字列を生成します。<br />
     * 識別文字列は以下の規則で生成します。
     * <dl>
     * <dt>{@code parentUrl} が存在するとき</dt>
     * <dd>{@code parentUrl} の文字列表現 + {@code $} + {@code path}
     * <dt>{@code parentUrl} が存在しないとき</dt>
     * <dd>{@code path}
     * </dl>
     * 
     * @param parentUrl
     *        親 URL
     * @param path
     *        パス
     * @return ID
     */
    public static String createId(final URL parentUrl, final String path) {
        if (parentUrl != null) {
            String fullPath = PathUtil.concat(parentUrl.toExternalForm(), path);
            return PathUtil.normalizeRelativePath(fullPath);
        } else {
            return path;
        }
    }

    protected void logImageResourceLoadFailed(final URL parentUrl, final String path) {
        String parentPath = parentUrl != null ? parentUrl.toExternalForm() : "";
        LOGGER.log(IMAGE_RESOURCE_LOAD_FAILED, path, parentPath);
    }

    /**
     * {@link UrumaImageDescriptor} を識別する文字列を取得します。<br />
     * 
     * @return ID
     */
    public String getId() {
        return this.id;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof UrumaImageDescriptor)) {
            return false;
        }
        return ((UrumaImageDescriptor) obj).id.equals(this.id);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return UrumaImageDescriptor.class.getName() + "(" + id + ")";
    }
}
