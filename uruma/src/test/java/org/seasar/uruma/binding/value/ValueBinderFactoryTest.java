/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.binding.value;

import junit.framework.TestCase;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.component.UIComponent;

/**
 * {@link ValueBinderFactory} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class ValueBinderFactoryTest extends TestCase {
    /**
     * {@link ValueBinderFactory#addValueBinder(ValueBinder)} メソッドのテストです。<br />
     */
    public void testAddValueBinder() {
        assertNull("1", ValueBinderFactory.getValueBinder(TestWidget.class));

        TestValueBinder binder = new TestValueBinder();
        ValueBinderFactory.addValueBinder(binder);

        assertEquals("2", binder, ValueBinderFactory
                .getValueBinder(TestWidget.class));

    }

    class TestWidget {
    }

    class TestValueBinder implements ValueBinder {
        public void exportSelection(final Object widget, final Object formObj,
                final PropertyDesc propDesc) {
            // Do nothing.
        }

        public void exportValue(final Object widget, final Object formObj,
                final PropertyDesc propDesc, final UIComponent uiComp) {
            // Do nothing.
        }

        public Class<?> getWidgetType() {
            return TestWidget.class;
        }

        public void importSelection(final Object widget, final Object formObj,
                final PropertyDesc propDesc) {
            // Do nothing.
        }

        public void importValue(final Object widget, final Object formObj,
                final PropertyDesc propDesc) {
            // Do nothing.
        }
    }
}
