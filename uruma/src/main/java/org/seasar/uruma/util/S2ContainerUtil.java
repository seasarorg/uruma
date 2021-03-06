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

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * {@link S2Container} を利用するためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class S2ContainerUtil {
    private S2ContainerUtil() {

    }

    /**
     * 指定された名前に対応するコンポーネントを {@link S2Container} から取得して返します。<br />
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
        return getComponentNoException(componentName, container);
    }

    /**
     * 指定された名前に対応するコンポーネントを {@link S2Container} から取得して返します。<br />
     * <p>
     * コンポーネントが存在しない場合は例外をスローせず、<code>null</code> を返します。
     * </p>
     * 
     * @param componentName
     *            コンポーネント名称
     * @param container
     *            検索対象の {@link S2Container}
     * @return コンポーネントオブジェクト
     */
    public static Object getComponentNoException(final String componentName,
            final S2Container container) {
        if (container.hasComponentDef(componentName)) {
            return container.getComponentDef(componentName).getComponent();
        } else {
            return null;
        }
    }

    /**
     * 指定されたクラスに対応するコンポーネントを {@link S2Container} から取得して返します。<br />
     * コンポーネントが存在しない場合は例外をスローせず、 <code>null</code> を返します。
     * 
     * @param componentClass
     *            コンポーネントのクラス
     * @param container
     *            {@link S2Container}
     * @return コンポーネントオブジェクト
     */
    public static Object getComponentNoException(final Class<?> componentClass,
            final S2Container container) {
        if (container.hasComponentDef(componentClass)) {
            return container.getComponent(componentClass);
        } else {
            return null;
        }
    }

    /**
     * 指定されたクラスに対応するコンポーネントを {@link S2Container} から取得して返します。<br />
     * コンポーネントが存在しない場合は例外をスローせず、 <code>null</code> を返します。
     * 
     * @param componentClass
     *            コンポーネントのクラス
     * @return コンポーネントオブジェクト
     */
    public static Object getComponentNoException(final Class<?> componentClass) {
        return getComponentNoException(componentClass,
                SingletonS2ContainerFactory.getContainer());
    }

    /**
     * 指定された名前に対応するコンポーネントを {@link S2Container} から取得して返します。<br />
     * コンポーネントが存在しない場合は例外をスローせず、 <code>null</code> を返します。
     * 
     * @param componentName
     *            コンポーネント名称
     * @return コンポーネントオブジェクト
     */
    public static ComponentDef getComponentDefNoException(
            final String componentName) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container.hasComponentDef(componentName)) {
            return container.getComponentDef(componentName);
        } else {
            return null;
        }
    }

    /**
     * 指定されたクラスに対応するコンポーネントを {@link S2Container} から取得して返します。<br />
     * コンポーネントが存在しない場合は例外をスローせず、 <code>null</code> を返します。
     * 
     * @param componentClass
     *            コンポーネントクラス
     * @return コンポーネントオブジェクト
     */
    public static ComponentDef getComponentNoExceptionDef(
            final Class<?> componentClass) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container.hasComponentDef(componentClass)) {
            return container.getComponentDef(componentClass);
        } else {
            return null;
        }
    }

    /**
     * 指定された名前に対応するコンポーネントを {@link S2Container} から取得して返します。<br />
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
     * 指定されたクラスに対応するコンポーネントを {@link S2Container} から取得して返します。<br />
     * 
     * @param componentClass
     *            コンポーネントクラス
     * @return コンポーネントオブジェクト
     */
    public static ComponentDef getComponentDef(final Class<?> componentClass) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return container.getComponentDef(componentClass);
    }

    /**
     * 指定されたクラスに対応するコンポーネントを {@link S2Container} から取得して返します。<br />
     * 
     * @param componentClass
     *            コンポーネントクラス
     * @return コンポーネントオブジェクト
     */
    public static Object getComponent(final Class<?> componentClass) {
        return getComponent(componentClass, SingletonS2ContainerFactory
                .getContainer());
    }

    /**
     * 指定されたクラスに対応するコンポーネントを {@link S2Container} から取得して返します。<br />
     * 
     * @param componentClass
     *            コンポーネントクラス
     * @param container
     *            検索対象の {@link S2Container}
     * @return コンポーネントオブジェクト
     */
    public static Object getComponent(final Class<?> componentClass,
            final S2Container container) {
        return container.getComponent(componentClass);
    }

    /**
     * 指定されたオブジェクトに対して {@link S2Container} からコンポーネントをインジェクションします。<br />
     * <p>
     * <code>target</code> で指定されたオブジェクトに対して、<code>container</code> で指定された
     * {@link S2Container} からコンポーネントをインジェクションします。<br />
     * </p>
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param container
     *            {@link S2Container} オブジェクト
     */
    public static void injectDependency(final Object target,
            final S2Container container) {
        AssertionUtil.assertNotNull("target", target);
        AssertionUtil.assertNotNull("container", container);

        BeanDesc desc = BeanDescFactory.getBeanDesc(target.getClass());

        int pdSize = desc.getPropertyDescSize();
        for (int i = 0; i < pdSize; i++) {
            PropertyDesc pd = desc.getPropertyDesc(i);

            Class<?> clazz = pd.getPropertyType();
            Object component = getComponentNoException(clazz);
            if (component != null && pd.isWritable()) {
                pd.setValue(target, component);
            }
        }
    }
}
