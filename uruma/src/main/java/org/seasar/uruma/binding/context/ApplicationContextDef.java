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
package org.seasar.uruma.binding.context;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ApplicationContext} アノテートされたプロパティを表すクラスです。<br />
 * 
 * @author y-komori
 */
public class ApplicationContextDef {
    private PropertyDesc propertyDesc;

    private String name;

    /**
     * {@link ApplicationContextDef} を構築します。<br />
     * 
     * @param pd
     *            {@link PropertyDesc} オブジェクト
     * @param name
     *            名称
     */
    public ApplicationContextDef(final PropertyDesc pd, final String name) {
        AssertionUtil.assertNotNull("pd", pd);
        AssertionUtil.assertNotEmpty("name", name);
        this.propertyDesc = pd;
        this.name = name;
    }

    /**
     * {@link PropertyDesc} を取得します。<br />
     * 
     * @return {@link PropertyDesc} オブジェクト
     */
    public PropertyDesc getPropertyDesc() {
        return this.propertyDesc;
    }

    /**
     * 名称を取得します。<br />
     * 
     * @return 名称
     */
    public String getName() {
        return this.name;
    }
}
