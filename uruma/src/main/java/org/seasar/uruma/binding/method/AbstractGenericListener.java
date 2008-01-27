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

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Event;
import org.seasar.uruma.binding.value.ValueBindingSupport;
import org.seasar.uruma.binding.widget.WidgetBinder;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link MethodBinding} を使用してメソッドを呼び出す汎用リスナのための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractGenericListener {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(AbstractGenericListener.class);

    protected PartContext context;

    protected MethodBinding methodBinding;

    AbstractGenericListener(final PartContext context,
            final MethodBinding methodBinding) {
        AssertionUtil.assertNotNull("context", context);
        AssertionUtil.assertNotNull("methodBinding", methodBinding);
        this.methodBinding = methodBinding;
        this.context = context;
    }

    /**
     * {@link MethodBinding} に対する呼び出しを行います。<br />
     * 
     * @return メソッドの戻り値。メソッドの呼び出し中に例外が発生した場合は <code>null</code>。
     */
    protected Object invokeMethod() {
        return invokeMethod(null);
    }

    /**
     * {@link MethodBinding} に対する呼び出しを行います。<br />
     * 
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
     * @param event
     *            イベントオブジェクト
     * @return メソッドの戻り値。メソッドの呼び出し中に例外が発生した場合は <code>null</code>。
     */
    protected Object invokeMethod(final Object event) {
        try {
            WidgetBinder.bindWidgets(methodBinding.getTarget(), context);

            ValueBindingSupport.importSelection(context);
            ValueBindingSupport.importValue(context);

            Object result = methodBinding.invoke(new Object[] { event });

            if (!isDisposed(event)) {
                ValueBindingSupport.exportValue(context);
                ValueBindingSupport.exportSelection(context);
            }

            return result;
        } catch (Throwable ex) {
            logger.log(ex);
            return null;
        }
    }

    protected boolean isDisposed(final Object event) {
        if (event != null) {
            if (event instanceof Event) {
                return ((Event) event).widget.isDisposed();
            } else if (event instanceof TypedEvent) {
                return ((TypedEvent) event).widget.isDisposed();
            }
        }
        return false;
    }
}
