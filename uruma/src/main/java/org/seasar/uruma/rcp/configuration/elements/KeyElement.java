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
package org.seasar.uruma.rcp.configuration.elements;

import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.util.AssertionUtil;

/**
 * <code>key</code> 要素のための {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori
 * @see <a 
 *  href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_bindings.html#e.key"
 *  >key</a>
 */
public class KeyElement extends AbstractConfigurationElement {
    /**
     * 要素名です。<br />
     */
    public static final String ELEMENT_NAME = "key";

    /**
     * キーシーケンスです。<br />
     */
    @ConfigurationAttribute(required = true)
    public String sequence;

    /**
     * スキーム ID です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String schemeId;

    /**
     * コンテクスト ID です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String contextId;

    /**
     * コマンド ID です。<br />
     */
    @ConfigurationAttribute
    public String commandId;

    /**
     * キーバインディングを適用するプラットフォームです。<br />
     */
    @ConfigurationAttribute
    public String platform;

    /**
     * キーバインディングを適用するロケールです。<br />
     */
    @ConfigurationAttribute
    public String locale;

    /**
     * {@link KeyElement} を構築します。<br />
     * 
     * @param sequence
     *      キーシーケンス
     * @param schemeId
     *      スキームID
     * @param contextId
     *      コンテクスト ID     
     */
    public KeyElement(final String sequence, final String schemeId, final String contextId) {
        super();
        AssertionUtil.assertNotNull("sequence", sequence);
        AssertionUtil.assertNotNull("schemeId", schemeId);
        this.sequence = sequence;
        this.schemeId = schemeId;
        this.contextId = contextId;
    }
}
