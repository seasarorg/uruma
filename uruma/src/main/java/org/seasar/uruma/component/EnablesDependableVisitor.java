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

import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.binding.enables.EnablesDependingDef;
import org.seasar.uruma.binding.enables.EnablesForType;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;

/**
 * コンポーネントツリーを走査して、 {@link EnablesDependable} の登録を行うための {@link UIElementVisitor}
 * です。<br />
 * 
 * @author y-komori
 */
public class EnablesDependableVisitor implements UIElementVisitor,
        UrumaMessageCodes {
    private WindowContext context;

    /**
     * {@link EnablesDependableVisitor} を構築します。<br />
     * 
     * @param context
     */
    public EnablesDependableVisitor(final WindowContext context) {
        this.context = context;
    }

    /*
     * @see org.seasar.uruma.component.UIElementVisitor#visit(org.seasar.uruma.component.UIElement)
     */
    public void visit(final UIElement element) {
        if ((element instanceof UIComponent)
                && (element instanceof EnablesDependable)) {
            EnablesDependable dependable = (EnablesDependable) element;
            String id = ((UIComponent) element).getId();

            if (id != null) {

                String enablesDependingId = dependable.getEnablesDependingId();
                String enablesForType = dependable.getEnablesFor();

                if (!StringUtil.isEmpty(enablesDependingId)) {
                    EnablesForType type = EnablesForType.SELECTION;
                    if (!StringUtil.isEmpty(enablesForType)) {
                        type = EnablesForType.valueOf(enablesForType);
                    }

                    WidgetHandle handle = context.getWidgetHandle(id);
                    if (handle != null) {
                        EnablesDependingDef def = new EnablesDependingDef(
                                handle, enablesDependingId, type);
                        context.addEnablesDependingDef(def);
                    } else {
                        throw new NotFoundException(UICOMPONENT_NOT_FOUND, id);
                    }
                }
            }
        }
    }

    /*
     * @see org.seasar.uruma.component.UIElementVisitor#visit(org.seasar.uruma.component.UIElementContainer)
     */
    public void visit(final UIElementContainer container) {
        for (UIElement element : container.getChildren()) {
            element.accept(this);
        }
    }
}
