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
package org.seasar.uruma.debug.action;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.osgi.framework.Bundle;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.binding.context.ApplicationContextDef;
import org.seasar.uruma.binding.enables.EnablesDependingDef;
import org.seasar.uruma.binding.method.EventListenerDef;
import org.seasar.uruma.binding.method.GenericAction;
import org.seasar.uruma.binding.value.BindingCommand;
import org.seasar.uruma.binding.value.command.ExportSelectionCommand;
import org.seasar.uruma.binding.value.command.ExportValueCommand;
import org.seasar.uruma.binding.value.command.ImportSelectionCommand;
import org.seasar.uruma.binding.value.command.ImportValueCommand;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.MenuComponent;
import org.seasar.uruma.component.jface.MenuItemComponent;
import org.seasar.uruma.component.rcp.PartComponent;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.desc.FormDesc;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.exception.BindingException;
import org.seasar.uruma.rcp.configuration.ContributionBuilder;
import org.seasar.uruma.rcp.util.BundleUtil;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;
import org.seasar.uruma.ui.UrumaApplicationWindow;
import org.seasar.uruma.util.HtmlTagUtil;
import org.seasar.uruma.util.S2ContainerListToHtmlUtil;

/**
 * デバッグビュー用のアクションクラスです。<br />
 * 
 * @author y.sugigami
 */
public class UrumaDebugViewAction {

    /* IMPORT EXPORT */
    @ExportValue(id = "debugBrowser")
    public String html;

    public Browser debugBrowser;

    /**
     * 初期化処理
     */
    @InitializeMethod
    public void initialize() {
        doS2Container();
    }

    /**
     * s2container <br />
     */
    @EventListener(id = "s2container", type = EventListenerType.SELECTION)
    public void doS2Container() {
        html = S2ContainerListToHtmlUtil.list("");
    }

    /**
     * bundle <br />
     */
    @EventListener(id = "bundle", type = EventListenerType.SELECTION)
    public void doBundle() {
        html = HtmlTagUtil.createHeader();
        html += HtmlTagUtil.createH1("Workspace Path");

        // TODO 要実装

        // bundles
        String symbolicName = UrumaServiceUtil.getService().getPluginId();
        Bundle urumaBundle = BundleUtil.getBundle(symbolicName);
        Bundle[] bundles = urumaBundle.getBundleContext().getBundles();
        for (Bundle bundle : bundles) {
            html += HtmlTagUtil.createH1("[" + convertStatus(bundle.getState())
                    + "][" + bundle.getBundleId() + "] "
                    + bundle.getSymbolicName());
            Date date = new Date(bundle.getLastModified());
            html += HtmlTagUtil.createTable("[" + date + "]", "["
                    + bundle.getLocation() + "]");
            Dictionary headers = bundle.getHeaders();
            Enumeration elements = headers.keys();
            while (elements.hasMoreElements()) {
                String key = (String) elements.nextElement();
                String value = (String) headers.get(key);
                html += HtmlTagUtil.createTr(key, value);
            }
            html += HtmlTagUtil.closeTable();
        }
        html += HtmlTagUtil.closeHeader();
    }

    private String convertStatus(final int i) {
        String result = "";
        switch (i) {
        case 32:
            result = "ACTIVE";
            break;

        case 8:
            result = "LAZY";
            break;

        case 4:
            result = "RESOLVED";
            break;

        default:
            result = String.valueOf(i);
            break;
        }
        return result;
    }

