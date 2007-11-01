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
package org.seasar.uruma.binding.enables;

import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.exception.UnsupportedClassException;

/**
 * 自ウィジットの選択状態によってターゲットのイネーブル状態を変更するためのリスナーインターフェースです。<br />
 * 
 * @author y-komori
 */
public abstract class EnablesDependingListener {
    protected WidgetHandle target;

    protected Widget enabledWidget;

    protected EnablesForType type;

    protected PropertyDesc enabledProperty;

    protected static final String ENABLED_PROPERTY_NAME = "enabled";

    /**
     * {@link EnablesDependingListener} を構築します。<br />
     * 
     * @param target
     *            ターゲットの {@link WidgetHandle}
     * @param enabled
     *            イネーブル状態を変更するウィジットの {@link WidgetHandle}
     * @param type
     *            選択条件を表す {@link EnablesForType}
     */
    protected EnablesDependingListener(final WidgetHandle target,
            final WidgetHandle enabled, final EnablesForType type) {
        if (!(enabled.getWidget() instanceof Widget)) {
            throw new UnsupportedClassException(enabled.getWidgetClass(),
                    Widget.class);
        }
        this.target = target;
        this.enabledWidget = enabled.<Widget> getCastWidget();
        this.type = type;
        BeanDesc desc = BeanDescFactory.getBeanDesc(enabled.getWidgetClass());
        this.enabledProperty = desc.getPropertyDesc(ENABLED_PROPERTY_NAME);
    }

    /**
     * コンストラクタで設定されたターゲットに対してリスナーを生成します。<br />
     * 本メソッドはサブクラスで実装してください。<br />
     */
    protected abstract void setupListener();

    /**
     * イネーブル状態を最新にします。<br />
     */
    protected abstract void updateEnableState();
}
