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
package org.seasar.uruma.binding.method;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.uruma.exception.MethodInvocationException;

/**
 * 汎用的な {@link IAction} クラスです。<br />
 * 
 * @author y-komori
 */
public class GenericAction extends Action {

    private Listener listener;

    /**
     * {@link GenericAction} を構築します。<br />
     * 
     */
    public GenericAction() {
        super();
    }

    /**
     * {@link GenericAction} を構築します。<br />
     * 
     * @param text
     *            テキスト
     */
    public GenericAction(final String text) {
        super(text);
    }

    /**
     * {@link GenericAction} を構築します。<br />
     * 
     * @param text
     *            テキスト
     * @param style
     *            スタイル
     */
    public GenericAction(final String text, final int style) {
        super(text, style);
    }

    /**
     * {@link Listener} を設定します。<br />
     * 
     * @param listener
     *            {@link Listener} オブジェクト
     */
    public void setListener(final Listener listener) {
        this.listener = listener;
    }

    /*
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void runWithEvent(final Event event) {
        try {
            if (listener != null) {
                listener.handleEvent(event);
            }
        } catch (Throwable ex) {
            throw new MethodInvocationException(ex);
        }
    }

    /*
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        runWithEvent(null);
    }

    /*
     * @see org.eclipse.jface.action.Action#setHelpListener(org.eclipse.swt.events.HelpListener)
     */
    @Override
    @Binding(bindingType = BindingType.MAY)
    public void setHelpListener(final HelpListener listener) {
        super.setHelpListener(listener);
    }

    /*
     * @see org.eclipse.jface.action.Action#setMenuCreator(org.eclipse.jface.action.IMenuCreator)
     */
    @Override
    @Binding(bindingType = BindingType.MAY)
    public void setMenuCreator(final IMenuCreator creator) {
        super.setMenuCreator(creator);
    }

}
