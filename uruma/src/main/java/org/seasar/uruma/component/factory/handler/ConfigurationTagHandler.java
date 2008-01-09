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
package org.seasar.uruma.component.factory.handler;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.exception.UnsupportedClassException;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;
import org.seasar.uruma.rcp.configuration.ConfigurationWriterFactory;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;
import org.xml.sax.Attributes;

/**
 * {@link ConfigurationElement} を実装したコンポーネントに対応するタグのためのタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class ConfigurationTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = -8005172410028962982L;

    /**
     * {@link ConfigurationTagHandler} を構築します。<br />
     * 
     * @param uiElementClass
     *            対応するクラス
     */
    public ConfigurationTagHandler(
            final Class<? extends UIElement> uiElementClass) {
        super(uiElementClass);

        if (!ConfigurationElement.class.isAssignableFrom(uiElementClass)) {
            throw new UnsupportedClassException(uiElementClass);
        }
    }

    /*
     * @see org.seasar.uruma.component.factory.handler.GenericTagHandler#start(org.seasar.framework.xml.TagHandlerContext,
     *      org.xml.sax.Attributes)
     */
    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        super.start(context, attributes);

        ConfigurationElement element = (ConfigurationElement) context.peek();

        setupRcpId(element, context);
        setupConfigurationWriter(element);
    }

    protected void setupConfigurationWriter(final ConfigurationElement element) {
        ConfigurationWriter writer = ConfigurationWriterFactory
                .getConfigurationWriter(element);
        element.setConfigurationWriter(writer);
    }

    protected void setupRcpId(final ConfigurationElement element,
            final TagHandlerContext context) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(element.getClass());
        if (desc.hasPropertyDesc("id")) {
            PropertyDesc pd = desc.getPropertyDesc("id");
            String id = (String) pd.getValue(element);
            if (StringUtil.isNotBlank(id)) {
                element.setRcpId(UrumaServiceUtil.getService()
                        .createRcpId(id));
            }
        }
    }
}
