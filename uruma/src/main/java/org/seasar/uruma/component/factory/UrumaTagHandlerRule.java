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
package org.seasar.uruma.component.factory;

import org.seasar.framework.xml.TagHandlerRule;
import org.seasar.uruma.component.factory.handler.CommonAttributesTagHandler;
import org.seasar.uruma.component.factory.handler.GenericTagHandler;
import org.seasar.uruma.component.factory.handler.GradientInfoTagHandler;
import org.seasar.uruma.component.factory.handler.GradientItemTagHandler;
import org.seasar.uruma.component.factory.handler.LayoutDataTagHandler;
import org.seasar.uruma.component.factory.handler.LayoutTagHandler;
import org.seasar.uruma.component.factory.handler.RootComponentTagHandler;
import org.seasar.uruma.component.factory.handler.SimpleItemTagHandler;
import org.seasar.uruma.component.factory.handler.TableCellTagHandler;
import org.seasar.uruma.component.factory.handler.TableColumnTagHandler;
import org.seasar.uruma.component.factory.handler.TemplateTagHandler;
import org.seasar.uruma.component.factory.handler.TreeItemTagHandler;
import org.seasar.uruma.component.jface.BrowserComponent;
import org.seasar.uruma.component.jface.ButtonComponent;
import org.seasar.uruma.component.jface.CTabFolderComponent;
import org.seasar.uruma.component.jface.CTabItemComponent;
import org.seasar.uruma.component.jface.CanvasComponent;
import org.seasar.uruma.component.jface.ComboComponent;
import org.seasar.uruma.component.jface.CompositeComponent;
import org.seasar.uruma.component.jface.CoolBarComponent;
import org.seasar.uruma.component.jface.CoolItemComponent;
import org.seasar.uruma.component.jface.DateTimeComponent;
import org.seasar.uruma.component.jface.FillLayoutInfo;
import org.seasar.uruma.component.jface.GridDataInfo;
import org.seasar.uruma.component.jface.GridLayoutInfo;
import org.seasar.uruma.component.jface.GroupComponent;
import org.seasar.uruma.component.jface.LabelComponent;
import org.seasar.uruma.component.jface.LinkComponent;
import org.seasar.uruma.component.jface.MenuComponent;
import org.seasar.uruma.component.jface.MenuItemComponent;
import org.seasar.uruma.component.jface.ProgressBarComponent;
import org.seasar.uruma.component.jface.RowDataInfo;
import org.seasar.uruma.component.jface.RowLayoutInfo;
import org.seasar.uruma.component.jface.SashFormComponent;
import org.seasar.uruma.component.jface.ScaleComponent;
import org.seasar.uruma.component.jface.SeparatorComponent;
import org.seasar.uruma.component.jface.SliderComponent;
import org.seasar.uruma.component.jface.SpinnerComponent;
import org.seasar.uruma.component.jface.TabFolderComponent;
import org.seasar.uruma.component.jface.TabItemComponent;
import org.seasar.uruma.component.jface.TableComponent;
import org.seasar.uruma.component.jface.TableItemComponent;
import org.seasar.uruma.component.jface.TextComponent;
import org.seasar.uruma.component.jface.ToolBarComponent;
import org.seasar.uruma.component.jface.ToolItemComponent;
import org.seasar.uruma.component.jface.TreeComponent;
import org.seasar.uruma.component.jface.WindowComponent;
import org.seasar.uruma.component.rcp.PartComponent;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;

