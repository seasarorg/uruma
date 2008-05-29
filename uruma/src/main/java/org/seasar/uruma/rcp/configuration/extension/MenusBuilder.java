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
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.rcp.binding.CommandDesc;
import org.seasar.uruma.rcp.binding.CommandRegistry;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionBuilder;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.elements.CategoryElement;
import org.seasar.uruma.rcp.configuration.elements.CommandElement;
import org.seasar.uruma.rcp.configuration.elements.ContextElement;
import org.seasar.uruma.rcp.configuration.elements.KeyElement;
import org.seasar.uruma.rcp.configuration.elements.MenuCommandElement;
import org.seasar.uruma.rcp.configuration.elements.MenuContributionElement;
import org.seasar.uruma.rcp.configuration.elements.MenuElement;
import org.seasar.uruma.rcp.configuration.elements.SchemeElement;
import org.seasar.uruma.rcp.configuration.elements.ToolbarElement;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;
import org.seasar.uruma.util.MnemonicUtil;

/**
 * <code>menus</code> 拡張ポイントを生成するための {@link ExtensionBuilder} です。<br />
 * 
 * @author y-komori
 * @author y.sugigami
 */
public class MenusBuilder extends AbstractExtensionBuilder implements
        UrumaConstants {

    /**
     * コンテクスト ID のサフィックスです。<br />
     */
    public static final String CONTEXT_SUFFIX = ".context";

    /**
     * デフォルトのコマンド ID サフィックスです。<br />
     */
    public static final String DEFAULT_COMMAND_ID_SUFFIX = ".command.";

    /**
     * デフォルトのメニュー ID サフィックスです。<br />
     */
    public static final String DEFAULT_MENUS_ID_SUFFIX = ".menus.";

    /**
     * デフォルトのカテゴリ ID サフィックスです。<br />
     */
    public static String DEFAULT_CATEGORY_ID_SUFFIX = ".command.category";

    protected static final String menuURI = "menu:org.eclipse.ui.main.menu?after=additions";

    protected static final String toolbarURI = "toolbar:org.eclipse.ui.main.toolbar?after=additions";

    protected int actionCount;

    protected Extension contexts;

    protected Extension commands;

    protected Extension handlers;

    protected Extension bindings;

    protected Extension menus;

    protected CommandRegistry commandRegistry;

    protected String localContextId;

    /*
     * @see org.seasar.uruma.rcp.configuration.ExtensionBuilder#buildExtension()
     */
    public Extension[] buildExtension() {
        this.commandRegistry = service.getCommandRegistry();

        createExtensions();

        setupScheme();
        setupContexts();

        MenuContributionElement menuContribution = setupMenuContribution();
        MenuContributionElement toolbarContribution = setupToolbarContribution();

        CategoryElement category = setupCategory();

        WorkbenchComponent workbenchComponent = service.getWorkbenchComponent();
        for (MenuComponent menu : workbenchComponent.getMenus()) {
            traverseMenu(category, menu, null, menuContribution);
            traverseToolbar(category, menu, null, toolbarContribution);
        }

        for (ViewPartComponent view : service.getViewPartComponent()) {
            MenuContributionElement viewMenuContribution = setupViewMenuContribution(view);
            MenuContributionElement viewToolbarContribution = setupViewToolbarContribution(view);
            for (MenuComponent menu : view.getMenus()) {
                traverseMenu(category, menu, null, viewMenuContribution);
                traverseToolbar(category, menu, null, viewToolbarContribution);
            }
        }
        return new Extension[] { contexts, commands, handlers, bindings, menus };
    }

    protected void createExtensions() {
        contexts = ExtensionFactory.createExtension(ExtensionPoints.CONTEXTS);
        commands = ExtensionFactory.createExtension(ExtensionPoints.COMMANDS);
        handlers = ExtensionFactory.createExtension(ExtensionPoints.HANDLERS);
        bindings = ExtensionFactory.createExtension(ExtensionPoints.BINDINGS);
        menus = ExtensionFactory.createExtension(ExtensionPoints.MENUS);
    }

    protected void setupScheme() {
        SchemeElement scheme = new SchemeElement(URUMA_APP_SCHEME_ID,
                URUMA_APP_SCHEME_NAME);
        bindings.addElement(scheme);
    }

    protected CategoryElement setupCategory() {
        String pluginId = service.getPluginId();
        CategoryElement category = new CategoryElement(pluginId
                + DEFAULT_CATEGORY_ID_SUFFIX, pluginId);
        commands.addElement(category);
        return category;
    }

    protected MenuContributionElement setupMenuContribution() {
        MenuContributionElement menuContribution = new MenuContributionElement();
        menuContribution.locationURI = menuURI;
        this.menus.addElement(menuContribution);
        return menuContribution;
    }

    protected MenuContributionElement setupToolbarContribution() {
        MenuContributionElement menuContribution = new MenuContributionElement();
        menuContribution.locationURI = toolbarURI;
        this.menus.addElement(menuContribution);
        return menuContribution;
    }

    protected MenuContributionElement setupViewToolbarContribution(
            final ViewPartComponent view) {
        MenuContributionElement menuContribution = new MenuContributionElement();
        menuContribution.locationURI = "toolbar:"
                + service.createRcpId(view.getId()) + "?after=additions";
        this.menus.addElement(menuContribution);
        return menuContribution;
    }

    protected MenuContributionElement setupViewMenuContribution(
            final ViewPartComponent view) {
        MenuContributionElement menuContribution = new MenuContributionElement();
        menuContribution.locationURI = "menu:"
                + service.createRcpId(view.getId()) + "?after=additions";
        this.menus.addElement(menuContribution);
        return menuContribution;
    }

    protected void traverseMenu(final CategoryElement category,
            final MenuComponent menuComponent,
            final MenuElement parentMenuElement,
            final MenuContributionElement menuContribution) {
        List<UIElement> children = menuComponent.getChildren();
        for (UIElement child : children) {
            if (child instanceof MenuComponent) {
                MenuElement menuElement = setupMenu((MenuComponent) child,
                        parentMenuElement, menuContribution);
                traverseMenu(category, (MenuComponent) child, menuElement,
                        menuContribution);
            } else if (child instanceof MenuItemComponent) {
                MenuItemComponent menuItem = (MenuItemComponent) child;
                String commandId = createCommandId(menuItem);

                setupCommand(category, commandId, menuItem);
                setupKey(commandId, menuItem);
                setupMenuCommand(commandId, menuItem, parentMenuElement);
            }
        }
    }

    protected void traverseToolbar(final CategoryElement category,
            final MenuComponent menuComponent,
            final ToolbarElement parentToolbarElement,
            final MenuContributionElement menuContribution) {
        List<UIElement> children = menuComponent.getChildren();
        for (UIElement child : children) {
            if (child instanceof MenuComponent) {
                ToolbarElement toolbarElement;
                if (toolbarURI.equals(menuContribution.locationURI)) {
                    // Workbench Toolbar
                    toolbarElement = setupToolbar((MenuComponent) child,
                            parentToolbarElement, menuContribution);
                } else {
                    // View Toolbar
                    toolbarElement = parentToolbarElement;
                }
                traverseToolbar(category, (MenuComponent) child,
                        toolbarElement, menuContribution);
            } else if (child instanceof MenuItemComponent) {
                MenuItemComponent menuItem = (MenuItemComponent) child;
                String commandId = createCommandId(menuItem);

                setupCommand(category, commandId, menuItem);
                setupKey(commandId, menuItem);
                setupToolbarCommand(commandId, menuItem, parentToolbarElement,
                        menuContribution);
            }
        }
    }

    protected MenuElement setupMenu(final MenuComponent component,
            final MenuElement parentElement,
            final MenuContributionElement menuContribution) {
        if (!(component.getParent() instanceof WorkbenchComponent)) {
            String text = component.text;
            if (text == null) {
                text = NULL_STRING;
            }
            MenuElement menu = new MenuElement(MnemonicUtil
                    .chopMnemonicAndAccelerator(text));
            if (!StringUtil.isEmpty(component.getId())) {
                menu.id = service.createRcpId(component.getId());
            }

            if (!StringUtil.isEmpty(text)) {
                menu.mnemonic = MnemonicUtil.getMnemonic(text);
            }

            if (parentElement == null) {
                // トップレベルメニューの場合
                menuContribution.addElement(menu);
            } else {
                // サブメニューの場合
                parentElement.addElement(menu);
            }

            return menu;
        } else {
            return null;
        }
    }

    protected ToolbarElement setupToolbar(final MenuComponent component,
            final ToolbarElement parentElement,
            final MenuContributionElement menuContribution) {
        if (!(component.getParent() instanceof WorkbenchComponent)) {
            String id = service.getPluginId() + ".toolbar." + component.text;
            ToolbarElement toolbar = new ToolbarElement(id);

            if (parentElement == null) {
                // トップレベルメニューの場合
                menuContribution.addElement(toolbar);
            } else {
                // サブメニューの場合
                parentElement.addElement(toolbar);
            }
            return toolbar;
        } else {
            return null;
        }
    }

    protected String createCommandId(final MenuItemComponent menuItem) {
        String id = menuItem.getId();
        if (StringUtil.isEmpty(id)) {
            id = AUTO_ACTION_ID_PREFIX + actionCount++;
            menuItem.setId(id);
        }
        id = service.getPluginId() + DEFAULT_COMMAND_ID_SUFFIX + id;
        return id;
    }

    protected void setupCommand(final CategoryElement category,
            final String commandId, final MenuItemComponent component) {
        for (ConfigurationElement ce : commands.getElements()) {
            if (ce instanceof CommandElement) {
                CommandElement existCommandElement = (CommandElement) ce;
                if (existCommandElement.id.equals(commandId)) {
                    return;
                }
            }
        }

        String name = MnemonicUtil.chopMnemonicAndAccelerator(component.text);

        CommandElement command = new CommandElement(commandId, name);
        command.categoryId = category.id;
        commands.addElement(command);

        CommandDesc desc = new CommandDesc(commandId, component.getId());
        commandRegistry.registerCommandDesc(desc);
    }

    protected void setupContexts() {
        this.localContextId = UrumaServiceUtil.getService().getPluginId()
                + CONTEXT_SUFFIX;
        ContextElement context = new ContextElement(localContextId,
                "Uruma allication local context");
        contexts.addElement(context);
    }

    protected void setupKey(final String commandId,
            final MenuItemComponent menuItem) {
        if (!StringUtil.isEmpty(menuItem.accelerator)) {
            for (ConfigurationElement ce : bindings.getElements()) {
                if (ce instanceof KeyElement) {
                    KeyElement existKeyElement = (KeyElement) ce;
                    if (existKeyElement.commandId.equals(commandId)) {
                        return;
                    }
                }
            }

            String sequence = menuItem.accelerator;
            KeyElement key = new KeyElement(sequence, URUMA_APP_SCHEME_ID);
            key.commandId = commandId;
            // key.contextId = localContextId;

            bindings.addElement(key);
        }
    }

    protected void setupMenuCommand(final String commandId,
            final MenuItemComponent menuItem,
            final MenuElement parentMenuElement) {
        MenuCommandElement command = new MenuCommandElement(commandId);
        if (!StringUtil.isEmpty(menuItem.text)) {
            command.mnemonic = MnemonicUtil.getMnemonic(menuItem.text);
        }

        command.icon = getRcpImagePath(menuItem.image);
        command.disabledIcon = getRcpImagePath(menuItem.disabledImage);
        command.hoverIcon = getRcpImagePath(menuItem.hoverImage);

        parentMenuElement.addElement(command);
    }

    protected void setupToolbarCommand(final String commandId,
            final MenuItemComponent menuItem,
            final ToolbarElement parentToolbarElement,
            final MenuContributionElement menuContribution) {
        MenuCommandElement command = new MenuCommandElement(commandId);
        if (!StringUtil.isEmpty(menuItem.text)) {
            command.mnemonic = MnemonicUtil.getMnemonic(menuItem.text);
        }

        command.icon = getRcpImagePath(menuItem.image);
        command.disabledIcon = getRcpImagePath(menuItem.disabledImage);
        command.hoverIcon = getRcpImagePath(menuItem.hoverImage);
        command.style = menuItem.getStyle() == null ? MenuCommandElement.STYLE_PUSH
                : menuItem.getStyle();

        if (parentToolbarElement == null) {
            menuContribution.addElement(command);
        } else {
            parentToolbarElement.addElement(command);
        }

    }

}