    /**
     * component <br />
     */
    @EventListener(id = "component", type = EventListenerType.SELECTION)
    public void doComponent() {
        html = HtmlTagUtil.createHeader();

        //
        // WorkbenchComponent
        //
        WorkbenchComponent workbenchComponent = UrumaServiceUtil.getService()
                .getWorkbenchComponent();

        html += HtmlTagUtil.createH1("WorkbenchComponent");

        html += HtmlTagUtil.createTable();
        html += HtmlTagUtil.createTr("id", workbenchComponent.getId());
        html += HtmlTagUtil.createTr("ClassName", workbenchComponent.getClass()
                .getName());
        html += HtmlTagUtil.createTr("basePath", workbenchComponent
                .getBasePath());
        html += HtmlTagUtil.createTr("Location", workbenchComponent
                .getLocation());
        html += HtmlTagUtil.createTr("Path", workbenchComponent.getPath());
        html += HtmlTagUtil.createTr("Renderer", workbenchComponent
                .getRenderer().getClass().getName());

        html += HtmlTagUtil.createTr("title", workbenchComponent.title);
        html += HtmlTagUtil.createTr("image", workbenchComponent.image);
        html += HtmlTagUtil.createTr("initHeight",
                workbenchComponent.initHeight);
        html += HtmlTagUtil.createTr("initWidth", workbenchComponent.initWidth);
        html += HtmlTagUtil.createTr("initialPerspectiveId",
                workbenchComponent.initialPerspectiveId);
        html += HtmlTagUtil.createTr("menu", workbenchComponent.menu);
        html += HtmlTagUtil.createTr("statusLine",
                workbenchComponent.statusLine);
        html += HtmlTagUtil.createTr("style", workbenchComponent.style);

        html += HtmlTagUtil.closeTable();

        List<UIElement> list = workbenchComponent.getChildren();
        for (UIElement element : list) {
            createUIElement(element);
        }

        html += HtmlTagUtil.closeHeader();
    }

