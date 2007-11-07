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
package org.seasar.uruma.context.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WidgetHolder;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link WidgetHolder} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractWidgetHolder implements WidgetHolder {
    private Map<String, WidgetHandle> handleMap = new HashMap<String, WidgetHandle>();

    /*
     * @see org.seasar.uruma.context.WidgetHolder#getWidgetHandle(java.lang.String)
     */
    public WidgetHandle getWidgetHandle(final String handleId) {
        return handleMap.get(handleId);
    }

    /*
     * @see org.seasar.uruma.context.WidgetHolder#getWidgetHandles()
     */
    public Collection<WidgetHandle> getWidgetHandles() {
        return Collections.unmodifiableCollection(handleMap.values());
    }

    /*
     * @see org.seasar.uruma.context.WidgetHolder#hasWidgetHandle(java.lang.String)
     */
    public boolean hasWidgetHandle(final String handleId) {
        return handleMap.containsKey(handleId);
    }

    /*
     * @see org.seasar.uruma.context.WidgetHolder#putWidgetHandle(org.seasar.uruma.context.WidgetHandle)
     */
    public void putWidgetHandle(final WidgetHandle handle) {
        AssertionUtil.assertNotNull("handle", handle);

        handleMap.put(handle.getId(), handle);
    }

    /*
     * @see org.seasar.uruma.context.WidgetHolder#getWidgetHandles(java.lang.Class)
     */
    public List<WidgetHandle> getWidgetHandles(final Class<?> clazz) {
        List<WidgetHandle> handles = new ArrayList<WidgetHandle>();
        for (WidgetHandle handle : handleMap.values()) {
            if (handle.instanceOf(clazz)) {
                handles.add(handle);
            }
        }
        return handles;
    }
}
