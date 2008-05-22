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
package org.seasar.uruma.component.spec;

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.util.AssertionUtil;

/**
 * GUI コンポーネントの属性パラメータ情報を取得するためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class UIComponentSpecUtil {
    /**
     * 指定された GUI コンポーネントに関する属性パラメータ情報のリストを取得します。<br />
     * 
     * @param uiElementClass
     *            GUI コンポーネントの {@link Class} オブジェクト
     * @return 属性パラメータ情報のリスト
     */
    public static List<ParamSpec> getParamSpecs(
            final Class<? extends UIElement> uiElementClass) {
        AssertionUtil.assertNotNull("uiElementClass", uiElementClass);
        List<ParamSpec> result = new ArrayList<ParamSpec>();

        // TODO 要実装

        return result;
    }
}
