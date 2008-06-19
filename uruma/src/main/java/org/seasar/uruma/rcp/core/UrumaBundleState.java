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

/**
 * RCP 環境における Uruma バンドルおよび Uruma アプリケーションの状態を保持するクラスです。<br />
 * 
 * @author y-komori
 */
public final class UrumaBundleState {
    private static UrumaBundleState instance;

    private Throwable urumaAppInitializingException;

    private BundleState urumaBundleState = BundleState.NOT_AVAILABLE;

    private BundleState appBundleState = BundleState.NOT_AVAILABLE;

    private UrumaBundleState() {

    }

    /**
     * Uruma アプリケーションバンドルの初期化中に発生した例外を取得します。<br />
     * 
     * @return 例外オブジェクト
     */
    public Throwable getUrumaAppInitializingException() {
        return urumaAppInitializingException;
    }

    /**
     * Uruma アプリケーションバンドルの初期化中に発生した例外を設定します。<br />
     * 
     * @param urumaAppInitializingException
     *            例外オブジェクト
     */
    void setUrumaAppInitializingException(
            final Throwable urumaAppInitializingException) {
        this.urumaAppInitializingException = urumaAppInitializingException;
    }

    /**
     * Uruma バンドルの状態を取得します。<br />
     * 
     * @return Uruma バンドルの状態
     */
    public BundleState getUrumaBundleState() {
        return urumaBundleState;
    }

    /**
     * Uruma バンドルの状態を設定します。<br />
     * 
     * @param urumaBundleState
     *            Uruma バンドルの状態
     */
    void setUrumaBundleState(final BundleState urumaBundleState) {
        this.urumaBundleState = urumaBundleState;
    }

    /**
     * Uruma アプリケーションの状態を取得します。<br />
     * 
     * @return Uruma アプリケーションの状態
     */
    public BundleState getAppBundleState() {
        return appBundleState;
    }

    /**
     * Uruma アプリケーションの状態を設定します。<br />
     * 
     * @param appBundleState
     *            Uruma アプリケーションの状態
     */
    void setAppBundleState(final BundleState appBundleState) {
        this.appBundleState = appBundleState;
    }

    /**
     * インスタンスを取得します。<br />
     * 
     * @return 本クラスのインスタンス
     */
    public static UrumaBundleState getInstance() {
        if (instance == null) {
            instance = new UrumaBundleState();
        }
        return instance;
    }

    /**
     * バンドルの状態を表す列挙型です。<br />
     * 
     * @author y-komori
     */
    public enum BundleState {
        /**
         * バンドルは利用できません。<br />
         */
        NOT_AVAILABLE,
        /**
         * バンドルは利用可能です。<br />
         */
        AVAILABLE;
    }
}
