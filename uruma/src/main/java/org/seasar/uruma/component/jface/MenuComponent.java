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
package org.seasar.uruma.component.jface;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.util.AssertionUtil;

/**
 * メニュー情報を保持するためのコンポーネントです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 * @see <a
 *      href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_actionSets.html">ActionSets</a>
 */
public class MenuComponent extends MenuItemComponent implements
        UIComponentContainer {
    /**
     * デフォルトアイテムIDです。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String defaultItemId;

    /**
     * 可視状態です。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String visible;

    /**
     * メニューの X 表示座標です。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String x;

    /**
     * メニューの Y 表示座標です。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String y;

    /**
     * メニューのパスです。<br />
     */
    @ConfigurationAttribute(name = "path")
    public String menuPath;

    private List<UIElement> children = new ArrayList<UIElement>();

    protected List<ConfigurationElement> configElements = new ArrayList<ConfigurationElement>();

    /*
     * @see org.seasar.uruma.component.UIElementContainer#addChild(org.seasar.uruma.component.UIElement)
     */
    public void addChild(final UIElement child) {
        AssertionUtil.assertNotNull("child", child);
        AssertionUtil.assertInstanceOf("child", MenuItemComponent.class, child);

        this.children.add(child);
    }

    /*
     * @see org.seasar.uruma.component.UIElementContainer#getChildren()
     */
    public List<UIElement> getChildren() {
        return this.children;
    }

    /*
     * @see org.seasar.uruma.component.jface.AbstractUIComponent#doPreRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.WindowContext)
     */
    @Override
    protected void doPreRender(final WidgetHandle parent,
            final WindowContext context) {
        UIComponentContainer parentComponent = getParent();

        if (parentComponent instanceof WindowComponent) {
            WindowComponent windowComponent = (WindowComponent) parentComponent;
            if (StringUtil.isEmpty(windowComponent.getMenu())) {
                windowComponent.setMenu(getId());
            }
        } else if (parentComponent instanceof WorkbenchComponent) {
            WorkbenchComponent workbench = (WorkbenchComponent) parentComponent;
            if (StringUtil.isEmpty(workbench.menu)) {
                workbench.menu = getId();
            }
        }

        for (UIElement child : children) {
            if (child instanceof UIComponent) {
                ((UIComponent) child).preRender(context
                        .getWidgetHandle(getId()), context);
            }
        }
    }

    @Override
    public void writeConfiguration(final Writer writer) {
        if (configurationWriter != null) {
            configurationWriter.writeStartTag(this, writer);
        }

        for (ConfigurationElement element : configElements) {
            element.writeConfiguration(writer);
        }

        if (configurationWriter != null) {
            configurationWriter.writeEndTag(this, writer);
        }
    }

    /**
     * 子要素となる {@link ConfigurationElement} を追加します。<br />
     * 
     * @param element
     *            {@link ConfigurationElement} オブジェクト
     */
    public void addConfigurationElement(final ConfigurationElement element) {
        AssertionUtil.assertNotNull("element", element);
        configElements.add(element);
    }
}
