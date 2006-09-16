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

import org.eclipse.swt.widgets.Composite;
import org.seasar.jface.WindowContext;
import org.seasar.jface.renderer.Renderer;

/**
 * @author y-komori
 */
public interface UIComponent extends UIElement {
    public String getId();

    public void setId(String id);

    public void setParent(UICompositeComponent parent);

    public UICompositeComponent getParent();

    public LayoutDataInfo getLayoutDataInfo();

    public void setLayoutDataInfo(LayoutDataInfo layoutDataInfo);

    public void setRenderer(Renderer renderer);

    /**
     * 設定されたレンダラを利用して、レンダリングを行います。</br>
     * 
     * @param parent
     *            親コンポジット
     * @param context
     *            <code>WindowContext</code> オブジェクト
     * @see WindowContext
     */
    public void render(Composite parent, WindowContext context);
}
