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
package org.seasar.uruma.binding.method;

import org.eclipse.jface.window.Window;
import org.seasar.uruma.context.PartContext;

/**
 * ウィンドウを閉じる直前に呼び出されるリスナクラスです。<br />
 * 
 * @author y-komori
 */
public class WindowCloseListener extends AbstractGenericListener {

    /**
     * {@link WindowCloseListener} を構築します。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     * @param methodBinding
     *            {@link MethodBinding} オブジェクト
     */
    public WindowCloseListener(final PartContext context,
            final MethodBinding methodBinding) {
        super(context, methodBinding);
    }

    /**
     * ウィンドウクローズを許可してよいかを調べます。<br />
     * 
     * @return ウィンドウクローズを許可する場合は <code>true</code>、 許可しない場合は
     *         <code>false</code>。
     */
    public boolean canWindowClose(final Window window) {
        Object ret = invokeMethod();
        if (ret instanceof Boolean) {
            return ((Boolean) ret).booleanValue();
        } else {
            return true;
        }
    }
}
