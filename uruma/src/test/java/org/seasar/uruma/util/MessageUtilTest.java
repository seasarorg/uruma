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
package org.seasar.uruma.util;

import junit.framework.TestCase;

import org.seasar.framework.message.MessageFormatter;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;

/**
 * {@link MessageUtil} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class MessageUtilTest extends TestCase {
    private static final String TEST_BUNDLE = "testMessages";

    private static final String NG_BUNDLE = "ng";

    private static final String NG_KEY = "ngkey";

    private static final String TEST_MESSAGE1 = "TEST_MESSAGE1";

    private static final String TEST_MESSAGE2 = "TEST_MESSAGE2";

    /**
     * {@link MessageUtil#getMessage(String)} メソッドのテストです。<br />
     */
    public void testGetMessage1() {
        assertEquals("テストメッセージ1", MessageUtil.getMessage(TEST_MESSAGE1));
    }

    /**
     * {@link MessageUtil#getMessage(String, Object...)} メソッドのテストです。<br />
     */
    public void testGetMessage2() {
        assertEquals("テストメッセージ2 引数 arg1 arg2 arg3", MessageUtil.getMessage(
                TEST_MESSAGE2, "arg1", "arg2", "arg3"));
    }

    /**
     * {@link MessageUtil#getMessageWithBundleName(String, String)} メソッドのテストです。<br />
     */
    public void testGetMessageWithBundleName1() {
        assertEquals("1", "テストメッセージ1", MessageUtil.getMessageWithBundleName(
                TEST_BUNDLE, TEST_MESSAGE1));

        String actual = MessageFormatter.getSimpleMessage(
                UrumaMessageCodes.MESSAGE_KEY_NOT_FOUND, new Object[] {
                        TEST_BUNDLE, NG_KEY });
        assertEquals("2", actual, MessageUtil.getMessageWithBundleName(
                TEST_BUNDLE, NG_KEY));
    }

    /**
     * {@link MessageUtil#getMessageWithBundleName(String, String, Object...)}
     * メソッドのテストです。<br />
     */
    public void testGetMessageWithBundleName2() {
        assertEquals("1", "テストメッセージ2 引数 arg1 arg2 arg3", MessageUtil
                .getMessageWithBundleName(TEST_BUNDLE, TEST_MESSAGE2, "arg1",
                        "arg2", "arg3"));

        try {
            MessageUtil.getMessageWithBundleName(NG_BUNDLE, TEST_MESSAGE2,
                    "arg1", "arg2", "arg3");
            fail("2");
        } catch (NotFoundException ex) {
            assertTrue(true);
        }
    }
}
