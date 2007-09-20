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
package org.seasar.eclipse.rcp.ui;

import java.lang.reflect.Method;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.SingleParamTypeMethodBinding;
import org.seasar.jface.exception.MethodInvocationException;
import org.seasar.jface.util.AssertionUtil;

/**
 * 任意のメソッドを呼び出すことができる、汎用的な {@link ISelectionListener} の実装クラスです。<br />
 * <p>
 * 本クラスは {@link ISelectionListener} として振る舞い、 <code>selectionChanged</code>
 * メソッドが呼び出された際に、コンストラクタで渡される {@link Object}、{@link Method} の表すメソッドを呼び出します。<br />
 * このとき、コンストラクタで渡される {@link Method} オブジェクトの表す引数によって、呼び出される際の引数の渡し方が変化します。<br />
 * <dl>
 * <dt>引数がなしのとき
 * <dd>引数には何も渡されません。
 * <dt>引数が配列型以外で 1 個のとき
 * <dd><code>selectionChanged</code> メソッドで渡される {@link ISelection}
 * オブジェクトの持つ要素が引数の型に代入可能かチェックし、代入可能ならば最初の 1 個を渡します。
 * <dt>引数が配列型で 1 個のとき
 * <dd><code>selectionChanged</code> メソッドで渡される {@link ISelection}
 * オブジェクトの持つ要素が引数の型に代入可能かチェックし、代入可能ならば全ての要素を渡します。
 * <dt>引数が 2 個以上のとき
 * <dd>コンストラクタ呼び出し時に {@link IllegalArgumentException} をスローします。
 * </dl>
 * </p>
 * <p>
 * メソッドの呼び出しに失敗した場合は、{@link MethodInvocationException} をスローします。
 * </p>
 * 
 * @author y-komori
 */
public class GenericSelectionListener implements ISelectionListener {
    private WindowContext context;

    private SingleParamTypeMethodBinding methodBinding;

    /**
     * {@link GenericSelectionListener} を構築します。<br />
     * 
     * @param context
     *            {@link WindowContext} オブジェクト
     * @param methodBinding
     *            呼び出し対象の {@link SingleParamTypeMethodBinding} オブジェクト
     */
    public GenericSelectionListener(WindowContext context,
            SingleParamTypeMethodBinding methodBinding) {
        AssertionUtil.assertNotNull("context", context);
        AssertionUtil.assertNotNull("methodBinding", methodBinding);

        this.context = context;
        this.methodBinding = methodBinding;
    }

    /*
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object[] selectedModels = structuredSelection.toArray();

            methodBinding.invoke(selectedModels);
        }
    }
}
