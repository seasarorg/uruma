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
package org.seasar.uruma.component;


/**
 * {@link UIElement} のためのビジターインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface UIElementVisitor {
    /**
     * {@link UIElement} を訪問します。<br />
     * 
     * @param element
     *            {@link UIElement} オブジェクト
     */
    public void visit(UIElement element);

    /**
     * {@link UIElementContainer} を訪問します。<br />
     * 
     * @param container
     *            {@link UIElementContainer} オブジェクト
     */
    public void visit(UIElementContainer container);
}
