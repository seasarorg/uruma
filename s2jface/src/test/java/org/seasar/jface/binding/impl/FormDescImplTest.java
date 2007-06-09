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
package org.seasar.jface.binding.impl;

import java.lang.reflect.Field;
import java.util.List;

import junit.framework.TestCase;

import org.seasar.jface.annotation.ExportValue;
import org.seasar.jface.annotation.ImportExportValue;
import org.seasar.jface.annotation.ImportValue;
import org.seasar.jface.binding.FormDesc;

/**
 * @author y-komori
 * 
 */
public class FormDescImplTest extends TestCase {
    public void testSetupFields() throws Exception {
        FormDesc desc = new FormDescImpl(Target01.class);
        List<Field> importFields = desc.getImportValueFields();
        List<Field> exportFields = desc.getExportValueFields();

        assertEquals("1", 3, importFields.size());
        assertEquals("2", Target01.class.getField("field1"), importFields
                .get(0));
        assertEquals("3", Target01.class.getField("field3"), importFields
                .get(1));
        assertEquals("4", Target01.class.getField("field5"), importFields
                .get(2));

        assertEquals("5", 3, exportFields.size());
        assertEquals("6", Target01.class.getField("field2"), exportFields
                .get(0));
        assertEquals("7", Target01.class.getField("field3"), exportFields
                .get(1));
        assertEquals("8", Target01.class.getField("field6"), exportFields
                .get(2));
    }

    class SuperTarget {
        @ImportValue
        public String field5;

        @ExportValue
        public String field6;
    }

    class Target01 extends SuperTarget {
        @ImportValue
        public String field1;

        @ExportValue
        public String field2;

        @ImportExportValue
        public String field3;
    }
}
