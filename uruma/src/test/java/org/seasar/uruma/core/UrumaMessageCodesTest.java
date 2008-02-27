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
package org.seasar.uruma.core;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import junit.framework.TestCase;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.message.MessageFormatter;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.StringUtil;

/**
 * {@link UrumaMessageCodes} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaMessageCodesTest extends TestCase {
    /**
     * {@link UrumaMessageCodes} に定義されている定数に対応するメッセージが URMMessages
     * に登録されているかどうかをテストします。<br />
     */
    public void testConstants() {
        BeanDesc desc = BeanDescFactory.getBeanDesc(UrumaMessageCodes.class);
        int size = desc.getFieldSize();

        for (int i = 0; i < size; i++) {
            Field field = desc.getField(i);
            String code = FieldUtil.getString(field);
            String message = MessageFormatter.getSimpleMessage(code, null);
            assertFalse(code + " is not found in URMMessages.", StringUtil
                    .isEmpty(message));
        }
    }

    /**
     * <code>URMMessages</code> に登録されているキーがすべて {@link UrumaMessageCodes}
     * に登録されているかどうかをテストします。<br />
     */
    public void testMessages() {
        BeanDesc desc = BeanDescFactory.getBeanDesc(UrumaMessageCodes.class);
        Map<String, String> constantMap = new HashMap<String, String>();

        int size = desc.getFieldSize();

        for (int i = 0; i < size; i++) {
            Field field = desc.getField(i);
            constantMap.put(FieldUtil.getString(field), field.getName());
        }

        ResourceBundle bundle = ResourceBundle.getBundle("URMMessages");
        assertNotNull("1", bundle);

        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            assertTrue(key + " is not found in UrumaMessageCodes", constantMap
                    .containsKey(key));
        }
    }
}
