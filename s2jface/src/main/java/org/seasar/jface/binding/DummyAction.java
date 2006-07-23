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
package org.seasar.jface.binding;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.events.HelpListener;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.container.annotation.tiger.Component;

/**
 * @author y-komori
 * 
 */
@Component
public class DummyAction extends Action {
    public DummyAction() {
        super();
    }

    public DummyAction(String text) {
        super(text);
    }

    public DummyAction(String text, int style) {
        super(text, style);
    }

    @Override
    @Binding(bindingType = BindingType.MAY)
    public void setHelpListener(HelpListener listener) {
        super.setHelpListener(listener);
    }

    @Override
    @Binding(bindingType = BindingType.MAY)
    public void setMenuCreator(IMenuCreator creator) {
        super.setMenuCreator(creator);
    }
}
