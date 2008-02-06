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
package org.seasar.uruma.rcp.unit;

import junit.framework.TestCase;

import org.seasar.uruma.rcp.UrumaService;

/**
 * Uruma RCP 環境でテストを行うための基底クラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaRcpTestCase extends TestCase {
    private UrumaService service;

    @Override
    public void runBare() throws Throwable {
        setUpService();
        try {
            setUp();
            try {
                runTest();
            } finally {
                tearDown();
            }
        } finally {
            tearDownService();
        }
    }

    private void setUpService() {
        service = new MockUrumaService();
    }

    private void tearDownService() {
        service = null;
    }

    protected UrumaService getService() {
        return service;
    }
}
