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

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.exception.DuplicateComponentIdException;

/**
 * {@link WindowContext} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class WindowContextImpl extends AbstractWidgetHolder implements
        WindowContext {
    /**
     * {@link IMenuManager} の登録 ID です。
     */
    public static final String MENU_MANAGER_ID = "menuManager";

    /**
     * {@link IStatusLineManager} の登録 ID です。
     */
    public static final String STATUS_LINE_MANAGER_ID = "statusLineManager";

    private String windowName;

    private List<PartContext> partContextList;

    /**
     * {@link WindowContextImpl} を構築します。<br />
     */
    protected WindowContextImpl(final String windowName) {
        super();

        this.windowName = windowName;
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

    /**
     * {@link PartContext} オブジェクトを追加します。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     * @throws DuplicateComponentIdException
     *             パート名称が既に登録されている場合
     */
    protected void addPartContext(final PartContext context) {
        if (partContextList == null) {
            partContextList = new ArrayList<PartContext>();
        }

        if (getPartContext(context.getName()) == null) {
            partContextList.add(context);
        } else {
            throw new DuplicateComponentIdException(context.getName());
        }
    }
}
