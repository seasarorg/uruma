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
package org.seasar.uruma.rcp.binding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;

/**
 * 任意のメソッドを呼び出すことができる、汎用的な {@link IHandler} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class GenericHandler extends AbstractHandler {
    protected static final UrumaLogger logger = UrumaLogger
            .getLogger(GenericHandler.class);

    protected List<Listener> listeners = new ArrayList<Listener>();

    protected boolean enabled = true;

    /*
     * @see
     * org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands
     * .ExecutionEvent)
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        int size = listeners.size();
        for (int i = 0; i < size; i++) {
            try {
                listeners.get(i).handleEvent((Event) event.getTrigger());
            } catch (Throwable ex) {
                logger.log(ex);
            }
        }
        return null;
    }

    /**
     * {@link Listener} を追加します。<br />
     * 
     * @param listener
     *            {@link Listener} オブジェクト
     */
    public void addListener(final Listener listener) {
        AssertionUtil.assertNotNull("listener", listener);
        this.listeners.add(listener);
    }

    /*
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * ハンドラのイネーブル状態を変更します。<br />
     * 
     * @param enabled
     *            <code>true</code> の場合、イネーブル。<code>false</code> の場合、ディスエーブル。
     */
    public void setEnabled(final boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            fireHandlerChanged(new HandlerEvent(this, true, false));
        }
    }
}
