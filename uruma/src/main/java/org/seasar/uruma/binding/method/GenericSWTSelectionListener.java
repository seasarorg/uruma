/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.seasar.uruma.binding.value.ValueBindingSupport;
import org.seasar.uruma.binding.widget.WidgetBinder;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.util.AssertionUtil;

/**
 * 任意のメソッドを実行することができる汎用的な {@link SelectionListener} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class GenericSWTSelectionListener extends SelectionAdapter {
    private PartContext context;

    private MethodBinding methodBinding;

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
        AssertionUtil.assertNotNull("context", context);
        AssertionUtil.assertNotNull("methodBinding", methodBinding);

        this.context = context;
        this.methodBinding = methodBinding;
    }

    /*
     * @see org.eclipse.swt.events.SelectionAdapter#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetDefaultSelected(final SelectionEvent e) {
        invokeMethod(e);
    }

    /*
     * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetSelected(final SelectionEvent e) {
        invokeMethod(e);
    }

    protected void invokeMethod(final SelectionEvent event) {
        WidgetBinder.bindWidgets(methodBinding.getTarget(), context);

        ValueBindingSupport.importSelection(context);
        ValueBindingSupport.importValue(context);

        methodBinding.invoke(new Object[] { event });

        if (!event.widget.isDisposed()) {
            ValueBindingSupport.exportValue(context);
            ValueBindingSupport.exportSelection(context);
        }
    }
}