    private void createUIElement(final UIElement element) {
        if (MenuComponent.class.isAssignableFrom(element.getClass())) {
            MenuComponent c = (MenuComponent) element;
            html += HtmlTagUtil.createH2("MenuComponent");
            html += HtmlTagUtil.createTable();
            html += HtmlTagUtil.createTr("id", c.getId());
            html += HtmlTagUtil.createTr("ClassName", c.getClass().getName());

            html += HtmlTagUtil.createTr("BasePath", c.getBasePath());
            html += HtmlTagUtil.createTr("Location", c.getLocation());
            html += HtmlTagUtil.createTr("Path", c.getPath());

            html += HtmlTagUtil.createTr("BasePath", c.getBasePath());
            html += HtmlTagUtil.createTr("Location", c.getLocation());
            html += HtmlTagUtil.createTr("Path", c.getPath());

            html += HtmlTagUtil.createTr("accelerator", c.accelerator);
            html += HtmlTagUtil.createTr("defaultItemId", c.defaultItemId);
            html += HtmlTagUtil.createTr("description", c.description);
            html += HtmlTagUtil.createTr("disabledImage", c.disabledImage);
            html += HtmlTagUtil.createTr("enabled", c.enabled);
            html += HtmlTagUtil.createTr("enablesDependingId",
                    c.enablesDependingId);
            html += HtmlTagUtil.createTr("enablesFor", c.enablesFor);
            html += HtmlTagUtil.createTr("hoverImage", c.hoverImage);
            html += HtmlTagUtil.createTr("image", c.image);
            html += HtmlTagUtil.createTr("selection", c.selection);
            html += HtmlTagUtil.createTr("text", c.text);
            html += HtmlTagUtil.createTr("visible", c.visible);
            html += HtmlTagUtil.createTr("Renderer", c.getRenderer().getClass()
                    .getName());
            html += HtmlTagUtil.closeTable();

            for (UIElement elementChild : c.getChildren()) {
                createUIElement(elementChild);
            }

        } else if (MenuItemComponent.class.isAssignableFrom(element.getClass())) {
            MenuItemComponent c = (MenuItemComponent) element;
            html += HtmlTagUtil.createH2("MenuItemComponent");
            html += HtmlTagUtil.createTable();
            html += HtmlTagUtil.createTr("id", c.getId());
            html += HtmlTagUtil.createTr("ClassName", c.getClass().getName());

            html += HtmlTagUtil.createTr("BasePath", c.getBasePath());
            html += HtmlTagUtil.createTr("Location", c.getLocation());
            html += HtmlTagUtil.createTr("Path", c.getPath());

            html += HtmlTagUtil.createTr("accelerator", c.accelerator);
            html += HtmlTagUtil.createTr("description", c.description);
            html += HtmlTagUtil.createTr("disabledImage", c.disabledImage);
            html += HtmlTagUtil.createTr("enabled", c.enabled);
            html += HtmlTagUtil.createTr("enablesDependingId",
                    c.enablesDependingId);
            html += HtmlTagUtil.createTr("enablesFor", c.enablesFor);
            html += HtmlTagUtil.createTr("hoverImage", c.hoverImage);
            html += HtmlTagUtil.createTr("image", c.image);
            html += HtmlTagUtil.createTr("selection", c.selection);
            html += HtmlTagUtil.createTr("text", c.text);
            html += HtmlTagUtil.createTr("Renderer", c.getRenderer().getClass()
                    .getName());
            html += HtmlTagUtil.closeTable();

        } else if (PerspectiveComponent.class.isAssignableFrom(element
                .getClass())) {
            PerspectiveComponent c = (PerspectiveComponent) element;

            html += HtmlTagUtil.createH2("PerspectiveComponent");
            html += HtmlTagUtil.createTable();
            html += HtmlTagUtil.createTr("id", c.id);
            html += HtmlTagUtil.createTr("ClassName", c.getClass().getName());

            html += HtmlTagUtil.createTr("BasePath", c.getBasePath());
            html += HtmlTagUtil.createTr("Location", c.getLocation());
            html += HtmlTagUtil.createTr("Path", c.getPath());

            html += HtmlTagUtil.createTr("name", c.name);
            html += HtmlTagUtil.createTr("fixed", c.fixed);
            html += HtmlTagUtil.createTr("iconicon", c.icon);
            html += HtmlTagUtil.createTr("clazz", c.clazz);
            html += HtmlTagUtil.closeTable();
            for (UIElement elementChild : c.getChildren()) {
                createUIElement(elementChild);
            }

        } else if (PartComponent.class.isAssignableFrom(element.getClass())) {
            PartComponent c = (PartComponent) element;

            html += HtmlTagUtil.createH3("PartComponent");
            html += HtmlTagUtil.createTable();
            html += HtmlTagUtil.createTr("ClassName", c.getClass().getName());
            html += HtmlTagUtil.createTr("BasePath", c.getBasePath());
            html += HtmlTagUtil.createTr("Location", c.getLocation());
            html += HtmlTagUtil.createTr("Path", c.getPath());

            html += HtmlTagUtil.createTr("ref", c.ref);
            html += HtmlTagUtil.createTr("position", c.position);
            html += HtmlTagUtil.createTr("ratio", c.ratio);
            html += HtmlTagUtil.closeTable();

        } else if (ViewPartComponent.class.isAssignableFrom(element.getClass())) {
            ViewPartComponent c = (ViewPartComponent) element;

            html += HtmlTagUtil.createH3("ViewPartComponent");
            html += HtmlTagUtil.createTable();
            html += HtmlTagUtil.createTr("id", c.getId());
            html += HtmlTagUtil.createTr("ClassName", c.getClass().getName());
            html += HtmlTagUtil.createTr("BasePath", c.getBasePath());
            html += HtmlTagUtil.createTr("Location", c.getLocation());
            html += HtmlTagUtil.createTr("Path", c.getPath());
            html += HtmlTagUtil.createTr("Renderer", c.getRenderer().getClass()
                    .getName());

            html += HtmlTagUtil.createTr("position", c.allowMultiple);
            html += HtmlTagUtil.createTr("background", c.background);
            html += HtmlTagUtil.createTr("backgroundImage", c.backgroundImage);
            html += HtmlTagUtil.createTr("category", c.category);
            html += HtmlTagUtil.createTr("enabled", c.enabled);
            html += HtmlTagUtil.createTr("enablesDependingId",
                    c.enablesDependingId);
            html += HtmlTagUtil.createTr("enablesFor", c.enablesFor);
            html += HtmlTagUtil.createTr("fontHeight", c.fontHeight);
            html += HtmlTagUtil.createTr("fontName", c.fontName);
            html += HtmlTagUtil.createTr("fontStyle", c.fontStyle);
            html += HtmlTagUtil.createTr("foreground", c.foreground);
            html += HtmlTagUtil.createTr("height", c.height);
            html += HtmlTagUtil.createTr("image", c.image);
            html += HtmlTagUtil.createTr("menu", c.menu);
            html += HtmlTagUtil.createTr("title", c.title);
            html += HtmlTagUtil.createTr("toolTipText", c.toolTipText);
            html += HtmlTagUtil.createTr("visible", c.visible);
            html += HtmlTagUtil.createTr("width", c.width);
            html += HtmlTagUtil.createTr("x", c.x);
            html += HtmlTagUtil.createTr("y", c.y);
            html += HtmlTagUtil.closeTable();
            for (UIElement elementChild : c.getChildren()) {
                createUIElement(elementChild);
            }
        } else {
            html += element.getClass().getName() + "<br />";
        }
    }

