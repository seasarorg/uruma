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
package org.seasar.jface.template;

import java.util.List;

import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;

/**
 * @author y-komori
 * 
 */
public class ItemTagHandlerTest extends S2FrameworkTestCase {
    protected TemplateBuilder builder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        builder = new TemplateBuilder();
    }

    public void testItemElement() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath(getClass().getSimpleName() + ".xml"));

        assertNotNull("1", template);
        WindowComponent windowComponent = template.getWindowComponent();
        ControlComponent controlComponent = (ControlComponent) windowComponent
                .getChildren().iterator().next();

        List<Item> itemList = controlComponent.getItemList();
        assertEquals(5, itemList.size());

        Item item1 = itemList.get(0);
        assertEquals("item1_1", "item1Label", item1.getLabel());
        assertEquals("item1_2", "item1Value", item1.getValue());

        Item item2 = itemList.get(1);
        assertNull("item2_1", item2.getLabel());
        assertEquals("item2_2", "item2Value", item2.getValue());

        Item item3 = itemList.get(2);
        assertEquals("item3_1", "item3Label", item3.getLabel());
        assertNull("item3_2", item3.getValue());

        Item item4 = itemList.get(3);
        assertEquals("item4_1", "item4Label", item4.getLabel());
        List<Item> item4List = item4.getChildren();
        assertEquals("item4_2", 3, item4List.size());

        Item item41 = item4List.get(0);
        assertEquals("item4_3", "item4-1Label", item41.getLabel());
        assertEquals("item4_4", "item4-1Value", item41.getValue());

        Item item42 = item4List.get(1);
        assertNull("item4_5", item42.getLabel());
        assertEquals("item4_6", "item4-2Value", item42.getValue());

        Item item43 = item4List.get(2);
        assertEquals("item4_7", "item4-3Label", item43.getLabel());
        assertNull("item4_8", item43.getValue());

        Item item5 = itemList.get(4);
        assertEquals("item5_1", "item5Label", item5.getLabel());
        List<Item> item5List = item5.getChildren();
        assertEquals("item5_2", 2, item5List.size());

        List<Item> item51List = item5List.get(0).getChildren();
        assertEquals("item5_3", 1, item51List.size());

        Item item511 = item51List.get(0);
        assertEquals("item5_4", "item5-1-1Label", item511.getLabel());

        List<Item> item52List = item5List.get(1).getChildren();
        assertEquals("item5_5", 1, item52List.size());

        Item item521 = item52List.get(0);
        assertEquals("item5_6", "item5-2-1Label", item521.getLabel());
    }

}
