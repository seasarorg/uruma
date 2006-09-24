/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
import org.seasar.jface.component.factory.handler.LayoutDataTagHandler;
import org.seasar.jface.component.factory.handler.LayoutTagHandler;
import org.seasar.jface.component.factory.handler.S2JFaceGenericTagHandler;
import org.seasar.jface.component.factory.handler.SimpleItemTagHandler;
import org.seasar.jface.component.factory.handler.TemplateTagHandler;
import org.seasar.jface.component.factory.handler.WindowTagHandler;
import org.seasar.jface.component.impl.ButtonComponent;
import org.seasar.jface.component.impl.ComboComponent;
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.component.impl.FillLayoutInfo;
import org.seasar.jface.component.impl.GridDataInfo;
import org.seasar.jface.component.impl.GridLayoutInfo;
import org.seasar.jface.component.impl.LabelComponent;
import org.seasar.jface.component.impl.RowDataInfo;
import org.seasar.jface.component.impl.RowLayoutInfo;
import org.seasar.jface.component.impl.TabFolderComponent;
import org.seasar.jface.component.impl.TabItemComponent;

/**
 * S2JFace の画面定義XMLをパースするためのタグハンドラを保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class S2JFaceTagHandlerRule extends TagHandlerRule {
    private static final long serialVersionUID = -6918247426777293347L;

    public S2JFaceTagHandlerRule() {
        addTagHandler(new TemplateTagHandler());
        addTagHandler(new WindowTagHandler());

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

        // Control
        addTagHandler("label", new S2JFaceGenericTagHandler(
                LabelComponent.class));
        addTagHandler("button", new S2JFaceGenericTagHandler(
                ButtonComponent.class));
        
        // SimpleItem
        addTagHandler(new SimpleItemTagHandler());
    }

    protected void addTagHandler(S2JFaceTagHandler tagHandler) {
        addTagHandler(tagHandler.getElementPath(), tagHandler);
    }
}
