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

import org.seasar.jface.component.Item;

/**
 * <code>Item</code> 要素の情報を保持するためのコンポーネントです。
 * 
 * @author dkameya
 */
public class ItemComponent implements Item {
    private String label;

    private String value;

    private List<Item> children = new ArrayList<Item>();

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void addChild(final Item item) {
        children.add(item);
    }

    public List<Item> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return children.size() > 0 ? true : false;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }
}
