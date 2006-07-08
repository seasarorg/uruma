/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.component.impl;

import static org.seasar.jface.component.Inheritable.INHERITANCE_CHILD;
import static org.seasar.jface.component.Inheritable.INHERITANCE_CHILD_ONLY;
import static org.seasar.jface.component.Inheritable.INHERITANCE_DESCENDANT;
import static org.seasar.jface.component.Inheritable.INHERITANCE_DESCENDANT_ONLY;
import static org.seasar.jface.component.Inheritable.INHERITANCE_NONE;
import static org.seasar.jface.component.Inheritance.CHILD;
import static org.seasar.jface.component.Inheritance.CHILD_ONLY;
import static org.seasar.jface.component.Inheritance.DESCENDANT;
import static org.seasar.jface.component.Inheritance.DESCENDANT_ONLY;
import static org.seasar.jface.component.Inheritance.NONE;

import org.seasar.jface.component.Inheritance;

/**
 * <code>Inheritance</code> オブジェクトを生成するためのファクトリクラスです。</br>
 * 
 * @author y-komori
 */
public class InheritanceFactory {
    /**
     * 指定された文字列に従って、<code>Inheritance</code> オブジェクトを生成します。</br>
     * <p>
     * 文字列が <code>null</code>、<code>none</code>、<code>child</code>、
     * <code>childOnly</code>、<code>descendant</code>、<code>descendantOnly</code>
     * の場合、それぞれに応じた<code>Inheritance</code> オブジェクトを生成します。</br>
     * この際、大文字小文字は区別しません。</br>
     * </p>
     * <p>
     * 文字列が <code>null</code> または上記以外の場合、<code>Inheritance.NULL</code>
     * を返します。
     * </p>
     * 
     * @param str
     *            文字列
     * @return <code>Inheritance</code> オブジェクト
     * @see Inheritance
     */
    public static Inheritance createInheritance(final String str) {
        Inheritance inheritance = Inheritance.NULL;
        if (str != null) {
            if (INHERITANCE_NONE.equalsIgnoreCase(str)) {
                inheritance = NONE;
            } else if (INHERITANCE_CHILD.equalsIgnoreCase(str)) {
                inheritance = CHILD;
            } else if (INHERITANCE_CHILD_ONLY.equalsIgnoreCase(str)) {
                inheritance = CHILD_ONLY;
            } else if (INHERITANCE_DESCENDANT.equalsIgnoreCase(str)) {
                inheritance = DESCENDANT;
            } else if (INHERITANCE_DESCENDANT_ONLY.equalsIgnoreCase(str)) {
                inheritance = DESCENDANT_ONLY;
            }
        }
        return inheritance;
    }
}
