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
package org.seasar.uruma.renderer.impl;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.MenuItem;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.binding.method.GenericAction;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.jface.MenuItemComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.renderer.RendererSupportUtil;

/**
 * {@link MenuItem} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class MenuItemRenderer extends AbstractRenderer {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#preRender(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.WindowContext)
     */
    @Override
    public WidgetHandle preRender(final UIComponent uiComponent,
            final WidgetHandle parent, final WindowContext context) {
        setWindowContext(context);

        MenuItemComponent menuItemComponent = (MenuItemComponent) uiComponent;
        IAction action = new GenericAction(null, getStyle(menuItemComponent));

        setText(action, menuItemComponent);
        setAccelerator(action, menuItemComponent);
        setChecked(action, menuItemComponent);
        setEnabled(action, menuItemComponent);
        setImageDescriptor(action, menuItemComponent);
        setDisabledImageDescriptor(action, menuItemComponent);

        MenuManager parentMenuManager = parent.<MenuManager> getCastWidget();
        parentMenuManager.add(action);

        WidgetHandle handle = createWidgetHandle(uiComponent, action);

        if (uiComponent instanceof EnablesDependable) {
            setupEnablesDependingDef(handle, (EnablesDependable) uiComponent);
        }

        return handle;
    }

    /*
     * @see org.seasar.uruma.renderer.Renderer#render(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    public WidgetHandle render(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {
        // Do nothing.
        return null;
    }

    /*
     * @see org.seasar.uruma.renderer.Renderer#renderAfter(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    public void renderAfter(final WidgetHandle widget,
            final UIComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        // Do nothing.
    }

    protected int getStyle(final MenuItemComponent menuItemComponent) {
        String style = menuItemComponent.getStyle();
        if (MenuItemComponent.PUSH.equals(style)) {
            return IAction.AS_PUSH_BUTTON;
        } else if (MenuItemComponent.RADIO.equals(style)) {
            return IAction.AS_RADIO_BUTTON;
        } else if (MenuItemComponent.CHECK.equals(style)) {
            return IAction.AS_CHECK_BOX;
        } else {
            return IAction.AS_PUSH_BUTTON;
        }
    }

    protected void setText(final IAction action,
            final MenuItemComponent menuItemComponent) {
        String text = menuItemComponent.text;
        if (!StringUtil.isEmpty(text)) {
            action.setText(RendererSupportUtil.convertText(text));
        }
    }

    protected void setAccelerator(final IAction action,
            final MenuItemComponent menuItemComponent) {
        String accelStr = menuItemComponent.accelerator;
        if (accelStr != null) {
            action.setAccelerator(RendererSupportUtil
                    .convertAccelerator(accelStr));
        }
    }

    protected void setChecked(final IAction action,
            final MenuItemComponent menuItemComponent) {
        String selection = menuItemComponent.selection;
        if (selection != null) {
            action.setChecked(RendererSupportUtil.convertBoolean(selection));
        }
    }

    protected void setDescription(final IAction action,
            final MenuItemComponent menuItemComponent) {
        if (menuItemComponent.description != null) {
            action.setDescription(RendererSupportUtil
                    .convertText(menuItemComponent.description));
        }
    }

    protected void setEnabled(final IAction action,
            final MenuItemComponent menuItemComponent) {
        String checked = menuItemComponent.enabled;
        if (checked != null) {
            action.setEnabled(RendererSupportUtil.convertBoolean(checked));
        }
    }

    protected void setImageDescriptor(final IAction action,
            final MenuItemComponent menuItemComponent) {
        String path = menuItemComponent.image;
        if (!StringUtil.isEmpty(path)) {
            ImageDescriptor desc = RendererSupportUtil.convertImageDescriptor(
                    path, menuItemComponent.getBasePath());
            action.setImageDescriptor(desc);
        }
    }

    protected void setDisabledImageDescriptor(final IAction action,
            final MenuItemComponent menuItemComponent) {
        String path = menuItemComponent.disabledImage;
        if (!StringUtil.isEmpty(path)) {
            ImageDescriptor desc = RendererSupportUtil.convertImageDescriptor(
                    path, menuItemComponent.getBasePath());
            action.setDisabledImageDescriptor(desc);
        }
    }

    protected void setHoverImageDescriptor(final IAction action,
            final MenuItemComponent menuItemComponent) {
        String path = menuItemComponent.disabledImage;
        if (!StringUtil.isEmpty(path)) {
            ImageDescriptor desc = RendererSupportUtil.convertImageDescriptor(
                    path, menuItemComponent.getBasePath());
            action.setHoverImageDescriptor(desc);
        }
    }

}
