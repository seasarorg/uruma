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
package org.seasar.jface.events;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.EnabledDependBinder;
import org.seasar.jface.binding.MethodBinding;
import org.seasar.jface.binding.ValueBinder;
import org.seasar.jface.binding.WidgetBinder;
import org.seasar.jface.util.AssertionUtil;

/**
 * 任意のメソッドを実行できる、汎用的な {@link Listener} の実装クラスです。<br />
 * 
 * @author bskuroneko
 */
public class S2JFaceListener implements Listener {

    private WindowContext context;

    private MethodBinding methodBinding;

    /**
     * {@link S2JFaceListener} を構築します。<br />
     * 
     * @param context
     *            {@link WindowContext} オブジェクト
     * @param methodBinding
     *            {@link MethodBinding} オブジェクト
     */
    public S2JFaceListener(WindowContext context, MethodBinding methodBinding) {
        AssertionUtil.assertNotNull("context", context);
        AssertionUtil.assertNotNull("methodBinding", methodBinding);

        this.context = context;
        this.methodBinding = methodBinding;
    }

    /**
     * イベント処理を行います。<br />
     * <p>
     * 本メソッドでは、以下の処理を順に実行します。<br />
     * <ol>
     * <li>ターゲットオブジェクトへ、画面上のウィジットをバインドします。<br />
     * <li>ターゲットオブジェクトへ、画面の選択状態をバインド(ImportSelection)します。<br />
     * <li>ターゲットオブジェクトへ、画面の値をバインド(ImportValue)します。<br />
     * <li>コンストラクタで指定された {@link MethodBinding} の呼び出しを行います。<br />
     * <li>画面へ、ターゲットオブジェクトの値をバインド(ExportValue)します。<br />
     * <li>画面の選択状態ををターゲットオブジェクトのフィールドに従ってバインド(ExportSelection)します。<br />
     * </ol>
     * </p>
     * 
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event) {
        WidgetBinder.bindWidgets(methodBinding.getTarget(), context);
        ValueBinder.importSelection(context);
        ValueBinder.importValue(context);
        methodBinding.invoke(new Object[] { event });
        ValueBinder.exportValue(context);
        ValueBinder.exportSelection(context);
        EnabledDependBinder.updateEnabled(context);
    }
}
