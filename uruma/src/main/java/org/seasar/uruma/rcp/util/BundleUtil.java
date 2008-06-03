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
package org.seasar.uruma.rcp.util;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.BundleRuntimeException;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link Bundle} を扱うためのユーティリティクラスです。
 * 
 * @author y.sugigami
 */
public class BundleUtil {

    private static final UrumaLogger logger = UrumaLogger
            .getLogger(BundleUtil.class);

    /**
     * {@link Bundle}を取得します。<br />
     * 
     * @param symbolicName
     *      pluginID
     * @return {@link Bundle} オブジェクト
     */
    public static Bundle getBundle(final String symbolicName) {
        AssertionUtil.assertNotEmpty("SymbolicName", symbolicName);
        Bundle bundle = Platform.getBundle(symbolicName);
        return bundle;
    }

    /**
     * {@link Bundle}を開始します。<br />
     * 
     * @param symbolicName
     *      pluginID
     */
    public static void start(final String symbolicName) {
        AssertionUtil.assertNotEmpty("SymbolicName", symbolicName);
        Bundle targetBundle = getBundle(symbolicName);
        AssertionUtil.assertNotNull("Bundle", targetBundle);
        if (!BundleUtility.isActive(targetBundle)) {
            try {
                logger.log(UrumaMessageCodes.BUNDLE_START, symbolicName);
                targetBundle.start();
            } catch (BundleException e) {
                throw new BundleRuntimeException(
                        UrumaMessageCodes.EXCEPTION_OCCURED_WITH_REASON, e);
            }
        } else {
            logger.log(UrumaMessageCodes.BUNDLE_STARTED, symbolicName);
        }
    }

    /**
     * {@link Bundle}を停止します。<br />
     * 
     * @param symbolicName
     */
    public static void stop(final String symbolicName) {
        AssertionUtil.assertNotEmpty("SymbolicName", symbolicName);
        Bundle targetBundle = getBundle(symbolicName);
        AssertionUtil.assertNotNull("Bundle", targetBundle);
        if (BundleUtility.isActive(targetBundle)) {
            try {
                logger.log(UrumaMessageCodes.BUNDLE_STOP, symbolicName);
                targetBundle.stop();
            } catch (BundleException e) {
                throw new BundleRuntimeException(
                        UrumaMessageCodes.EXCEPTION_OCCURED_WITH_REASON, e);
            }
        }
    }

    /**
     * {@link Bundle}を更新します。<br />
     * 
     * @param symbolicName
     *      pluginID
     */
    public static void update(final String symbolicName) {
        AssertionUtil.assertNotEmpty("SymbolicName", symbolicName);
        Bundle targetBundle = getBundle(symbolicName);
        AssertionUtil.assertNotNull("Bundle", targetBundle);
        try {
            logger.log(UrumaMessageCodes.BUNDLE_UPDATE, symbolicName);
            targetBundle.update();
        } catch (BundleException e) {
            throw new BundleRuntimeException(
                    UrumaMessageCodes.EXCEPTION_OCCURED_WITH_REASON, e);
        }
    }
}
