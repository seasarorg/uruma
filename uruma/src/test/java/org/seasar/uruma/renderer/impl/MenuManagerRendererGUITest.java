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

import org.seasar.uruma.annotation.EventListener;

/**
 * {@link MenuManagerRenderer} のためのテストクラスです。<br />
 * 
 * @author bskuroneko
 */
public class MenuManagerRendererGUITest extends AbstractGUITest {

    /**
     * <code>accelerator1</code> が選択されたときに呼び出されるメソッドです。<br />
     */
    @EventListener(id = "accelerator1")
    public void accelerator1() {
        System.out.println("accelerator1 selected");
    }

    /**
     * <code>accelerator2</code> が選択されたときに呼び出されるメソッドです。<br />
     */
    @EventListener(id = "accelerator2")
    public void accelerator2() {
        System.out.println("accelerator2 selected");
    }
}
