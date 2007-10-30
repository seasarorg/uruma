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

import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.desc.FormDesc;
import org.seasar.uruma.desc.PartActionDesc;

/**
 * {@link PartContext} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class PartContextImpl extends AbstractWidgetHolder implements
        PartContext {
    private String partName;

    private PartActionDesc partActionDesc;

    private Object partActionObj;

    private FormDesc formDesc;

    private Object formObj;

    private WindowContextImpl parent;

    /**
     * {@link PartContextImpl} を構築します。<br />
     * 本クラスのインスタンスを生成するには、{@link ContextFactory#createPartContext(org.seasar.uruma.context.WindowContext, String)}
     * メソッドを利用してください。
     * 
     * @param partName
     *            パート名称
     * @param parent
     *            親 {@link WindowContext}
     */
    public PartContextImpl(final String partName, final WindowContext parent) {
        super();

        this.partName = partName;
        this.parent = (WindowContextImpl) parent;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#getFormDesc()
     */
    public FormDesc getFormDesc() {
        return this.formDesc;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#getFormObject()
     */
    public Object getFormObject() {
        return this.formObj;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#getPartActionDesc()
     */
    public PartActionDesc getPartActionDesc() {
        return this.partActionDesc;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#getPartActionObject()
     */
    public Object getPartActionObject() {
        return this.partActionObj;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#getPartName()
     */
    public String getName() {
        return this.partName;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#setFormDesc(org.seasar.uruma.desc.FormDesc)
     */
    public void setFormDesc(final FormDesc desc) {
        this.formDesc = desc;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#setFormObject(java.lang.Object)
     */
    public void setFormObject(final Object object) {
        this.formObj = object;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#setPartActionDesc(org.seasar.uruma.desc.PartActionDesc)
     */
    public void setPartActionDesc(final PartActionDesc desc) {
        this.partActionDesc = desc;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#setPartActionObject(java.lang.Object)
     */
    public void setPartActionObject(final Object object) {
        this.partActionObj = object;
    }

    /*
     * @see org.seasar.uruma.context.PartContext#getWindowContext()
     */
    public WindowContext getWindowContext() {
        return parent;
    }

    /**
     * この {@link PartContext} を親 {@link WindowContext} から削除します。<br />
     */
    public void dispose() {
        parent.disposePartContext(partName);
        parent = null;
    }

}
