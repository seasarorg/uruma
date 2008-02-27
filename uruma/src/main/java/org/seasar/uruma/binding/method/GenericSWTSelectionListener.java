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

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.seasar.uruma.context.PartContext;

/**
 * 任意のメソッドを実行することができる汎用的な {@link SelectionListener} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class GenericSWTSelectionListener extends AbstractGenericListener
        implements SelectionListener {
    /**
     * {@link GenericListener} を構築します。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     * @param methodBinding
     *            {@link MethodBinding} オブジェクト
     */
    public GenericSWTSelectionListener(final PartContext context,
            final MethodBinding methodBinding) {
        super(context, methodBinding);
    }

    /*
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(final SelectionEvent e) {
        invokeMethod(e);
    }

    /*
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(final SelectionEvent e) {
        invokeMethod(e);
    }
}
