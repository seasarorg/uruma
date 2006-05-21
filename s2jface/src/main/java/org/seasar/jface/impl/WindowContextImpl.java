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
package org.seasar.jface.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 *
 */
public class WindowContextImpl implements WindowContext {
    protected Map<String, Widget> componentMap = new HashMap<String, Widget>();
    
    public Widget getComponent(String id) {
        return componentMap.get(id);
    }

    public void putComponent(String id, Widget component) {
        AssertionUtil.assertNotNull("id", id);
        AssertionUtil.assertNotNull("component", component);

        componentMap.put(id, component);
    }
}
