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
package org.seasar.uruma.renderer;

import java.util.HashMap;
import java.util.Map;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.impl.ButtonComponent;
import org.seasar.uruma.component.impl.CTabFolderComponent;
import org.seasar.uruma.component.impl.CTabItemComponent;
import org.seasar.uruma.component.impl.CanvasComponent;
import org.seasar.uruma.component.impl.ComboComponent;
import org.seasar.uruma.component.impl.CompositeComponent;
import org.seasar.uruma.component.impl.CoolBarComponent;
import org.seasar.uruma.component.impl.CoolItemComponent;
import org.seasar.uruma.component.impl.GroupComponent;
import org.seasar.uruma.component.impl.LabelComponent;
import org.seasar.uruma.component.impl.LinkComponent;
import org.seasar.uruma.component.impl.MenuComponent;
import org.seasar.uruma.component.impl.MenuItemComponent;
import org.seasar.uruma.component.impl.ProgressBarComponent;
import org.seasar.uruma.component.impl.SashFormComponent;
import org.seasar.uruma.component.impl.ScaleComponent;
import org.seasar.uruma.component.impl.SeparatorComponent;
import org.seasar.uruma.component.impl.SliderComponent;
import org.seasar.uruma.component.impl.SpinnerComponent;
import org.seasar.uruma.component.impl.TabFolderComponent;
import org.seasar.uruma.component.impl.TabItemComponent;
import org.seasar.uruma.component.impl.TableColumnComponent;
import org.seasar.uruma.component.impl.TableComponent;
import org.seasar.uruma.component.impl.TableItemComponent;
import org.seasar.uruma.component.impl.TextComponent;
import org.seasar.uruma.component.impl.ToolBarComponent;
import org.seasar.uruma.component.impl.ToolItemComponent;
import org.seasar.uruma.component.impl.TreeComponent;
import org.seasar.uruma.component.impl.TreeItemComponent;
import org.seasar.uruma.component.impl.ViewPartComponent;
import org.seasar.uruma.component.impl.WindowComponent;
import org.seasar.uruma.component.impl.WorkbenchComponent;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.renderer.impl.ButtonRenderer;
import org.seasar.uruma.renderer.impl.CTabFolderRenderer;
import org.seasar.uruma.renderer.impl.CTabItemRenderer;
import org.seasar.uruma.renderer.impl.CanvasRenderer;
import org.seasar.uruma.renderer.impl.ComboViewerRenderer;
import org.seasar.uruma.renderer.impl.CompositeRenderer;
import org.seasar.uruma.renderer.impl.CoolBarRenderer;
import org.seasar.uruma.renderer.impl.CoolItemRenderer;
import org.seasar.uruma.renderer.impl.GroupRenderer;
import org.seasar.uruma.renderer.impl.LabelRenderer;
import org.seasar.uruma.renderer.impl.LinkRenderer;
import org.seasar.uruma.renderer.impl.MenuItemRenderer;
import org.seasar.uruma.renderer.impl.MenuManagerRenderer;
import org.seasar.uruma.renderer.impl.NullRenderer;
import org.seasar.uruma.renderer.impl.ProgressBarRenderer;
import org.seasar.uruma.renderer.impl.SashFormRenderer;
import org.seasar.uruma.renderer.impl.ScaleRenderer;
import org.seasar.uruma.renderer.impl.SeparatorRenderer;
import org.seasar.uruma.renderer.impl.SliderRenderer;
import org.seasar.uruma.renderer.impl.SpinnerRenderer;
import org.seasar.uruma.renderer.impl.TabFolderRenderer;
import org.seasar.uruma.renderer.impl.TabItemRenderer;
import org.seasar.uruma.renderer.impl.TableColumnRenderer;
import org.seasar.uruma.renderer.impl.TableItemRenderer;
import org.seasar.uruma.renderer.impl.TableViewerRenderer;
import org.seasar.uruma.renderer.impl.TextRenderer;
import org.seasar.uruma.renderer.impl.ToolBarRenderer;
import org.seasar.uruma.renderer.impl.ToolItemRenderer;
import org.seasar.uruma.renderer.impl.TreeItemRenderer;
import org.seasar.uruma.renderer.impl.TreeViewerRenderer;
import org.seasar.uruma.renderer.impl.ViewPartRenderer;
import org.seasar.uruma.renderer.impl.WindowRenderer;
import org.seasar.uruma.util.AssertionUtil;

