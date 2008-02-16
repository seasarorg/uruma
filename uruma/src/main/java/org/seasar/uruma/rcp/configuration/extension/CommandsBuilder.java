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

import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.MenuComponent;
import org.seasar.uruma.component.jface.MenuItemComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionBuilder;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.impl.CategoryElement;
import org.seasar.uruma.rcp.configuration.impl.CommandElement;

/**
 * <code>org.eclipse.ui.commands</code> 拡張ポイントのための {@link ExtensionBuilder}
 * です。<br />
 * 
 * @author y-komori
 */
public class CommandsBuilder extends AbstractExtensionBuilder {
    /**
     * デフォルトのカテゴリ ID サフィックスです。<br />
     */
    public String DEFAULT_CATEGORY_ID_SUFFIX = ".command.category";

    /*
     * @see org.seasar.uruma.rcp.configuration.ExtensionBuilder#buildExtension()
     */
    public Extension buildExtension() {
        Extension extension = ExtensionFactory
                .createExtension(ExtensionPoints.COMMANDS);

        String pluginId = service.getPluginId();
        CategoryElement category = new CategoryElement(pluginId
                + DEFAULT_CATEGORY_ID_SUFFIX, pluginId);
        extension.addElement(category);

        WorkbenchComponent workbenchComponent = service.getWorkbenchComponent();
        for (MenuComponent menu : workbenchComponent.getMenus()) {
            traverseMenu(extension, category, menu);
        }

        return extension;
    }

    protected void traverseMenu(final Extension extension,
            final CategoryElement category, final MenuComponent menu) {
        List<UIElement> children = menu.getChildren();
        for (UIElement child : children) {
            if (child instanceof MenuComponent) {
                traverseMenu(extension, category, (MenuComponent) child);
            } else if (child instanceof MenuItemComponent) {
                setupCommand(extension, category, (MenuItemComponent) child);
            }
        }
    }

    protected void setupCommand(final Extension extension,
            final CategoryElement category, final MenuItemComponent menuItem) {
        CommandElement command = new CommandElement(menuItem);
        command.categoryId = category.id;
        extension.addElement(command);
    }
}
