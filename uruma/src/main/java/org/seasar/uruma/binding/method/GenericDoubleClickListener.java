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
package org.seasar.uruma.binding.method;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.seasar.uruma.context.PartContext;

/**
 * 任意のメソッドを呼び出すことができる、汎用的な {@link IDoubleClickListener} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class GenericDoubleClickListener extends AbstractGenericListener
        implements IDoubleClickListener {

    /**
     * {@link GenericDoubleClickListener} を構築します。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     * @param methodBinding
     *            {@link MethodBinding} オブジェクト
     */
    public GenericDoubleClickListener(final PartContext context,
            final MethodBinding methodBinding) {
        super(context, methodBinding);
    }

    /*
     * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
     */
    public void doubleClick(final DoubleClickEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
