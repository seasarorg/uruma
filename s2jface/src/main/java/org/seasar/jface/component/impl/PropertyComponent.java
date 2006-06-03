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
import org.seasar.jface.component.Property;
import org.seasar.jface.util.AssertionUtil;

/**
 * <code>Property</code> 要素の情報を保持するためのコンポーネントです。
 * 
 * @author y-komori
 * 
 */
public class PropertyComponent implements Property {
    private String name;

    private String value;

    private Inheritance inheritance = Inheritance.NULL;

    public PropertyComponent(final String name) {
        AssertionUtil.assertNotNull("name", name);
        this.name = name;
    }

    public PropertyComponent(final String name, final String value) {
        this(name);
        this.value = value;
    }

    public PropertyComponent(final String name, final String value,
            final Inheritance inheritance) {
        this(name, value);
        this.inheritance = inheritance;
    }

    public Inheritance getInheritance() {
        return this.inheritance;
    }

    public void setInheritance(Inheritance inheritance) {
        this.inheritance = inheritance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIntValue() {
        // TODO 例外処理
        return Integer.parseInt(value);
    }

    public boolean getBooleanValue() {
        // TODO 例外処理
        return Boolean.parseBoolean(value);
    }

    public boolean isValueExist() {
        return (value != null ? true : false);
    }

    public Property cloneProperty(final Inheritance inheritance) {
        return new PropertyComponent(name, value, inheritance);
    }
}
