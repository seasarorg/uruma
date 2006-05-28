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

import org.eclipse.swt.widgets.List;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * @author dkameya
 */
public class ListRenderer extends AbstractControlRenderer<List> {
    
    @Override
    protected void doRender(List list, ControlComponent controlComponent) {
        addItem(list, controlComponent);
    }

    @Override
    protected Class<List> getControlType() {
        return List.class;
    }

    public String getRendererName() {
        return "list";
    }
    
    private void addItem(List list, ControlComponent controlComponent) {
        java.util.List items = controlComponent.getItemList();
        for (int i = 0; i < items.size(); i++) {
            list.add(((Item) items.get(i)).getValue());
        }
    }
}
