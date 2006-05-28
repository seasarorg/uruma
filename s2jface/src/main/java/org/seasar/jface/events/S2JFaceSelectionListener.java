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
package org.seasar.jface.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.MethodBinding;
import org.seasar.jface.binding.WidgetBinder;

/**
 * @author y-komori
 * 
 */
public class S2JFaceSelectionListener extends SelectionAdapter {
    private WindowContext context;

    private MethodBinding methodBinding;

    public S2JFaceSelectionListener(final WindowContext context,
            final MethodBinding methodBinding) {
        this.context = context;
        this.methodBinding = methodBinding;
    }

    @Override
    public void widgetSelected(SelectionEvent event) {
        if (methodBinding != null) {
            WidgetBinder.bindWidgets(methodBinding.getTarget(), context);
            methodBinding.invoke();
        }
    }
}
