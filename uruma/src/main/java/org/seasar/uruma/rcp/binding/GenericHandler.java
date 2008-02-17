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
package org.seasar.uruma.rcp.binding;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

/**
 * 任意のメソッドを呼び出すことができる、汎用的な {@link IHandler} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class GenericHandler extends AbstractHandler {
    protected String id;

    /**
     * {@link GenericHandler} を構築します。<br />
     * 
     * @param id
     *            Uruma画面定義 XML 上の ID
     */
    public GenericHandler(final String id) {
        this.id = id;
    }

    @Override
    public Object execute(final ExecutionEvent arg0) throws ExecutionException {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
