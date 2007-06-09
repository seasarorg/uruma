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
package org.seasar.jface.util;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * S2Container を利用するためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class S2ContainerUtil {
    /**
     * 指定された名前に対応するコンポーネントを <code>S2Container</code> から取得して返します。<br />
     * <p>
     * コンポーネントが存在しない場合は例外をスローせず、<code>null</code> を返します。
     * </p>
     * 
     * @param componentName
     *            コンポーネント名称
     * @return コンポーネントオブジェクト
     */
    public static Object getComponentNoException(final String componentName) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container.hasComponentDef(componentName)) {
            return container.getComponentDef(componentName).getComponent();
        } else {
            return null;
        }
    }

    /**
     * 指定された名前に対応するコンポーネントを <code>S2Container</code> から取得して返します。<br />
     * 
     * @param componentName
     *            コンポーネント名称
     * @return コンポーネントオブジェクト
     */
    public static ComponentDef getComponentDef(final String componentName) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return container.getComponentDef(componentName);
    }

    /**
     * 指定されたクラスに対応するコンポーネントを <code>S2Container</code> から取得して返します。<br />
     * 
     * @param componentClass
     *            コンポーネントクラス
     * @return コンポーネントオブジェクト
     */
    public static ComponentDef getComponentDef(final Class componentClass) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return container.getComponentDef(componentClass);
    }

    /**
     * 指定されたクラスに対応するコンポーネントを <code>S2Container</code> から取得して返します。<br />
     * 
     * @param componentClass
     *            コンポーネントクラス
     * @return コンポーネントオブジェクト
     */
    public static Object getComponent(final Class componentClass) {
        return getComponentDef(componentClass).getComponent();
    }
}