    private static final ImportValueCommand IMPORT_VALUE_COMMAND = new ImportValueCommand();

    private static final ExportValueCommand EXPORT_VALUE_COMMAND = new ExportValueCommand();

    private static final ImportSelectionCommand IMPORT_SELECTION_COMMAND = new ImportSelectionCommand();

    private static final ExportSelectionCommand EXPORT_SELECTION_COMMAND = new ExportSelectionCommand();

    /**
     * context <br />
     */
    @EventListener(id = "context", type = EventListenerType.SELECTION)
    public void doCcontext() {
        html = HtmlTagUtil.createHeader();
        //
        // ApplicationContext
        //
        WindowContext wc = UrumaServiceUtil.getService()
                .getWorkbenchWindowContext();
        ApplicationContext ac = wc.getApplicationContext();

        html += HtmlTagUtil.createH1("ApplicationContext");
        html += HtmlTagUtil.createTable();
        html += HtmlTagUtil.createTr("ClassName", ac.getClass().getName());
        html += HtmlTagUtil.closeTable();

        //
        // WindowContext
        //
        html += HtmlTagUtil.createH1("WindowContext");
        html += HtmlTagUtil.createTable("Name", wc.getName());
        html += HtmlTagUtil.createTr("ClassName", wc.getClass().getName());
        html += HtmlTagUtil.createTr("WorkbenchActionObject", wc
                .getPartActionObject().getClass().getName());

        // PartContext
        for (PartContext pc : wc.getPartContextList()) {
            html += HtmlTagUtil.createTr("PartContext Name", pc.getName());
        }

        html += HtmlTagUtil.closeTable();

        // EnablesDependingDef
        for (EnablesDependingDef ed : wc.getEnablesDependingDefList()) {
            html += HtmlTagUtil.createH1("EnablesDependingDef");
            html += HtmlTagUtil.createTable();
            html += HtmlTagUtil.createTr("ClassName", ed.getClass().getName());
            html += HtmlTagUtil.createTr("targetId", ed.getTargetId());
            html += HtmlTagUtil.createTr("type", ed.getType().name());

            // WidgetHandle
            createWidgetHandle(ed.getWidgetHandle());

            html += HtmlTagUtil.closeTable();
        }

        // PartContext
        for (PartContext pc : wc.getPartContextList()) {
            PartActionDesc partActionDesc = pc.getPartActionDesc();
            FormDesc formDesc = pc.getFormDesc();
            html += HtmlTagUtil.createH1("PartContext");
            html += HtmlTagUtil.createTable("Name", pc.getName());
            html += HtmlTagUtil.createTr("ClassName", pc.getClass().getName());

            html += HtmlTagUtil.createTrSub1("PartActionClass Name",
                    partActionDesc.getPartActionClass().getSimpleName());
            html += HtmlTagUtil.createTr("partActionObject", pc
                    .getPartActionObject().getClass().getSimpleName());
            String initializeMethodName = " ";
            if (partActionDesc.getInitializeMethod() != null) {
                initializeMethodName = partActionDesc.getInitializeMethod()
                        .getName()
                        + "()";
            }
            html += HtmlTagUtil.createTr("InitializeMethodName",
                    initializeMethodName);

            String postOpenMethodName = " ";
            if (partActionDesc.getPostOpenMethod() != null) {
                postOpenMethodName = partActionDesc.getPostOpenMethod()
                        .getName()
                        + "()";
            }
            html += HtmlTagUtil.createTr("PostOpenMethodName",
                    postOpenMethodName);

            for (EventListenerDef eld : partActionDesc
                    .getEventListenerDefList()) {
                html += HtmlTagUtil.createTrSub1(
                        "EventListenerDef TargetMethodName", eld
                                .getTargetMethod().getName());
                html += HtmlTagUtil.createTr("Type", eld.getType().getName());
                html += HtmlTagUtil.createTr("isSWTEvent", String.valueOf(eld
                        .getType().isSWTEvent()));
            }

            for (ApplicationContextDef acd : partActionDesc
                    .getApplicationContextDefList()) {
                html += HtmlTagUtil.createTrSub1("ApplicationContextDef Name",
                        acd.getName());
                html += HtmlTagUtil.createTr("PropertyName", acd
                        .getPropertyDesc().getPropertyName());
            }

            html += HtmlTagUtil.createTrSub1("FormClass Name", formDesc
                    .getFormClass().getSimpleName());
            html += HtmlTagUtil.createTr("formObject", pc.getFormObject()
                    .getClass().getSimpleName());

            dealFields(pc, IMPORT_VALUE_COMMAND);
            dealFields(pc, EXPORT_VALUE_COMMAND);
            dealFields(pc, IMPORT_SELECTION_COMMAND);
            dealFields(pc, EXPORT_SELECTION_COMMAND);

            for (WidgetHandle wh : pc.getWidgetHandles()) {
                // WidgetHandle
                createWidgetHandle(wh);
            }

            html += HtmlTagUtil.closeTable();
        }

        html += HtmlTagUtil.closeHeader();
    }

