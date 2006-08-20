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
package org.seasar.jface;

import org.eclipse.jface.action.MenuManager;
import org.seasar.jface.component.Menu;

/**
 * <code>MenuManager</code> を構築するクラスのためのインターフェースです。<br/>
 * 
 * @author y-komori
 */
public interface MenuManagerBuilder {

    /**
     * <code>Menu</code> オブジェクトから <code>MenuManager</code> を構築します。<br />
     * 
     * @param menu
     *            <code>Menu</code> オブジェクト
     * @return 構築した <code>MenuManager</code> オブジェクト
     * @see Menu
     * @see MenuManager
     */
    public MenuManager createMenuManager(Menu menu);
}
