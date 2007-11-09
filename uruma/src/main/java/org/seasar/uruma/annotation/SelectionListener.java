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
package org.seasar.uruma.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.INullSelectionListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 選択状態の変化を受信するメソッドを指定するためのアノテーションです。<br />
 * <p>
 * {@link IWorkbenchPart} 上のメソッドに対して本アノテーションを付加すると、 {@link ISelectionProvider}
 * が発行する選択状態の変化を受け取ることができるようになります。<br />
 * メソッドの引数は0または1個でなくてはなりません。 {@link ISelection}
 * が内包するオブジェクトの型が引数の型に一致するか、内包オブジェクトが引数の示す型のサブクラスである場合のみ、メソッドが呼び出されます。
 * </p>
 * 
 * @see ISelectionListener
 * @author y-komori
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface SelectionListener {
    /**
     * メソッドが呼び出される際の {@link ISelection} の発行元を限定したい場合、発行元の <code>partId</code>
     * を指定します。<br />
     * 
     * @see ISelectionService#addSelectionListener(String, ISelectionListener)
     */
    String partId() default "";

    /**
     * <code>true</code> に指定すると、選択結果が <code>null</code>
     * にもメソッドが呼び出されるようになります。<br />
     * その場合の引数は <code>null</code> が渡されます。<br />
     * 
     * @see INullSelectionListener
     */
    boolean nullSelection() default false;

}
