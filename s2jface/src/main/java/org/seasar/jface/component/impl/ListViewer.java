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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.util.AssertionUtil;

/**
 * 
 * @author dkameya
 *
 */
public class ListViewer extends ControlComponent {
    private List<ItemComponent> itemList = new ArrayList<ItemComponent>();
    
    public void render(final Composite parent, final WindowContext context) {
        AssertionUtil.assertNotNull("renderer", renderer);
        Widget widget = renderer.render(this, parent, context);
        
        if (getId() != null) {
            context.putComponent(getId(), widget);
        }
    }
    
    public void addItem(ItemComponent item) {
        itemList.add(item);
    }
    
    public List getItems() {
        return itemList;
    }
    
}
