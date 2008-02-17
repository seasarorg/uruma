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
import org.seasar.uruma.rcp.binding.GenericHandler;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionBuilder;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.impl.CommandElement;
import org.seasar.uruma.rcp.configuration.impl.HandlerElement;

/**
 * <code>org.eclipse.ui.handlers</code> 拡張ポイントのための {@link ExtensionBuilder}
 * です。<br />
 * 
 * @author y-komori
 */
public class HandlersBuilder extends AbstractExtensionBuilder {
    protected int actionCount;

    /*
     * @see org.seasar.uruma.rcp.configuration.ExtensionBuilder#buildExtension()
     */
    public Extension buildExtension() {
        Extension extension = ExtensionFactory
                .createExtension(ExtensionPoints.HANDLERS);

        WorkbenchComponent workbenchComponent = service.getWorkbenchComponent();
        for (MenuComponent menu : workbenchComponent.getMenus()) {
            traverseMenu(extension, menu);
        }

        return extension;
    }

    protected void traverseMenu(final Extension extension,
            final MenuComponent menu) {
        List<UIElement> children = menu.getChildren();
        for (UIElement child : children) {
            if (child instanceof MenuComponent) {
                traverseMenu(extension, (MenuComponent) child);
            } else if (child instanceof MenuItemComponent) {
                setupHandler(extension, (MenuItemComponent) child);
            }
        }
    }

    protected void setupHandler(final Extension extension,
            final MenuItemComponent menuItem) {
        // MenuItem の id が設定されていない場合は自動設定する
        String id = menuItem.getId();
        if (StringUtil.isEmpty(id)) {
            id = AUTO_ACTION_ID_PREFIX + actionCount++;
        }
        id = service.getPluginId() + CommandElement.DEFAULT_COMMAND_ID_SUFFIX
                + id;
        HandlerElement handler = new HandlerElement(id);
        handler.clazz = GenericHandler.class.getName();

        extension.addElement(handler);
    }

}
