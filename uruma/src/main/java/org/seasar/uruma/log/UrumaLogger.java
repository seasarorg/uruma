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
package org.seasar.uruma.log;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.framework.message.MessageFormatter;

/**
 * ログ出力を提供するクラスです。<br />
 * 
 * @author y-komori
 */
public final class UrumaLogger {

    private static final Map<Class<?>, UrumaLogger> loggers = new HashMap<Class<?>, UrumaLogger>();

    private static boolean initialized;

    private final Log log;

    /**
     * {@link UrumaLogger} を返します。<br />
     * 
     * @param clazz
     *            {@link Class} オブジェクト
     * @return {@link UrumaLogger} オブジェクト
     */
    public static synchronized UrumaLogger getLogger(final Class<?> clazz) {
        if (!initialized) {
            initialize();
        }
        UrumaLogger logger = loggers.get(clazz);
        if (logger == null) {
            logger = new UrumaLogger(clazz);
            loggers.put(clazz, logger);
        }
        return logger;
    }

    /**
     * {@link UrumaLogger} を初期化します。<br />
     */
    public static synchronized void initialize() {
        initialized = true;
    }

    /**
     * リソースを開放します。<br />
     */
    public synchronized static void dispose() {
        LogFactory.releaseAll();
        loggers.clear();
        initialized = false;
    }

    private UrumaLogger(final Class<?> clazz) {
        log = LogFactory.getLog(clazz);
    }

    /**
     * DEBUG情報が出力されるかどうかを返します。<br />
     * 
     * @return DEBUG情報が出力されるかどうか
     */
    public final boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    /**
     * DEBUG情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            {@link Throwable} オブジェクト
     */
    public final void debug(final Object message, final Throwable throwable) {
        if (isDebugEnabled()) {
            log.debug(message, throwable);
        }
    }

    /**
     * DEBUG情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     */
    public final void debug(final Object message) {
        if (isDebugEnabled()) {
            log.debug(message);
        }
    }

    /**
     * INFO情報が出力されるかどうかを返します。<br />
     * 
     * @return INFO情報が出力されるかどうか
     */
    public final boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    /**
     * INFO情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            {@link Throwable} オブジェクト
     */
    public final void info(final Object message, final Throwable throwable) {
        if (isInfoEnabled()) {
            log.info(message, throwable);
        }
    }

    /**
     * INFO情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     */
    public final void info(final Object message) {
        if (isInfoEnabled()) {
            log.info(message);
        }
    }

    /**
     * WARN情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            {@link Throwable} オブジェクト
     */
    public final void warn(final Object message, final Throwable throwable) {
        log.warn(message, throwable);
    }

    /**
     * WARN情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     */
    public final void warn(final Object message) {
        log.warn(message);
    }

    /**
     * ERROR情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            {@link Throwable} オブジェクト
     */
    public final void error(final Object message, final Throwable throwable) {
        log.error(message, throwable);
    }

    /**
     * ERROR情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     */
    public final void error(final Object message) {
        log.error(message);
    }

    /**
     * FATAL情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            {@link Throwable} オブジェクト
     */
    public final void fatal(final Object message, final Throwable throwable) {
        log.fatal(message, throwable);
    }

    /**
     * FATAL情報を出力します。<br />
     * 
     * @param message
     *            メッセージ
     */
    public final void fatal(final Object message) {
        log.fatal(message);
    }

    /**
     * ログを出力します。<br />
     * 
     * @param throwable
     *            {@link Throwable} オブジェクト
     */
    public final void log(final Throwable throwable) {
        error(throwable.getMessage(), throwable);
    }

    /**
     * ログを出力します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param args
     *            引数
     */
    public final void log(final String messageCode, final Object... args) {
        log(messageCode, null, args);
    }

    /**
     * ログを出力します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param throwable
     *            {@link Throwable} オブジェクト
     * @param args
     *            引数
     */
    public final void log(final String messageCode, final Throwable throwable,
            final Object... args) {
        char messageType = messageCode.charAt(0);
        if (isEnabledFor(messageType)) {
            String message = MessageFormatter.getMessage(messageCode, args);

            switch (messageType) {
            case 'D':
                log.debug(message, throwable);
                break;
            case 'I':
                log.info(message, throwable);
                break;
            case 'W':
                log.warn(message, throwable);
                break;
            case 'E':
                log.error(message, throwable);
                break;
            case 'F':
                log.fatal(message, throwable);
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(messageType));
            }
        }
    }

    private boolean isEnabledFor(final char messageType) {
        switch (messageType) {
        case 'D':
            return log.isDebugEnabled();
        case 'I':
            return log.isInfoEnabled();
        case 'W':
            return log.isWarnEnabled();
        case 'E':
            return log.isErrorEnabled();
        case 'F':
            return log.isFatalEnabled();
        default:
            throw new IllegalArgumentException(String.valueOf(messageType));
        }
    }

    /**
     * オブジェクトの詳細情報を返します。<br />
     * 
     * @param obj
     *            オブジェクト
     * @return 詳細情報
     */
    public static final String getObjectDescription(final Object obj) {
        if (obj != null) {
            return obj.getClass().getName() + "@"
                    + Integer.toHexString(obj.hashCode());
        } else {
            return "NULL";
        }
    }
}
