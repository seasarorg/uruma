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
package org.seasar.jface.events;

import org.junit.Test;
import org.seasar.jface.S2JFace;

public class EventListernerFactoryTest {
    private S2JFace s2JFace;

    @Test
    public void setup() {
        s2JFace = new S2JFace(
                "org/seasar/jface/events/EventListenerTestAction.dicon");
        s2JFace.openWindow("org/seasar/jface/events/EventListenerTest.xml");
    }
}
