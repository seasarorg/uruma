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
package org.seasar.jface.component;

/**
 * <code>Property</code> 要素の子要素への引き継ぎを指定するための列挙型です。
 * <dl>
 * <dt><code>NULL</code>
 * <dd>未指定の状態です。各レンダラのデフォルト値が利用されます。(デフォルト)
 * <dt><code>NONE</code>
 * <dd>子要素へ引き継ぎません。
 * <dt><code>CHILD</code>
 * <dd>直近の子要素へのみ引き継ぎます。
 * <dt><code>CHILD_ONLY</code>
 * <dd>直近の子要素へのみ引き継ぎ、自要素へは適用しません。
 * <dt><code>DESCENDANT</code>
 * <dd>すべての子要素へ引き継ぎます。
 * <dt><code>DESCENDANT_ONLY</code>
 * <dd>すべての子要素へ引き継ぎ、自要素へは適用しません。
 * </dl>
 * 
 * @author y-komori
 */
public enum Inheritance {
    NULL, NONE, CHILD, CHILD_ONLY, DESCENDANT, DESCENDANT_ONLY
}
