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
package org.seasar.uruma.binding.context;

import java.util.List;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;

/**
 * 任意のオブジェクトと {@link ApplicationContext} の間でバインディングを行うためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ApplicationContextBinder {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(ApplicationContextBinder.class);

    private ApplicationContextBinder() {

    }

    /**
     * プロパティから {@link ApplicationContext} へ値をバインドします。<br />
     * 対象となるプロパティは、<code>defs</code> で指定される {@link ApplicationContextDef}
     * のリストです。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param defs
     *            {@link ApplicationContextDef} のリスト
     * @param context
     *            {@link ApplicationContext} オブジェクト
     */
    public static void exportObjects(final Object target,
            final List<ApplicationContextDef> defs,
            final ApplicationContext context) {
        for (ApplicationContextDef def : defs) {
            PropertyDesc pd = def.getPropertyDesc();
            String name = def.getName();
            Object value = pd.getValue(target);

            if (logger.isDebugEnabled()) {
                logger.log(UrumaMessageCodes.EXPORT_APPLICATION_CONTEXT,
                        UrumaLogger.getObjectDescription(target), pd
                                .getPropertyName(), name, value);
            }
            context.setValue(name, value);
        }
    }

    /**
     * {@link ApplicationContext} からプロパティへ値をバインドします。<br />
     * 対象となるプロパティは、<code>defs</code> で指定される {@link ApplicationContextDef}
     * のリストです。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param defs
     *            {@link ApplicationContextDef} のリスト
     * @param context
     *            {@link ApplicationContext} オブジェクト
     */
    public static void importObjects(final Object target,
            final List<ApplicationContextDef> defs,
            final ApplicationContext context) {
        for (ApplicationContextDef def : defs) {
            PropertyDesc pd = def.getPropertyDesc();
            String name = def.getName();
            Object value = context.getValue(name);

            if (logger.isDebugEnabled()) {
                logger.log(UrumaMessageCodes.IMPORT_APPLICATION_CONTEXT,
                        UrumaLogger.getObjectDescription(target), pd
                                .getPropertyName(), name, value);
            }

            Class<?> propertyType = pd.getPropertyType();
            if (value != null) {
                if (propertyType.isAssignableFrom(value.getClass())) {
                    pd.setValue(target, value);
                } else {
                    // TODO 例外処理
                }
            } else {
                pd.setValue(target, null);
            }
        }
    }
}