    private void dealFields(final PartContext context,
            final BindingCommand command) {
        Object form = context.getFormObject();
        FormDesc formDesc = context.getFormDesc();
        if (form == null || formDesc == null) {
            return;
        }
        html += HtmlTagUtil.createTrSub1("BindingCommand Class Name", command
                .getClass().getSimpleName());

        List<PropertyDesc> targetProperties = command
                .getTargetPropertyDescs(formDesc);
        for (PropertyDesc pd : targetProperties) {
            String id = command.getId(pd.getField());

            WidgetHandle handle = context.getWidgetHandle(id);
            if (handle != null) {
                Object widget = handle.getWidget();

                html += HtmlTagUtil.createTrSub2("PropertyName", pd
                        .getPropertyName());
                html += HtmlTagUtil.createTr("WidgetName", widget.getClass()
                        .getSimpleName());
            } else {
                throw new BindingException(UrumaMessageCodes.WIDGET_NOT_FOUND,
                        id, form.getClass(), pd.getField());
            }
        }
    }

    private void createWidgetHandle(final WidgetHandle wh) {
        html += HtmlTagUtil.createTrSub1("WidgetHandle Id", wh.getId());
        html += HtmlTagUtil.createTr("Class Name", wh.getClass().getName());
        html += HtmlTagUtil.createTr("widgetClass", wh.getWidgetClass()
                .getName());

        if (StructuredViewer.class.isAssignableFrom(wh.getWidgetClass())) {
            StructuredViewer viewer = wh.<StructuredViewer> getCastWidget();
            html += HtmlTagUtil.createTr("LabelProvider", getSimpleName(viewer
                    .getLabelProvider()));
            html += HtmlTagUtil.createTr("ContentProvider",
                    getSimpleName(viewer.getContentProvider()));
            html += HtmlTagUtil.createTr("Comparator", getSimpleName(viewer
                    .getComparator()));
            html += HtmlTagUtil.createTr("Sorter", getSimpleName(viewer
                    .getSorter()));

        } else if (GenericAction.class.isAssignableFrom(wh.getWidgetClass())) {
            // TODO 要実装
        } else if (UrumaApplicationWindow.class.isAssignableFrom(wh
                .getWidgetClass())) {
            // TODO 要実装
        } else if (Widget.class.isAssignableFrom(wh.getWidgetClass())) {
            // TODO 要実装
        } else if (MenuManager.class.isAssignableFrom(wh.getWidgetClass())) {
            // TODO 要実装
        } else if (Control.class.isAssignableFrom(wh.getWidgetClass())) {
            // TODO 要実装
        }

        UIComponent uc = wh.getUiComponent();
        if (uc != null) {
            html += HtmlTagUtil.createTrSub2("UIComponent id", uc.getId());
            html += HtmlTagUtil.createTr("Class Name", uc.getClass().getName());
            html += HtmlTagUtil.createTr("BasePath", uc.getBasePath());
            html += HtmlTagUtil.createTr("Location", uc.getLocation());
            html += HtmlTagUtil.createTr("Path", uc.getPath());
            html += HtmlTagUtil.createTr("style", uc.getStyle());
            html += HtmlTagUtil.createTr("renderer", uc.getRenderer()
                    .getClass().getName());
        }
    }

