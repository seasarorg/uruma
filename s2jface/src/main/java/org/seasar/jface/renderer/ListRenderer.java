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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.ListInfo;

/**
 * @author dkameya
 */
public class ListRenderer extends AbstractControlRenderer<List> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        int style = super.getStyle(controlComponent);
        style = (style == SWT.NONE) ? SWT.BORDER : style;
        return style;
    }

    @Override
    protected void doRender(final List list,
            final ControlComponent controlComponent) {
        addItem(list, controlComponent);
    }

    @Override
    protected Class<List> getControlType() {
        return List.class;
    }

    public String getRendererName() {
        return "list";
    }

    protected void addItem(final List list,
            final ControlComponent controlComponent) {
        for (Item item : controlComponent.getItemList()) {
            list.add(item.getValue());
        }
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return ListInfo.class;
    }
}