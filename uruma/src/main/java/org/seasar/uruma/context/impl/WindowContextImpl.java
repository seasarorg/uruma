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
import java.util.Collections;
import java.util.List;

import org.seasar.uruma.binding.enables.EnablesDependingDef;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.exception.DuplicateComponentIdException;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link WindowContext} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class WindowContextImpl extends AbstractWidgetHolder implements
        WindowContext {

    private String windowName;

    private List<PartContext> partContextList;

    private ApplicationContextImpl parent;

    private List<EnablesDependingDef> enablesDependingDefList = new ArrayList<EnablesDependingDef>();

    /**
     * {@link WindowContextImpl} を構築します。<br />
     * 
     * @param windowName
     *            ウィンドウ名称
     * @param parent
     *            親 {@link ApplicationContext}
     */
    public WindowContextImpl(final String windowName,
            final ApplicationContext parent) {
        super();

        this.windowName = windowName;
        this.parent = (ApplicationContextImpl) parent;
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#getWindowName()
     */
    public String getName() {
        return this.windowName;
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#getPartContext()
     */
    public PartContext getPartContext() {
        if ((partContextList != null) && (partContextList.size() > 0)) {
            return partContextList.get(0);
        } else {
            return null;
        }
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#getPartContext(java.lang.String)
     */
    public PartContext getPartContext(final String partName) {
        for (PartContext context : partContextList) {
            if (context.getName().equals(partName)) {
                return context;
            }
        }
        return null;
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#getPartContextList()
     */
    public List<PartContext> getPartContextList() {
        return Collections.unmodifiableList(partContextList);
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#getApplicationContext()
     */
    public ApplicationContext getApplicationContext() {
        return parent;
    }

    /**
     * {@link PartContext} オブジェクトを追加します。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     * @throws DuplicateComponentIdException
     *             パート名称が既に登録されている場合
     */
    public void addPartContext(final PartContext context) {
        if (partContextList == null) {
            partContextList = new ArrayList<PartContext>();
        }

        if (getPartContext(context.getName()) == null) {
            partContextList.add(context);
        } else {
            throw new DuplicateComponentIdException(context.getName());
        }
    }

    /**
     * {@link PartContext} オブジェクトを削除します。<br />
     * 
     * @param partName
     *            パート名称
     */
    public void disposePartContext(final String partName) {
        PartContext partContext = getPartContext(partName);
        if (partContext != null) {
            partContextList.remove(partContext);
        }
    }

    /**
     * この {@link WindowContext} を親 {@link ApplicationContext} から削除します。<br />
     */
    public void dispose() {
        for (PartContext part : partContextList) {
            disposePartContext(part.getName());
        }
        parent.disposeWindowContext(windowName);
        parent = null;
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#findWidgetHandles(java.lang.String)
     */
    public List<WidgetHandle> findWidgetHandles(final String handleId) {
        List<WidgetHandle> results = new ArrayList<WidgetHandle>();

        WidgetHandle handle = getWidgetHandle(handleId);
        if (handle != null) {
            results.add(handle);
        }

        for (PartContext part : getPartContextList()) {
            handle = part.getWidgetHandle(handleId);
            if (handle != null) {
                results.add(handle);
            }
        }
        return results;
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#getAllWidgetHandles(java.lang.Class)
     */
    public List<WidgetHandle> getAllWidgetHandles(final Class<?> clazz) {
        List<WidgetHandle> handles = new ArrayList<WidgetHandle>();
        handles.addAll(getWidgetHandles(clazz));

        for (PartContext part : getPartContextList()) {
            handles.addAll(part.getWidgetHandles(clazz));
        }

        return handles;
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#addEnablesDependingDef(org.seasar.uruma.binding.enables.EnablesDependingDef)
     */
    public void addEnablesDependingDef(
            final EnablesDependingDef enablesDependingDef) {
        AssertionUtil.assertNotNull("enablesDependingDef", enablesDependingDef);
        enablesDependingDefList.add(enablesDependingDef);
    }

    /*
     * @see org.seasar.uruma.context.WindowContext#getEnablesDependingDefList()
     */
    public List<EnablesDependingDef> getEnablesDependingDefList() {
        return Collections.unmodifiableList(enablesDependingDefList);
    }
}
