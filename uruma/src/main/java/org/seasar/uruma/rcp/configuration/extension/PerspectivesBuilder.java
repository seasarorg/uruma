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

import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionBuilder;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.elements.PerspectiveElement;
import org.seasar.uruma.rcp.ui.AutoPerspectiveFactory;
import org.seasar.uruma.rcp.ui.BlankPerspectiveFactory;
import org.seasar.uruma.rcp.ui.GenericPerspectiveFactory;
import org.seasar.uruma.util.PathUtil;

/**
 * <code>perspectives</code> 拡張ポイントのための {@link ExtensionBuilder} です。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$
 */
public class PerspectivesBuilder extends AbstractExtensionBuilder {

    /*
     * @see org.seasar.uruma.rcp.configuration.ExtensionBuilder#buildExtension()
     */
    public Extension[] buildExtension() {
        Extension extension = ExtensionFactory.createExtension(ExtensionPoints.PERSPECTIVES);

        WorkbenchComponent workbenchComponent = service.getWorkbenchComponent();

        if (DUMMY_WORKBENCH_PATH
                .equals(PathUtil.getFileName(workbenchComponent.getURL().getPath()))) {
            return new Extension[] { createBlankPerspective() };
        }

        boolean defaultIdUsed = false;

        for (PerspectiveComponent perspective : workbenchComponent.getPerspectives()) {

            // ID のついていない最初のパースペクティブにはデフォルトIDをつける
            if (StringUtil.isBlank(perspective.id) && !defaultIdUsed) {
                perspective.id = DEFAULT_PERSPECTIVE_ID;
                defaultIdUsed = true;
            }

            PerspectiveElement element = new PerspectiveElement(perspective);
            element.clazz = GenericPerspectiveFactory.class.getName();

            extension.addElement(element);
        }

        if (extension.getElements().size() == 0) {
            // perspective 要素が定義されていないときにデフォルト設定を行う
            PerspectiveComponent component = new PerspectiveComponent();
            component.clazz = AutoPerspectiveFactory.class.getName();
            component.id = DEFAULT_PERSPECTIVE_ID;
            component.name = service.getPluginId();

            workbenchComponent.addChild(component);
            workbenchComponent.initialPerspectiveId = DEFAULT_PERSPECTIVE_ID;

            PerspectiveElement element = new PerspectiveElement(component);
            extension.addElement(element);

        } else if (StringUtil.isBlank(workbenchComponent.initialPerspectiveId)) {
            // initialPerspectiveId が定義されていない場合は
            // 最初に記述されている perspective を表示する
            PerspectiveComponent perspective = workbenchComponent.getPerspectives().get(0);
            workbenchComponent.initialPerspectiveId = perspective.id;
        }

        return new Extension[] { extension };
    }

    protected Extension createBlankPerspective() {
        Extension extension = ExtensionFactory.createExtension(ExtensionPoints.PERSPECTIVES);

        PerspectiveElement blank = new PerspectiveElement();
        blank.clazz = BlankPerspectiveFactory.class.getName();
        blank.id = service.createRcpId(DEFAULT_PERSPECTIVE_ID);
        blank.name = "";

        extension.addElement(blank);
        return extension;
    }
}
