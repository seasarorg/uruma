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
package org.seasar.jface.container.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.jface.container.EventListenerDef;
import org.seasar.jface.container.S2JFaceComponentDef;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 * 
 */
public class S2JFaceComponentDefImpl extends ComponentDefImpl implements
        S2JFaceComponentDef {

    private List<EventListenerDef> eventListenerDefs = new ArrayList<EventListenerDef>();

    public S2JFaceComponentDefImpl() {
        super();
    }

    public S2JFaceComponentDefImpl(Class componentClass, String componentName) {
        super(componentClass, componentName);
    }

    public S2JFaceComponentDefImpl(Class componentClass) {
        super(componentClass);
    }

    public void addEventListenerDef(EventListenerDef eventListenerDef) {
        AssertionUtil.assertNotNull("eventListener", eventListenerDef);

        eventListenerDefs.add(eventListenerDef);
    }

    public Iterator<EventListenerDef> eventListenerDefIterator() {
        return eventListenerDefs.iterator();
    }

}
