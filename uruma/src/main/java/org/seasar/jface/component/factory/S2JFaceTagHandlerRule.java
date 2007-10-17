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
package org.seasar.jface.component.factory;

import org.seasar.framework.xml.TagHandlerRule;
import org.seasar.jface.component.factory.handler.CommonAttributesTagHandler;
import org.seasar.jface.component.factory.handler.GradientInfoTagHandler;
import org.seasar.jface.component.factory.handler.GradientItemTagHandler;
import org.seasar.jface.component.factory.handler.LayoutDataTagHandler;
import org.seasar.jface.component.factory.handler.LayoutTagHandler;
import org.seasar.jface.component.factory.handler.MenuItemTagHandler;
import org.seasar.jface.component.factory.handler.MenuTagHandler;
import org.seasar.jface.component.factory.handler.S2JFaceGenericTagHandler;
import org.seasar.jface.component.factory.handler.SeparatorTagHandler;
import org.seasar.jface.component.factory.handler.SimpleItemTagHandler;
import org.seasar.jface.component.factory.handler.TableCellTagHandler;
import org.seasar.jface.component.factory.handler.TableColumnTagHandler;
import org.seasar.jface.component.factory.handler.TemplateTagHandler;
import org.seasar.jface.component.factory.handler.TreeItemTagHandler;
import org.seasar.jface.component.factory.handler.ViewPartTagHandler;
import org.seasar.jface.component.factory.handler.WindowTagHandler;
import org.seasar.jface.component.impl.ButtonComponent;
import org.seasar.jface.component.impl.CTabFolderComponent;
import org.seasar.jface.component.impl.CTabItemComponent;
import org.seasar.jface.component.impl.CanvasComponent;
import org.seasar.jface.component.impl.ComboComponent;
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.component.impl.CoolBarComponent;
import org.seasar.jface.component.impl.CoolItemComponent;
import org.seasar.jface.component.impl.FillLayoutInfo;
import org.seasar.jface.component.impl.GridDataInfo;
import org.seasar.jface.component.impl.GridLayoutInfo;
import org.seasar.jface.component.impl.GroupComponent;
import org.seasar.jface.component.impl.LabelComponent;
import org.seasar.jface.component.impl.LinkComponent;
import org.seasar.jface.component.impl.ProgressBarComponent;
import org.seasar.jface.component.impl.RowDataInfo;
import org.seasar.jface.component.impl.RowLayoutInfo;
import org.seasar.jface.component.impl.SashFormComponent;
import org.seasar.jface.component.impl.ScaleComponent;
import org.seasar.jface.component.impl.SliderComponent;
import org.seasar.jface.component.impl.SpinnerComponent;
import org.seasar.jface.component.impl.TabFolderComponent;
import org.seasar.jface.component.impl.TabItemComponent;
import org.seasar.jface.component.impl.TableComponent;
import org.seasar.jface.component.impl.TableItemComponent;
import org.seasar.jface.component.impl.TextComponent;
import org.seasar.jface.component.impl.ToolBarComponent;
import org.seasar.jface.component.impl.ToolItemComponent;
import org.seasar.jface.component.impl.TreeComponent;

/**
 * S2JFace の画面定義XMLをパースするためのタグハンドラを保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class S2JFaceTagHandlerRule extends TagHandlerRule {
    private static final long serialVersionUID = -6918247426777293347L;

    /**
     * {@link S2JFaceTagHandlerRule} クラスを構築します。<br />
     */
    public S2JFaceTagHandlerRule() {
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
        addTagHandler("composite", new S2JFaceGenericTagHandler(
                CompositeComponent.class));
        addTagHandler("combo", new S2JFaceGenericTagHandler(
                ComboComponent.class));
        addTagHandler("tabFolder", new S2JFaceGenericTagHandler(
                TabFolderComponent.class));
        addTagHandler("tabItem", new S2JFaceGenericTagHandler(
                TabItemComponent.class));
        addTagHandler("table", new S2JFaceGenericTagHandler(
                TableComponent.class));
        addTagHandler("tableColumn", new TableColumnTagHandler());
        addTagHandler("tableItem", new S2JFaceGenericTagHandler(
                TableItemComponent.class));
        addTagHandler(new TableCellTagHandler());
        addTagHandler("tree", new S2JFaceGenericTagHandler(TreeComponent.class));
        addTagHandler("treeItem", new TreeItemTagHandler());
        addTagHandler("spinner", new S2JFaceGenericTagHandler(
                SpinnerComponent.class));
        addTagHandler("cTabFolder", new S2JFaceGenericTagHandler(
                CTabFolderComponent.class));
        addTagHandler("cTabItem", new S2JFaceGenericTagHandler(
                CTabItemComponent.class));
        addTagHandler(new GradientInfoTagHandler());
        addTagHandler(new GradientItemTagHandler());
        addTagHandler("group", new S2JFaceGenericTagHandler(
                GroupComponent.class));
        addTagHandler("toolBar", new S2JFaceGenericTagHandler(
                ToolBarComponent.class));
        addTagHandler("toolItem", new S2JFaceGenericTagHandler(
                ToolItemComponent.class));
        addTagHandler("coolBar", new S2JFaceGenericTagHandler(
                CoolBarComponent.class));
        addTagHandler("coolItem", new S2JFaceGenericTagHandler(
                CoolItemComponent.class));
        addTagHandler("canvas", new S2JFaceGenericTagHandler(
                CanvasComponent.class));
        addTagHandler("sashForm", new S2JFaceGenericTagHandler(
                SashFormComponent.class));

        // Control
        addTagHandler("label", new S2JFaceGenericTagHandler(
                LabelComponent.class));
        addTagHandler("button", new S2JFaceGenericTagHandler(
                ButtonComponent.class));
        addTagHandler("text", new S2JFaceGenericTagHandler(TextComponent.class));
        addTagHandler("progressBar", new S2JFaceGenericTagHandler(
                ProgressBarComponent.class));
        addTagHandler("scale", new S2JFaceGenericTagHandler(
                ScaleComponent.class));
        addTagHandler("slider", new S2JFaceGenericTagHandler(
                SliderComponent.class));
        addTagHandler("link", new S2JFaceGenericTagHandler(LinkComponent.class));

        // SimpleItem
        addTagHandler(new SimpleItemTagHandler());

        // Menu
        addTagHandler(new MenuTagHandler());
        addTagHandler(new MenuItemTagHandler());
        addTagHandler(new SeparatorTagHandler());
    }

    protected void addTagHandler(S2JFaceTagHandler tagHandler) {
        addTagHandler(tagHandler.getElementPath(), tagHandler);
    }
}
