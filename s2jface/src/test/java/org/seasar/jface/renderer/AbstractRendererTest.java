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
package org.seasar.jface.renderer;

import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.jface.S2JFace;

/**
 * レンダラのテストを行うための基底クラスです。</br> 各レンダラのテストクラスは、本クラスを継承してください。</br>
 * 
 * @author y-komori
 * 
 */
public abstract class AbstractRendererTest extends S2FrameworkTestCase {
    protected S2JFace s2JFace;

    @Override
    protected void setUp() throws Exception {
        s2JFace = new S2JFace();
    }

    public void testRender() {
        String path = convertPath(getClass().getSimpleName() + ".xml");
        s2JFace.openWindow(path);
    }
}
