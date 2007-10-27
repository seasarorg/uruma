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
package org.seasar.uruma.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.events.HelpListener;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;

/**
 * ダミーのアクションクラスです。<br />
 * 
 * @author y-komori
 */
public class DummyAction extends Action {
    /**
     * {@link DummyAction} を構築します。<br />
     * 
     */
    public DummyAction() {
        super();
    }

    /**
     * {@link DummyAction} を構築します。<br />
     * 
     * @param text
     *            テキスト
     */
    public DummyAction(final String text) {
        super(text);
    }

    /**
     * {@link DummyAction} を構築します。<br />
     * 
     * @param text
     *            テキスト
     * @param style
     *            スタイル
     */
    public DummyAction(final String text, final int style) {
        super(text, style);
    }

    @Override
    @Binding(bindingType = BindingType.MAY)
    public void setHelpListener(final HelpListener listener) {
        super.setHelpListener(listener);
    }

    @Override
    @Binding(bindingType = BindingType.MAY)
    public void setMenuCreator(final IMenuCreator creator) {
        super.setMenuCreator(creator);
    }
}
