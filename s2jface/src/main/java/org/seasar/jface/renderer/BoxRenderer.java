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
package org.seasar.jface.renderer;

import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.layout.LayoutSupport;
import org.seasar.jface.layout.LayoutSupportFactory;
import org.seasar.jface.util.PropertyUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * @author y-komori
 * 
 */
public class BoxRenderer extends AbstractControlRenderer<Composite> {

    @Override
    protected Class<Composite> getControlType() {
        return Composite.class;
    }

    @Override
    protected void doRender(Composite control, ControlComponent uiComponent) {
        CompositeComponent compositeComponent = (CompositeComponent) uiComponent;
        String layoutType = compositeComponent.getLayout();
        if (layoutType != null) {
            LayoutSupport layoutSupport = LayoutSupportFactory
                    .getLayoutSupport(layoutType);
            Layout layout = layoutSupport.createLayout();

            String layoutParam = compositeComponent.getLayoutParam();
            if (layoutParam != null) {
                setupLayoutParam(layout, layoutParam);
            }
            control.setLayout(layout);
        }
    }

    protected void setupLayoutParam(final Layout layout, final String params) {
        StringTokenizer st = new StringTokenizer(params, ";");
        while (st.hasMoreElements()) {
            String param = st.nextToken();
            int eqPos = param.indexOf("=");
            if (eqPos >= 0) {
                String property = param.substring(0, eqPos).trim();
                String value = param.substring(eqPos + 1).trim();

                int swtConstant = SWTUtil.getSWTConstant(value);
                if (swtConstant != SWT.NONE) {
                    value = Integer.toString(swtConstant);
                }

                PropertyUtil
                        .setField(layout, property, Integer.parseInt(value));
            }
        }
    }
}
