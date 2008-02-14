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
package org.seasar.uruma.rcp.configuration.impl;

import org.eclipse.ui.IViewPart;
import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;

/**
 * <code>view</code> 要素のための {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori <a
 *         href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_views.html#e.view">view</a>
 */
public class ViewElement extends AbstractConfigurationElement implements
        UrumaConstants {

    /**
     * {@link ViewElement} を構築します。<br />
     */
    public ViewElement() {
        super();
    }

    /**
     * {@link ViewPartComponent} を元にして {@link ViewElement} を構築します。<br />
     * 
     * @param component
     *            {@link ViewPartComponent} オブジェクト
     */
    public ViewElement(final ViewPartComponent component) {
        super();
        this.id = createRcpId(component.getId());
        this.name = component.title;
        this.category = component.category;
        this.clazz = component.clazz;
        this.icon = component.image;
        this.allowMultiple = Boolean.getBoolean(component.allowMultiple);
    }

    /**
     * ビューの ID です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String id;

    /**
     * ビューの名称です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String name;

    /**
     * ビューのカテゴリ名称です。<br />
     */
    @ConfigurationAttribute
    public String category;

    /**
     * {@link IViewPart} 実装クラスのフルネームです。<br />
     */
    @ConfigurationAttribute(name = "class", required = true)
    public String clazz;

    /**
     * アイコンのパスです。<br />
     */
    @ConfigurationAttribute
    public String icon;

    /**
     * 高速ビュー表示時の表示割合です。<br />
     */
    @ConfigurationAttribute
    public String fastViewWidthRatio;

    /**
     * ビューの複数表示を許可するかどうかのフラグです。<br />
     */
    @ConfigurationAttribute
    public boolean allowMultiple;

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name + " : " + id;
    }
}