    private String getSimpleName(final Object obj) {
        if (obj != null) {
            return obj.getClass().getSimpleName();
        }
        return " ";
    }

    /**
     * template <br />
     */
    @EventListener(id = "template", type = EventListenerType.SELECTION)
    public void doTemplate() {

        //
        // TemplateManager
        //
        html = HtmlTagUtil.createHeader();
        html += HtmlTagUtil.createH1("TemplateManager");

        html += HtmlTagUtil.createTable();

        TemplateManager templateManager = (TemplateManager) UrumaServiceUtil
                .getService().getContainer()
                .getComponent(TemplateManager.class);
        List<Template> viewTemplates = templateManager
                .getTemplates(ViewPartComponent.class);

        for (Template template : viewTemplates) {
            html += HtmlTagUtil.createTrSub1("Path", template.getPath());
            html += HtmlTagUtil.createTr("Location", template.getLocation());
            html += HtmlTagUtil.createTr("BasePath", template.getBasePath());
            html += HtmlTagUtil.createTr("Extends", template.getExtends());

            UIComponentContainer uic = template.getRootComponent();
            createChildTemplateManager(uic);
        }

        html += HtmlTagUtil.closeTable();
        html += HtmlTagUtil.closeTable();
    }

    private void createChildTemplateManager(final Object uic) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(uic.getClass());
        html += HtmlTagUtil.createTrSub2("ClassName", uic.getClass()
                .getSimpleName());
        for (int i = 0; i < beanDesc.getFieldSize(); i++) {
            Field filed = beanDesc.getField(i);

            String value = "";
            Object obj = null;
            if (filed.isAccessible()) {
                try {
                    obj = filed.get(uic);
                    if (obj != null) {
                        value = obj.getClass().getSimpleName();
                        value += ": " + obj.toString();
                    } else {
                        value = "null";
                    }
                } catch (IllegalArgumentException ignore) {
                } catch (IllegalAccessException ignore) {
                }
            }

            html += HtmlTagUtil.createTr(filed.getName(), value);
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                for (int j = 0; j < list.size(); j++) {
                    Object array_element = list.get(j);

                    html += HtmlTagUtil.createTr("array_element", array_element
                            .toString());
                    createChildTemplateManager(array_element);
                }
            }
        }
    }

    /**
     * plugin <br />
     */
    @EventListener(id = "plugin", type = EventListenerType.SELECTION)
    public void doPlugin() {
        html = HtmlTagUtil.createHeader();
        html += "<pre>";
        html += ContributionBuilder.getContent().replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
        html += "</pre>";
        html += HtmlTagUtil.closeHeader();
    }

}
