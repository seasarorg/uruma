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
package org.seasar.uruma.rcp.configuration;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.configuration.impl.ViewElement;

/**
 * {@link Bundle} に対してコントリビューションを動的に追加するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ContributionBuilder {
    private static final int BUFFER_SIZE = 4096;

    private static final UrumaLogger logger = UrumaLogger
            .getLogger(ContributionBuilder.class);

    private ContributionBuilder() {

    }

    public static void build(final Bundle bundle,
            final List<Extension> extensions) {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        StringBuilder builder = new StringBuilder(BUFFER_SIZE);

        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<?eclipse version=\"3.2\"?>\n");
        builder.append("<plugin>\n");

        for (Extension extension : extensions) {
            writeExtension(extension, builder);
        }

        builder.append("</plugin>\n");

        if (logger.isDebugEnabled()) {
            logger.log(UrumaMessageCodes.CREATE_CONTRIBUTION, builder
                    .toString());
        }

        ByteArrayInputStream is = null;
        try {
            is = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ignore) {
        }

        Object token = ((ExtensionRegistry) registry).getTemporaryUserToken();
        IContributor contributorOsgi = ContributorFactoryOSGi
                .createContributor(bundle);
        registry.addContribution(is, contributorOsgi, false, null, null, token);
    }

    private static void writeExtension(final Extension extension,
            final StringBuilder builder) {
        builder.append("<extension point=\"" + extension.getPoint());
        builder.append("\">\n");

        List<ConfigurationElement> elements = extension
                .getConfigurationElements();
        for (ConfigurationElement element : elements) {
            // TODO 要素毎にクラスをわけてファクトリから得るようにする
            if (element instanceof ViewElement) {
                writeViewElement((ViewElement) element, builder);
            }
        }

        builder.append("</extension>\n");
    }

    private static void writeViewElement(final ViewElement element,
            final StringBuilder builder) {
        builder.append("<view class=\"" + element.className + "\" ");
        builder.append("id=\"" + element.id + "\" ");
        builder.append("name=\"" + element.name + "\" />\n");
    }
}
