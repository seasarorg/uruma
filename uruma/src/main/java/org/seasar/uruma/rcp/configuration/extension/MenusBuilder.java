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
import org.seasar.uruma.rcp.binding.GenericHandler;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionBuilder;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.impl.CategoryElement;
import org.seasar.uruma.rcp.configuration.impl.CommandElement;
import org.seasar.uruma.rcp.configuration.impl.HandlerElement;
import org.seasar.uruma.rcp.configuration.impl.KeyElement;
import org.seasar.uruma.util.MnemonicUtil;

/**
 * <code>menus</code> 拡張ポイントを生成するための {@link ExtensionBuilder} です。<br />
 * 
 * @author y-komori
 */
public class MenusBuilder extends AbstractExtensionBuilder implements
        UrumaConstants {

    /**
     * デフォルトのコマンド ID サフィックスです。<br />
     */
    public static final String DEFAULT_COMMAND_ID_SUFFIX = ".command.";

    /**
     * デフォルトのカテゴリ ID サフィックスです。<br />
     */
    public String DEFAULT_CATEGORY_ID_SUFFIX = ".command.category";

    protected int actionCount;

    protected Extension commands;

    protected Extension handlers;

    protected Extension bindings;

    /*
     * @see org.seasar.uruma.rcp.configuration.ExtensionBuilder#buildExtension()
     */
    public Extension[] buildExtension() {
        createExtensions();

        CategoryElement category = setupCategory();

        WorkbenchComponent workbenchComponent = service.getWorkbenchComponent();
        for (MenuComponent menu : workbenchComponent.getMenus()) {
            traverseMenu(category, menu);
        }

        return new Extension[] { commands, handlers, bindings };
    }

    protected void createExtensions() {
        commands = ExtensionFactory.createExtension(ExtensionPoints.COMMANDS);
        handlers = ExtensionFactory.createExtension(ExtensionPoints.HANDLERS);
        bindings = ExtensionFactory.createExtension(ExtensionPoints.BINDINGS);
    }

    protected CategoryElement setupCategory() {
        String pluginId = service.getPluginId();
        CategoryElement category = new CategoryElement(pluginId
                + DEFAULT_CATEGORY_ID_SUFFIX, pluginId);
        commands.addElement(category);
        return category;
    }

    protected void traverseMenu(final CategoryElement category,
            final MenuComponent menu) {
        List<UIElement> children = menu.getChildren();
        for (UIElement child : children) {
            if (child instanceof MenuComponent) {
                traverseMenu(category, (MenuComponent) child);
            } else if (child instanceof MenuItemComponent) {
                MenuItemComponent menuItem = (MenuItemComponent) child;
                String commandId = createCommandId(menuItem);

                setupCommand(category, commandId, menuItem);
                setupHandler(commandId, menuItem);
                setupKey(commandId, menuItem);
            }
        }
    }

    protected String createCommandId(final MenuItemComponent menuItem) {
        String id = menuItem.getId();
        if (StringUtil.isEmpty(id)) {
            id = AUTO_ACTION_ID_PREFIX + actionCount++;
        }
        id = service.getPluginId() + DEFAULT_COMMAND_ID_SUFFIX + id;
        return id;
    }

    protected void setupCommand(final CategoryElement category,
            final String commandId, final MenuItemComponent component) {
        String name = MnemonicUtil.chopAccelerator(MnemonicUtil
                .chopMnemonic(component.getText()));

        CommandElement command = new CommandElement(commandId, name);
        command.categoryId = category.id;
        commands.addElement(command);
    }

    protected void setupHandler(final String commandId,
            final MenuItemComponent menuItem) {
        HandlerElement handler = new HandlerElement(commandId);
        handler.clazz = GenericHandler.class.getName();

        handlers.addElement(handler);
    }

    protected void setupKey(final String commandId,
            final MenuItemComponent menuItem) {
        String sequence = "";
        KeyElement key = new KeyElement(sequence,
                "org.eclipse.ui.defaultAcceleratorConfiguration");
        key.commandId = commandId;

        bindings.addElement(key);
    }
}
