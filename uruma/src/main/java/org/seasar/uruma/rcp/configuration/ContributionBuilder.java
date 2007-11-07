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
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;

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

    public static void build(final IContributor contributor,
            final List<Extension> extensions) {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        StringWriter writer = new StringWriter(BUFFER_SIZE);

        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<?eclipse version=\"3.2\"?>\n");
        writer.write("<plugin>\n");

        for (Extension extension : extensions) {
            extension.writeConfiguration(writer);
        }

        writer.write("</plugin>\n");

        String content = writer.getBuffer().toString();

        if (logger.isDebugEnabled()) {
            logger.log(UrumaMessageCodes.CREATE_CONTRIBUTION, content);
        }

        ByteArrayInputStream is = null;
        try {
            is = new ByteArrayInputStream(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ignore) {
        }

        Object token = ((ExtensionRegistry) registry).getTemporaryUserToken();

        registry.addContribution(is, contributor, false, null, null, token);
    }
}