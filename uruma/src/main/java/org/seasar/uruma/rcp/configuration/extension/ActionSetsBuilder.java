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
package org.seasar.uruma.rcp.configuration.extension;

import java.util.List;

import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.MenuComponent;
import org.seasar.uruma.component.jface.MenuItemComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionBuilder;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.impl.ActionElement;
import org.seasar.uruma.rcp.configuration.impl.ActionSetElement;
import org.seasar.uruma.rcp.configuration.impl.GroupMarkerElement;
import org.seasar.uruma.rcp.configuration.impl.MenuElement;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;

/**
 * <code>actionSets</code> 拡張ポイントを生成するための {@link ExtensionBuilder} です。<br />
 * 
 * @author y-komori
 */
public class ActionSetsBuilder implements ExtensionBuilder, UrumaConstants {
    static final String START_MARKER = "startMarker";

    protected UrumaService service;

    protected int menuCount;

    protected int actionCount;

    /**
     * {@link ActionSetsBuilder} を構築します。<br />
     */
    public ActionSetsBuilder() {
        this.service = UrumaServiceUtil.getService();
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ExtensionBuilder#buildExtension()
     */
    public Extension buildExtension() {
        this.menuCount = 0;
        this.actionCount = 0;

        return setupActionSets();
    }

    protected Extension setupActionSets() {
        Extension extension = ExtensionFactory
                .createExtension(ExtensionPoints.ACTIONSETS);

        ActionSetElement actionSet = createActionSet();

        WorkbenchComponent workbenchComponent = service.getWorkbenchComponent();
        for (MenuComponent menu : workbenchComponent.getMenus()) {
            List<UIElement> children = menu.getChildren();
            for (UIElement child : children) {
                if (child instanceof MenuComponent) {
                    setupMenu(actionSet, (MenuComponent) child, null);
                }
            }
        }

        extension.addConfigurationElement(actionSet);

        return extension;
    }

    protected ActionSetElement createActionSet() {
        ActionSetElement actionSet = new ActionSetElement();
        actionSet.label = service.getPluginId() + "'s actionSet.";
        actionSet.setRcpId(service.getPluginId() + ".actionSet");
        return actionSet;
    }

    protected void setupMenu(final ActionSetElement actionSet,
            final MenuComponent menuComponent, final String path) {
        MenuElement menu = new MenuElement(menuComponent);

        // Menu の id が設定されていない場合は自動設定する
        if (StringUtil.isEmpty(menu.id)) {
            menu.id = AUTO_MENU_ID_PREFIX + menuCount++;
        }
        menu.id = service.createRcpId(menu.id);

        if (path != null) {
            menu.path = path;
        }

        GroupMarkerElement marker = new GroupMarkerElement();
        marker.name = START_MARKER;
        menu.addChild(marker);

        actionSet.addChild(menu);

        List<UIElement> children = menuComponent.getChildren();
        for (UIElement child : children) {
            String childPath = chopStartMarker(path) + SLASH + START_MARKER;
            if (child instanceof MenuComponent) {
                setupMenu(actionSet, (MenuComponent) child, childPath);
            } else if (child instanceof MenuItemComponent) {
                setupMenuItem(actionSet, (MenuItemComponent) child, childPath);
            }
        }
    }

    protected void setupMenuItem(final ActionSetElement actionSet,
            final MenuItemComponent menuItem, final String path) {
        ActionElement action = new ActionElement(menuItem);

        // MenuItem の id が設定されていない場合は自動設定する
        if (StringUtil.isEmpty(menuItem.getId())) {
            action.id = AUTO_ACTION_ID_PREFIX + actionCount++;
        }
        action.id = service.createRcpId(action.id);

        if (path != null) {
            action.menubarPath = path;
        }

        actionSet.addChild(action);
    }

    protected String chopStartMarker(final String path) {
        if (path.endsWith(SLASH + START_MARKER)) {
            return path.substring(0, path.length()
                    - (SLASH + START_MARKER).length());
        } else {
            return path;
        }
    }
}
