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
import org.seasar.jface.component.factory.handler.S2JFaceGenericTagHandler;
import org.seasar.jface.component.info.impl.TemplateComponentInfo;
import org.seasar.jface.util.ClassUtil;

/**
 * S2JFace の画面定義XMLをパースするためのタグハンドラを保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class S2JFaceTagHandlerRule extends TagHandlerRule {
    private static final long serialVersionUID = -6918247426777293347L;

    public S2JFaceTagHandlerRule() {
        addTagHandler(new S2JFaceGenericTagHandler(TemplateComponentInfo.class));
    }

    protected void addTagHandler(S2JFaceTagHandler tagHandler) {
        addTagHandler(tagHandler.getElementName(), tagHandler);
    }

    protected void addTagHandler(Class<? extends S2JFaceTagHandler> clazz) {
        S2JFaceTagHandler tagHandler = ClassUtil
                .<S2JFaceTagHandler> newInstance(clazz);
        addTagHandler(tagHandler.getElementName(), tagHandler);
    }
}
