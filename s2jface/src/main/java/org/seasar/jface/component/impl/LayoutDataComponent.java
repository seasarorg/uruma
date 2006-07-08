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

import org.seasar.jface.component.Inheritance;
import org.seasar.jface.component.LayoutData;

/**
 * <code>LayoutData</code> 要素の情報を保持するためのコンポーネントです。</br>
 * 
 * @author y-komori
 */
public class LayoutDataComponent implements LayoutData {
    private String name;

    private String value;

    private Inheritance inheritance = Inheritance.NULL;

    public LayoutDataComponent(final String name,
            final Inheritance inheritance, final String value) {
        this.name = name;
        this.inheritance = inheritance;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public Inheritance getInheritance() {
        return this.inheritance;
    }

    public void setInheritance(final Inheritance inheritance) {
        this.inheritance = inheritance;
    }
}
