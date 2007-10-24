/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.uruma.desc.impl;

import java.util.List;

import junit.framework.TestCase;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.ImportValue;
import org.seasar.uruma.desc.FormDesc;

/**
 * {@link FormDescImpl} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class FormDescImplTest extends TestCase {
    private BeanDesc beanDesc;

    private FormDesc desc;

    @Override
    protected void setUp() throws Exception {
        beanDesc = BeanDescFactory.getBeanDesc(Target01.class);
        desc = new FormDescImpl(Target01.class);
    }

    public void testGetImportValueProperties() throws Exception {
        List<PropertyDesc> importProps = desc.getImportValueProperties();

        assertEquals("1", 3, importProps.size());
        assertEquals("2", beanDesc.getPropertyDesc("field1"), importProps
                .get(0));
        assertEquals("3", beanDesc.getPropertyDesc("field3"), importProps
                .get(1));
        assertEquals("4", beanDesc.getPropertyDesc("field6"), importProps
                .get(2));

        try {
            importProps.add(beanDesc.getPropertyDesc("field1"));
            fail("5");
        } catch (UnsupportedOperationException ex) {
            assertTrue(true);
        }
    }

    public void testGetExportValueProperties() throws Exception {
        List<PropertyDesc> exportProps = desc.getExportValueProperties();

        assertEquals("1", 3, exportProps.size());
        assertEquals("2", beanDesc.getPropertyDesc("field2"), exportProps
                .get(0));
        assertEquals("3", beanDesc.getPropertyDesc("field3"), exportProps
                .get(1));
        assertEquals("4", beanDesc.getPropertyDesc("field7"), exportProps
                .get(2));

        try {
            exportProps.add(beanDesc.getPropertyDesc("field1"));
            fail("5");
        } catch (UnsupportedOperationException ex) {
            assertTrue(true);
        }
    }

    public void testGetImportSelectionProperties() throws Exception {
        List<PropertyDesc> importSelectionProps = desc
                .getImportSelectionProperties();

        assertEquals("1", 2, importSelectionProps.size());
        assertEquals("2", beanDesc.getPropertyDesc("field4"),
                importSelectionProps.get(0));
        assertEquals("3", beanDesc.getPropertyDesc("field8"),
                importSelectionProps.get(1));
        try {
            importSelectionProps.add(beanDesc.getPropertyDesc("field1"));
            fail("4");
        } catch (UnsupportedOperationException ex) {
            assertTrue(true);
        }
    }

    public void testGetExportSelectionProperties() throws Exception {
        List<PropertyDesc> exportSelectionProps = desc
                .getExportSelectionProperties();

        assertEquals("1", 2, exportSelectionProps.size());
        assertEquals("2", beanDesc.getPropertyDesc("field5"),
                exportSelectionProps.get(0));
        assertEquals("3", beanDesc.getPropertyDesc("field9"),
                exportSelectionProps.get(1));

        try {
            exportSelectionProps.add(beanDesc.getPropertyDesc("field1"));
            fail("4");
        } catch (UnsupportedOperationException ex) {
            assertTrue(true);
        }
    }

    class SuperTarget {
        @ImportValue
        public String field6;

        @ExportValue
        public String field7;

        @ImportSelection
        public String field8;

        @ExportSelection
        public String field9;
    }

    class Target01 extends SuperTarget {
        @ImportValue
        public String field1;

        @ExportValue
        public String field2;

        @ImportExportValue
        public String field3;

        @ImportSelection
        public String field4;

        @ExportSelection
        public String field5;
    }
}