/**
 * Uruma の画面定義XMLをパースするためのタグハンドラを保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaTagHandlerRule extends TagHandlerRule {
    private static final long serialVersionUID = -6918247426777293347L;

    /**
     * {@link UrumaTagHandlerRule} クラスを構築します。<br />
     */
    public UrumaTagHandlerRule() {
        // Root components
        addTagHandler(new TemplateTagHandler());
        addTagHandler("window", new RootComponentTagHandler(
                WindowComponent.class));
        addTagHandler("workbench", new RootComponentTagHandler(
                WorkbenchComponent.class));
        addTagHandler("viewPart", new RootComponentTagHandler(
                ViewPartComponent.class));

        // Common Attribute
        addTagHandler(new CommonAttributesTagHandler());

        // Layout
        addTagHandler("fillLayout", new LayoutTagHandler(FillLayoutInfo.class));
        addTagHandler("rowLayout", new LayoutTagHandler(RowLayoutInfo.class));
        addTagHandler("gridLayout", new LayoutTagHandler(GridLayoutInfo.class));

        // LayoutData
        addTagHandler("rowData", new LayoutDataTagHandler(RowDataInfo.class));
        addTagHandler("gridData", new LayoutDataTagHandler(GridDataInfo.class));

        // Composite
        addTagHandler("composite", new GenericTagHandler(
                CompositeComponent.class));
        addTagHandler("combo", new GenericTagHandler(ComboComponent.class));
        addTagHandler("tabFolder", new GenericTagHandler(
                TabFolderComponent.class));
        addTagHandler("tabItem", new GenericTagHandler(TabItemComponent.class));
        addTagHandler("table", new GenericTagHandler(TableComponent.class));
        addTagHandler("tableColumn", new TableColumnTagHandler());
        addTagHandler("tableItem", new GenericTagHandler(
                TableItemComponent.class));
        addTagHandler(new TableCellTagHandler());
        addTagHandler("tree", new GenericTagHandler(TreeComponent.class));
        addTagHandler("treeItem", new TreeItemTagHandler());
        addTagHandler("spinner", new GenericTagHandler(SpinnerComponent.class));
        addTagHandler("cTabFolder", new GenericTagHandler(
                CTabFolderComponent.class));
        addTagHandler("cTabItem",
                new GenericTagHandler(CTabItemComponent.class));
        addTagHandler(new GradientInfoTagHandler());
        addTagHandler(new GradientItemTagHandler());
        addTagHandler("group", new GenericTagHandler(GroupComponent.class));
        addTagHandler("toolBar", new GenericTagHandler(ToolBarComponent.class));
        addTagHandler("toolItem",
                new GenericTagHandler(ToolItemComponent.class));
        addTagHandler("coolBar", new GenericTagHandler(CoolBarComponent.class));
        addTagHandler("coolItem",
                new GenericTagHandler(CoolItemComponent.class));
        addTagHandler("canvas", new GenericTagHandler(CanvasComponent.class));
        addTagHandler("sashForm",
                new GenericTagHandler(SashFormComponent.class));

        // Control
        addTagHandler("label", new GenericTagHandler(LabelComponent.class));
        addTagHandler("button", new GenericTagHandler(ButtonComponent.class));
        addTagHandler("text", new GenericTagHandler(TextComponent.class));
        addTagHandler("progressBar", new GenericTagHandler(
                ProgressBarComponent.class));
        addTagHandler("scale", new GenericTagHandler(ScaleComponent.class));
        addTagHandler("slider", new GenericTagHandler(SliderComponent.class));
        addTagHandler("link", new GenericTagHandler(LinkComponent.class));
        addTagHandler("browser", new GenericTagHandler(BrowserComponent.class));
        addTagHandler("datetime",
                new GenericTagHandler(DateTimeComponent.class));

        // SimpleItem
        addTagHandler(new SimpleItemTagHandler());

        // Menu
        addTagHandler("menu", new GenericTagHandler(MenuComponent.class));
        addTagHandler("menuItem",
                new GenericTagHandler(MenuItemComponent.class));
        addTagHandler("separator", new GenericTagHandler(
                SeparatorComponent.class));

        // RCP
        addTagHandler("perspective", new GenericTagHandler(
                PerspectiveComponent.class));
        addTagHandler("part", new GenericTagHandler(PartComponent.class));
    }

    protected void addTagHandler(final UrumaTagHandler tagHandler) {
        addTagHandler(tagHandler.getElementPath(), tagHandler);
    }
}
