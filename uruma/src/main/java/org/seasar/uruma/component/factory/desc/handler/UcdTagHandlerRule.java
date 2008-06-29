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
package org.seasar.uruma.component.factory.desc.handler;

import org.seasar.framework.xml.TagHandlerRule;

/**
 * Uruma コンポーネントディスクリプタのための {@link TagHandlerRule} です。<br />
 * 
 * @author y-komori
 */
public class UcdTagHandlerRule extends TagHandlerRule {

    private static final long serialVersionUID = -8601482965667832088L;

    /**
     * {@link UcdTagHandlerRule} を構築します。<br />
     */
    public UcdTagHandlerRule() {
        addTagHandler("urumaComponent", new UrumaComponentTagHandler());
        addTagHandler("tagName", new TagNameTagHandler());
        addTagHandler("tagHandler", new TagHandlerTagHandler());
        addTagHandler("/urumaComponent/tagHandler/arg",
                new TagHandlerArgTagHandler());
        addTagHandler("renderer", new RendererTagHandler());
        addTagHandler("/urumaComponent/renderer/arg",
                new RendererArgTagHandler());
    }
}
