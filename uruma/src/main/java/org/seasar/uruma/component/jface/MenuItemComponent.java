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
package org.seasar.uruma.component.jface;

import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.base.AbstractItemComponent;

/**
 * メニュー項目情報を保持するためのコンポーネントです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
public class MenuItemComponent extends AbstractItemComponent implements
        EnablesDependable {

    /**
     * プッシュボタンスタイルを表す文字列です。<br />
     */
    public static final String PUSH = "PUSH";

    /**
     * ラジオボタンスタイルを表す文字列です。<br />
     */
    public static final String RADIO = "RADIO";

    /**
     * チェックボックススタイルを表す文字列です。<br />
     */
    public static final String CHECK = "CHECK";

    /**
     * アクセラレータです。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String accelerator;

    /**
     * イネーブル状態です。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String enabled;

    /**
     * 選択状態です。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String selection;

    /**
     * 選択不可時のイメージパスです。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String disabledImage;

    /**
     * 選択時のイメージパスです。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String hoverImage;

    /**
     * 説明テキストです。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String description;

    /**
     * 依存先コンポーネントの ID です。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String enablesDependingId;

    /**
     * 依存方法です。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String enablesFor;

    /*
     * @see org.seasar.uruma.component.base.AbstractItemComponent#setText(java.lang.String)
     */
    @Override
    public void setText(final String text) {
        super.setText(text);
    }

    /*
     * @see org.seasar.uruma.component.base.AbstractItemComponent#setImage(java.lang.String)
     */
    @Override
    public void setImage(final String image) {
        super.setImage(image);
    }

    /*
     * @see org.seasar.uruma.component.EnablesDependable#getEnablesDependingId()
     */
    public String getEnablesDependingId() {
        return this.enablesDependingId;
    }

    /*
     * @see org.seasar.uruma.component.EnablesDependable#getEnablesForType()
     */
    public String getEnablesFor() {
        return this.enablesFor;
    }
}
