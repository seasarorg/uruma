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
package org.seasar.uruma.util;

import java.text.MessageFormat;

import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.message.MessageFormatter;
import org.seasar.framework.message.MessageResourceBundle;
import org.seasar.framework.message.MessageResourceBundleFactory;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;

/**
 * Uruma のユーザアプリケーションから使用できるメッセージリソースのためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class MessageUtil {
    private MessageUtil() {

    }

    /**
     * メッセージをデフォルトのリソースバンドルから取得して返します。<br />
     * デフォルトのリソースバンドル名は {@link UrumaConstants#USER_MESSAGE_BASE} が使用されます。
     * 
     * @param key
     *            キー
     * @return メッセージ
     * @see UrumaConstants#USER_MESSAGE_BASE
     */
    public static String getMessage(final String key) {
        return getMessage(key, (Object[]) null);
    }

    /**
     * メッセージをデフォルトのリソースバンドルから取得して返します。<br />
     * デフォルトのリソースバンドル名は {@link UrumaConstants#USER_MESSAGE_BASE} が使用されます。
     * 
     * @param key
     *            キー
     * @param args
     *            メッセージ引数
     * @return メッセージ
     */
    public static String getMessage(final String key, final Object... args) {
        return getMessageWithBundleName(UrumaConstants.USER_MESSAGE_BASE, key,
                args);
    }

    /**
     * メッセージを指定したリソースバンドルから取得して返します。<br />
     * 
     * @param bundleName
     *            リソースバンドル名称
     * @param key
     *            キー
     * @return メッセージ
     */
    public static String getMessageWithBundleName(final String bundleName,
            final String key) {
        return getMessageWithBundleName(bundleName, key, (Object[]) null);
    }

    /**
     * メッセージを指定したリソースバンドルから取得して返します。<br />
     * 
     * @param bundleName
     *            リソースバンドル名称
     * @param key
     *            キー
     * @param args
     *            メッセージ引数
     * @return メッセージ
     * @throws NotFoundException
     *             リソースバンドルが見つからなかった場合
     */
    public static String getMessageWithBundleName(final String bundleName,
            final String key, final Object... args) {
        try {

            MessageResourceBundle bundle = MessageResourceBundleFactory
                    .getBundle(bundleName);
            String message = bundle.get(key);
            if (message != null) {
                return MessageFormat.format(message, args);
            } else {
                return MessageFormatter.getSimpleMessage(
                        UrumaMessageCodes.MESSAGE_KEY_NOT_FOUND, new Object[] {
                                bundleName, key });
            }
        } catch (ResourceNotFoundRuntimeException ex) {
            throw new NotFoundException(
                    UrumaMessageCodes.MESSAGE_RESOURCE_NOT_FOUND, bundleName);
        }
    }
}
