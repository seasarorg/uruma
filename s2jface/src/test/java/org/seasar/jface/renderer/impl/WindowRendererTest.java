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
package org.seasar.jface.renderer.impl;

import org.eclipse.swt.widgets.Shell;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.jface.S2JFace;

/**
 * @author y-komori
 * 
 */
public class WindowRendererTest extends S2FrameworkTestCase {
    protected S2JFace s2JFace;

    protected Shell shell;

    private static boolean result;

    @Override
    protected void setUp() throws Exception {
        s2JFace = new S2JFace();
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // container.register(createActionComponentDef());

        // クラス名と同名のdiconファイルが存在すればインクルードする
        try {
            S2Container child = S2ContainerFactory
                    .create(convertPath(getClass().getSimpleName() + ".dicon"));
            container.include(child);
        } catch (ResourceNotFoundRuntimeException ex) {
            // do nothing.
        }

        result = false;

    }

    public void testRender() {
        String path = convertPath(getClass().getSimpleName() + ".xml");
        s2JFace.openWindow(path);
        assertTrue(path, result);
    }
}
