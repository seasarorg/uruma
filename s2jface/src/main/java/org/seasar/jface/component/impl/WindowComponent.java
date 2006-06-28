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
package org.seasar.jface.component.impl;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.Inheritance;
import org.seasar.jface.renderer.info.WindowInfo;

/**
 * @author y-komori
 * 
 */
public class WindowComponent extends CompositeComponent {

    public WindowComponent() {
        super();
        addProperty(new PropertyComponent(WindowInfo.X_PROP, "center",
                Inheritance.NONE));
        addProperty(new PropertyComponent(WindowInfo.Y_PROP, "middle",
                Inheritance.NONE));
        addProperty(new PropertyComponent(WindowInfo.WIDTH_PROP, "50%",
                Inheritance.NONE));
        addProperty(new PropertyComponent(WindowInfo.HEIGHT_PROP, "50%",
                Inheritance.NONE));
    }

    public void render(final Composite parent, final WindowContext context) {
        if (parent instanceof Shell) {
            context.putComponent(WindowContext.SHELL_ID, parent);
        }

        Widget widget = null;
        if (renderer != null) {
            widget = renderer.render(this, parent, context);
            setWidget(widget);

            if (getId() != null) {
                context.putComponent(getId(), widget);
            }
        }

        renderChild((Composite) widget, context);

        renderer.renderAfter(widget, this, parent, context);
    }
}
