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

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.exception.NotFoundException;

/**
 * 各種バインディングに関する準備を行うためのクラスです。<br />
 * 
 * @author bskuroneko
 */
public class BindingFacade {

    public static void bindAll(ActionDesc actionDesc, WindowContext context) {
        if (actionDesc != null) {
            MethodBindingSupport.createListeners(actionDesc, context);
        }

        createEnabledDependBinders(context);
    }

    private static void createEnabledDependBinders(WindowContext context) {
        for (EnabledDepend depend : context.getEnabledDepends()) {
            Widget targetWidget = context.getComponent(depend.getTargetId());
            if (targetWidget == null) {
                throw new NotFoundException(NotFoundException.UICOMPONENT,
                        depend.getTargetId());
            }

            WidgetEnabledDependBinder binder = WidgetEnabledDependBinderFactory
                    .getBinder(depend.getWidget(), targetWidget, depend
                            .getType());
            context.addWidgetEnabledDependBinder(binder);
            binder.bindEventListeners();
            binder.updateEnabled();
        }
    }

}
