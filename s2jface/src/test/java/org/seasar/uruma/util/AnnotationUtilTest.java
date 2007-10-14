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
package org.seasar.uruma.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.List;

import junit.framework.TestCase;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

/**
 * {@link AnnotationUtil} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class AnnotationUtilTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetAnnotatedFields() {
        List<Field> fields = AnnotationUtil.getAnnotatedFields(
                TargetClass.class, TestAnnotation.class);
        try {
            assertEquals("1", TargetClass.class.getField("field3"), fields
                    .get(0));
            assertEquals("2", TargetClass.class.getField("field4"), fields
                    .get(1));
            assertEquals("3", TargetClass.class.getField("field1"), fields
                    .get(2));
        } catch (Exception ex) {
        }

        fields = AnnotationUtil.getAnnotatedFields(TargetClass.class,
                TestAnnotation.class);
        try {
            assertEquals("4", TargetClass.class.getField("field3"), fields
                    .get(0));
            assertEquals("5", TargetClass.class.getField("field4"), fields
                    .get(1));
            assertEquals("6", TargetClass.class.getField("field1"), fields
                    .get(2));
        } catch (Exception ex) {
        }
    }

    public void testGetAnnotatedPropertyDescs() {
        BeanDesc desc = BeanDescFactory.getBeanDesc(TargetClass.class);
        List<PropertyDesc> pds = AnnotationUtil.getAnnotatedPropertyDescs(
                TargetClass.class, TestAnnotation.class);
        try {
            assertEquals("1", desc.getPropertyDesc("field3"), pds.get(0));
            assertEquals("2", desc.getPropertyDesc("field4"), pds.get(1));
            assertEquals("3", desc.getPropertyDesc("field1"), pds.get(2));
        } catch (Exception ex) {
        }

        pds = AnnotationUtil.getAnnotatedPropertyDescs(TargetClass.class,
                TestAnnotation.class);
        try {
            assertEquals("4", desc.getPropertyDesc("field3"), pds.get(0));
            assertEquals("5", desc.getPropertyDesc("field4"), pds.get(1));
            assertEquals("6", desc.getPropertyDesc("field1"), pds.get(2));
        } catch (Exception ex) {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface TestAnnotation {
    }

    class TargetSuperClass {
        @TestAnnotation
        private boolean field1;

        private int field2;

        public boolean getField1() {
            return this.field1;
        }

        public void setField1(final boolean field1) {
            this.field1 = field1;
        }

        public int getField2() {
            return this.field2;
        }

        public void setField2(final int field2) {
            this.field2 = field2;
        }
    }

    class TargetClass extends TargetSuperClass {
        @TestAnnotation
        private long field3;

        @TestAnnotation
        private String field4;

        private Object field5;

        public long getField3() {
            return this.field3;
        }

        public void setField3(final long field3) {
            this.field3 = field3;
        }

        public String getField4() {
            return this.field4;
        }

        public void setField4(final String field4) {
            this.field4 = field4;
        }

        public Object getField5() {
            return this.field5;
        }

        public void setField5(final Object field5) {
            this.field5 = field5;
        }
    }
}
