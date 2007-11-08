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

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.desc.PartActionDescFactory;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.S2ContainerUtil;

/**
 * パートアクションクラスに関するユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class ComponentUtil {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(ComponentUtil.class);

    private static S2Container defaultContainer;

    private ComponentUtil() {
    }

    /**
     * 各種コンポーネントを検索する際の {@link S2Container} を設定します。<br />
     * 本クラスの他のメソッドを使用する前に呼び出してください。<br />
     * 
     * @param container
     *            {@link S2Container} オブジェクト
     */
    public static void setS2Container(final S2Container container) {
        AssertionUtil.assertNotNull("container", container);

        defaultContainer = container;
    }

    /**
     * パートアクションクラスを準備します。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     * @param id
     *            対応するパートの ID
     * @return パートアクションクラスが見つかった場合、そのオブジェクト。<br />
     *         見つからなかった場合は <code>null</code>
     */
    public static Object setupPartAction(final PartContext context,
            final String id) {
        String actionComponentName = StringUtil.decapitalize(id)
                + UrumaConstants.PART_ACTION_SUFFIX;

        Object partActionComponent = S2ContainerUtil.getComponentNoException(
                actionComponentName, defaultContainer);
        if (partActionComponent != null) {
            context.setPartActionObject(partActionComponent);
            PartActionDesc desc = PartActionDescFactory
                    .getPartActionDesc(partActionComponent.getClass());
            context.setPartActionDesc(desc);

            logger.log(UrumaMessageCodes.PART_ACTION_CLASS_FOUND, id,
                    actionComponentName, partActionComponent.getClass()
                            .getName());

            return partActionComponent;
        } else {
            return null;
        }
    }

    /**
     * フォームクラスを準備します。<br />
     * また、パートアクションクラスにフォームクラスのプロパティが存在する場合、 そのプロパティにフォームオブジェクトをインジェクションします。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     * @param id
     *            対応するパートの ID
     * @return フォームクラスが見つかった場合、そのオブジェクト。<br />
     *         見つからなかった場合は <code>null</code>
     */
    public static Object setupFormComponent(final PartContext context,
            final String id) {
        Object formObject = null;
        Object actionObject = context.getPartActionObject();
        if (actionObject != null) {
            Form formAnnotation = actionObject.getClass().getAnnotation(
                    Form.class);
            if (formAnnotation != null) {
                Class<?> formClass = formAnnotation.value();
                if (formClass == actionObject.getClass()) {
                    formObject = actionObject;
                } else {
                    formObject = S2ContainerUtil.getComponent(formClass,
                            defaultContainer);
                }
            }
        }

        if (formObject == null) {
            String formComponentName = StringUtil.decapitalize(id)
                    + UrumaConstants.FORM_SUFFIX;
            formObject = S2ContainerUtil.getComponentNoException(
                    formComponentName, defaultContainer);
        }

        if (formObject != null) {
            logger.log(UrumaMessageCodes.FORM_CLASS_FOUND, id, formObject
                    .getClass().getName());

            context.setFormObject(formObject);
            injectFormToAction(context);
            return formObject;
        } else {
            return null;
        }
    }

    /**
     * パートアクションオブジェクトにフォームオブジェクトのプロパティが存在する場合、 {@link PartContext}
     * が保持するフォームオブジェクトをインジェクションします。
     */
    protected static void injectFormToAction(final PartContext context) {
        Object partActionObject = context.getPartActionObject();
        Object formObject = context.getFormObject();

        String formObjectName = formObject.getClass().getSimpleName();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(partActionObject
                .getClass());
        if (beanDesc.hasPropertyDesc(formObjectName)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(formObjectName);
            pd.setValue(partActionObject, formObject);
        }
    }

}
