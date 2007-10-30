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
package org.seasar.uruma.core;

/**
 * Uruma で利用するメッセージコードを定義するインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface UrumaMessageCodes {
    /**
     * 画面定義テンプレートをファイルから読み込む際のメッセージコードです。
     */
    public static final String LOAD_TEMPLATE_FROM_FILE = "IURM0112";

    /**
     * 画面定義テンプレートをキャッシュから読み込む際のメッセージコードです。
     */
    public static final String LOAD_TEMPLATE_FROM_CACHE = "IURM0113";

    /**
     * ウィンドウオープン時のメッセージコードです。
     */
    public static final String OPEN_WINDOW = "IURM0401";

    /**
     * ウィンドウ初期化時のメッセージコードです。
     */
    public static final String INIT_WINDOW = "IURM0402";

    /**
     * ウィンドウクローズ時のメッセージコードです。
     */
    public static final String CLOSE_WINDOW = "IURM0403";

}
