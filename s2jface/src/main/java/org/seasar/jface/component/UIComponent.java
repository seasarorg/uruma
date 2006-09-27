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

import java.util.List;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.renderer.Renderer;

/**
 * @author y-komori
 */
public interface UIComponent extends UIElement {
    public String getId();

    public void setId(String id);

    public String getStyle();

    public void setStyle(String style);

    public void setParent(UIComponent parent);

    public UIComponent getParent();

    public void setRenderer(Renderer renderer);

    public Widget getWidget();

    public void setWidget(Widget widget);
    
    public void addChild(UIComponent child);

    public List<UIComponent> getChildren();

    /**
     * 設定されたレンダラを利用して、レンダリングを行います。</br>
     * 
     * @param parent
     *            親となる <code>Widget</code> オブジェクト
     * @param context
     *            <code>WindowContext</code> オブジェクト
     * @see WindowContext
     */
    public void render(Widget parent, WindowContext context);
}