/**
 * レンダラを取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class RendererFactrory {
    private static final Map<Class<? extends UIComponent>, Renderer> rendererMap = new HashMap<Class<? extends UIComponent>, Renderer>();

    static {
        addRenderer(WindowComponent.class, new WindowRenderer());
        addRenderer(ViewPartComponent.class, new ViewPartRenderer());

        addRenderer(CompositeComponent.class, new CompositeRenderer());
        addRenderer(ComboComponent.class, new ComboViewerRenderer());
        addRenderer(TabFolderComponent.class, new TabFolderRenderer());
        addRenderer(TabItemComponent.class, new TabItemRenderer());
        addRenderer(TableComponent.class, new TableViewerRenderer());
        addRenderer(TableColumnComponent.class, new TableColumnRenderer());
        addRenderer(TableItemComponent.class, new TableItemRenderer());
        addRenderer(SpinnerComponent.class, new SpinnerRenderer());
        addRenderer(CTabFolderComponent.class, new CTabFolderRenderer());
        addRenderer(CTabItemComponent.class, new CTabItemRenderer());
        addRenderer(GroupComponent.class, new GroupRenderer());
        addRenderer(ToolBarComponent.class, new ToolBarRenderer());
        addRenderer(ToolItemComponent.class, new ToolItemRenderer());
        addRenderer(CoolBarComponent.class, new CoolBarRenderer());
        addRenderer(CoolItemComponent.class, new CoolItemRenderer());
        addRenderer(CanvasComponent.class, new CanvasRenderer());
        addRenderer(SashFormComponent.class, new SashFormRenderer());

        addRenderer(TreeComponent.class, new TreeViewerRenderer());
        addRenderer(TreeItemComponent.class, new TreeItemRenderer());

        addRenderer(ButtonComponent.class, new ButtonRenderer());
        addRenderer(LabelComponent.class, new LabelRenderer());
        addRenderer(TextComponent.class, new TextRenderer());
        addRenderer(ProgressBarComponent.class, new ProgressBarRenderer());
        addRenderer(ScaleComponent.class, new ScaleRenderer());
        addRenderer(SliderComponent.class, new SliderRenderer());
        addRenderer(LinkComponent.class, new LinkRenderer());

        addRenderer(MenuComponent.class, new MenuManagerRenderer());
        addRenderer(MenuItemComponent.class, new MenuItemRenderer());
        addRenderer(SeparatorComponent.class, new SeparatorRenderer());

        addRenderer(WorkbenchComponent.class, new NullRenderer());
    }

    /**
     * {@link UIComponent} クラスをキーにして、レンダラを取得します。</br>
     * 
     * @param uiComponentClass
     *            レンダラに対応する {@link UIComponent} の {@link Class} オブジェクト
     * @return レンダラオブジェクト
     * @throws NotFoundException
     *             レンダラが見つからなかった場合
     */
    public static Renderer getRenderer(
            final Class<? extends UIComponent> uiComponentClass) {
        Renderer renderer = rendererMap.get(uiComponentClass);
        if (renderer != null) {
            return renderer;
        } else {
            throw new NotFoundException(UrumaMessageCodes.RENDERER_NOT_FOUND,
                    uiComponentClass.getName());
        }
    }

    /**
     * {@link UIComponent} クラスをキーとして、レンダラを登録します。</br>
     * 
     * @param uiComponentClass
     *            {@link UIComponent} クラスの {@link Class} オブジェクト
     * @param renderer
     *            レンダラオブジェクト
     */
    public static void addRenderer(
            final Class<? extends UIComponent> uiComponentClass,
            final Renderer renderer) {
        AssertionUtil.assertNotNull("uiComponentClass", uiComponentClass);
        AssertionUtil.assertNotNull("renderer", renderer);
        rendererMap.put(uiComponentClass, renderer);
    }
}
