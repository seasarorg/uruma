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

/**
 * メニューのニーモニックを扱うためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class MnemonicUtil {
    private MnemonicUtil() {

    }

    /**
     * テキストからニーモニック部分を削除します。<br />
     * 
     * @param text
     *            テキスト
     * @return 削除結果
     */
    public static String chopMnemonic(final String text) {
        int startPos = text.indexOf("(&");
        if (startPos > 0) {
            int endPos = text.indexOf(")", startPos);
            if (endPos > 0) {
                return text.substring(0, startPos) + text.substring(endPos + 1);
            }
        }
        return text;
    }

    /**
     * テキストからアクセラレータ部分を削除します。<br />
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
}
