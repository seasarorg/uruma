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

import org.seasar.uruma.core.UrumaConstants;

/**
 * メニューのニーモニックを扱うためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class MnemonicUtil implements UrumaConstants {
    private MnemonicUtil() {

    }

    /**
     * テキストからニーモニックとアクセラレータを削除します。<br />
     * 
     * @param text
     *            テキスト
     * @return 削除結果
     */
    public static String chopMnemonicAndAccelerator(final String text) {
        return chopMnemonic(chopAccelerator(text));
    }

    /**
     * テキストからニーモニックプレフィックス(&記号)を削除します。<br />
     * 【例】「ファイル(&F)」の場合、結果は「ファイル(F)」となります。
     * 
     * @param text
     *            テキスト
     * @return 削除結果
     */
    public static String chopMnemonic(final String text) {
        return text.replace(AMPERSAND, NULL_STRING);
    }

    /**
     * テキストからアクセラレータ部分を削除します。<br />
     * 【例】「開く(&O)\tCtrl-O」の場合、結果は「Ctrl-O」となります。
     * 
     * @param title
     *            テキスト
     * @return 削除結果
     */
    public static String chopAccelerator(final String title) {
        int startPos = title.indexOf("\\t");
        if (startPos > 0) {
            return title.substring(0, startPos);
        }
        return title;
    }

    /**
     * テキストからニーモニックを取り出します。<br />
     * ニーモニックは最初の「&」(アンパサンド)に続く一文字です。<br />
     * 【例】「ファイル(&F)」の場合、結果は「F」となります。
     * 
     * @param text
     *            テキスト
     * @return ニーモニック。見つからない場合は空文字列。
     */
    public static String getMnemonic(final String text) {
        int startPos = text.indexOf(AMPERSAND);
        if (startPos > 0) {
            return text.substring(startPos + 1, startPos + 2);
        }
        return NULL_STRING;
    }
}
