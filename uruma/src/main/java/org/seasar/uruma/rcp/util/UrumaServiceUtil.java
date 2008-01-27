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

import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.util.S2ContainerUtil;

/**
 * {@link UrumaService} を取得するためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaServiceUtil {
    private UrumaServiceUtil() {

    }

    /**
     * {@link UrumaService} のインスタンスを取得します。<br />
     * {@link UrumaService} は RCP 環境でのみ利用可能です。<br />
     * RCP環境でない場合、本メソッドの戻り値は <code>null</code> となります。<br />
     * 
     * @return {@link UrumaService} のインスタンス。RCP 環境でない場合は <code>null</code>。
     */
    public static UrumaService getService() {
        return (UrumaService) S2ContainerUtil
                .getComponentNoException(UrumaService.class);
    }
}
