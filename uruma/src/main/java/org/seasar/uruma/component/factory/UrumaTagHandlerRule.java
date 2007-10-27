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
package org.seasar.uruma.component.factory;

import org.seasar.framework.xml.TagHandlerRule;
import org.seasar.uruma.component.factory.handler.CommonAttributesTagHandler;
import org.seasar.uruma.component.factory.handler.GenericTagHandler;
import org.seasar.uruma.component.factory.handler.GradientInfoTagHandler;
import org.seasar.uruma.component.factory.handler.GradientItemTagHandler;
import org.seasar.uruma.component.factory.handler.LayoutDataTagHandler;
import org.seasar.uruma.component.factory.handler.LayoutTagHandler;
import org.seasar.uruma.component.factory.handler.SimpleItemTagHandler;
import org.seasar.uruma.component.factory.handler.TableCellTagHandler;
import org.seasar.uruma.component.factory.handler.TableColumnTagHandler;
import org.seasar.uruma.component.factory.handler.TemplateTagHandler;
import org.seasar.uruma.component.factory.handler.TreeItemTagHandler;
import org.seasar.uruma.component.factory.handler.ViewPartTagHandler;
import org.seasar.uruma.component.factory.handler.WindowTagHandler;
import org.seasar.uruma.component.impl.ButtonComponent;
import org.seasar.uruma.component.impl.CTabFolderComponent;
import org.seasar.uruma.component.impl.CTabItemComponent;
import org.seasar.uruma.component.impl.CanvasComponent;
import org.seasar.uruma.component.impl.ComboComponent;
import org.seasar.uruma.component.impl.CompositeComponent;
import org.seasar.uruma.component.impl.CoolBarComponent;
import org.seasar.uruma.component.impl.CoolItemComponent;
import org.seasar.uruma.component.impl.FillLayoutInfo;
import org.seasar.uruma.component.impl.GridDataInfo;
import org.seasar.uruma.component.impl.GridLayoutInfo;
import org.seasar.uruma.component.impl.GroupComponent;
import org.seasar.uruma.component.impl.LabelComponent;
import org.seasar.uruma.component.impl.LinkComponent;
import org.seasar.uruma.component.impl.MenuComponent;
import org.seasar.uruma.component.impl.MenuItemComponent;
import org.seasar.uruma.component.impl.ProgressBarComponent;
import org.seasar.uruma.component.impl.RowDataInfo;
import org.seasar.uruma.component.impl.RowLayoutInfo;
import org.seasar.uruma.component.impl.SashFormComponent;
import org.seasar.uruma.component.impl.ScaleComponent;
import org.seasar.uruma.component.impl.SeparatorComponent;
import org.seasar.uruma.component.impl.SliderComponent;
import org.seasar.uruma.component.impl.SpinnerComponent;
import org.seasar.uruma.component.impl.TabFolderComponent;
import org.seasar.uruma.component.impl.TabItemComponent;
import org.seasar.uruma.component.impl.TableComponent;
import org.seasar.uruma.component.impl.TableItemComponent;
import org.seasar.uruma.component.impl.TextComponent;
import org.seasar.uruma.component.impl.ToolBarComponent;
import org.seasar.uruma.component.impl.ToolItemComponent;
import org.seasar.uruma.component.impl.TreeComponent;

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
        addTagHandler(new TemplateTagHandler());
        addTagHandler(new WindowTagHandler());
        addTagHandler(new ViewPartTagHandler());

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

        // SimpleItem
        addTagHandler(new SimpleItemTagHandler());

        // Menu
        addTagHandler("menu", new GenericTagHandler(MenuComponent.class));
        addTagHandler("menuItem",
                new GenericTagHandler(MenuItemComponent.class));
        addTagHandler("separator", new GenericTagHandler(
                SeparatorComponent.class));
    }

    protected void addTagHandler(final UrumaTagHandler tagHandler) {
        addTagHandler(tagHandler.getElementPath(), tagHandler);
    }
}
