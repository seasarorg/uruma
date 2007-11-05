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
package org.seasar.uruma.component.impl;

import org.eclipse.jface.window.Window;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link Window} のコンポーネント情報を保持するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class WindowComponent extends CompositeComponent {
    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("ウィンドウタイトル")
    private String title;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("最小幅")
    private String minimumWidth;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("最小高さ")
    private String minimumHeight;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("幅")
    private String width;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("高さ")
    private String height;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("X 座標")
    private String x = "center";

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("Y 座標")
    private String y = "middle";

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("イメージパス")
    private String image;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("デフォルトボタンID")
    private String defaultButtonId;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("デフォルトフォーカスID")
    private String defaultFocusId;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("ステータスラインの有無")
    private String statusLine;

    /**
     * {@link WindowComponent} を構築します。<br />
     * 
     */
    public WindowComponent() {
        super();
    }

    /**
     * ウィンドウタイトルを取得します。<br />
     * 
     * @return ウィンドウタイトル
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * ウィンドウタイトルを設定します。<br />
     * 
     * @param title
     *            ウィンドウタイトル
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * 最小高さを取得します。<br />
     * 
     * @return 最小高さ
     */
    public String getMinimumHeight() {
        return this.minimumHeight;
    }

    /**
     * 最小高さを設定します。<br />
     * 
     * @param minimumHeight
     *            最小高さ
     */
    public void setMinimumHeight(final String minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    /**
     * 最小幅を取得します。<br />
     * 
     * @return 最小幅
     */
    public String getMinimumWidth() {
        return this.minimumWidth;
    }

    /**
     * 最小幅を設定します。<br />
     * 
     * @param minimumWidth
     *            最小幅
     */
    public void setMinimumWidth(final String minimumWidth) {
        this.minimumWidth = minimumWidth;
    }

    /*
     * @see org.seasar.uruma.component.impl.ControlComponent#getHeight()
     */
    @Override
    public String getHeight() {
        return this.height;
    }

    /*
     * @see org.seasar.uruma.component.impl.ControlComponent#setHeight(java.lang.String)
     */
    @Override
    public void setHeight(final String height) {
        this.height = height;
    }

    /**
     * イメージパスを取得します。<br />
     * 
     * @return イメージパス
     */
    public String getImage() {
        return this.image;
    }

    /**
     * イメージパスを設定します。<br />
     * 
     * @param image
     *            イメージパス
     */
    public void setImage(final String image) {
        this.image = image;
    }

    /*
     * @see org.seasar.uruma.component.impl.ControlComponent#getWidth()
     */
    @Override
    public String getWidth() {
        return this.width;
    }

    /*
     * @see org.seasar.uruma.component.impl.ControlComponent#setWidth(java.lang.String)
     */
    @Override
    public void setWidth(final String width) {
        this.width = width;
    }

    /*
     * @see org.seasar.uruma.component.impl.ControlComponent#getX()
     */
    @Override
    public String getX() {
        return this.x;
    }

    /*
     * @see org.seasar.uruma.component.impl.ControlComponent#setX(java.lang.String)
     */
    @Override
    public void setX(final String x) {
        this.x = x;
    }

    /*
     * @see org.seasar.uruma.component.impl.ControlComponent#getY()
     */
    @Override
    public String getY() {
        return this.y;
    }

    /*
     * @see org.seasar.uruma.component.impl.ControlComponent#setY(java.lang.String)
     */
    @Override
    public void setY(final String y) {
        this.y = y;
    }

    /**
     * デフォルトボタンIDを取得します。<br />
     * 
     * @return デフォルトボタンID
     */
    public String getDefaultButtonId() {
        return this.defaultButtonId;
    }

    /**
     * デフォルトボタンIDを設定します。<br />
     * 
     * @param defaultButtonId
     *            デフォルトボタンID
     */
    public void setDefaultButtonId(final String defaultButtonId) {
        this.defaultButtonId = defaultButtonId;
    }

    /**
     * デフォルトフォーカスIDを取得します。<br />
     * 
     * @return デフォルトフォーカスID
     */
    public String getDefaultFocusId() {
        return this.defaultFocusId;
    }

    /**
     * デフォルトフォーカスIDを設定します。<br />
     * 
     * @param defaultFocusId
     *            デフォルトフォーカスID
     */
    public void setDefaultFocusId(final String defaultFocusId) {
        this.defaultFocusId = defaultFocusId;
    }

    /**
     * ステータスラインの有無を取得します。<br />
     * 
     * @return ステータスラインの有無
     */
    public String getStatusLine() {
        return this.statusLine;
    }

    /**
     * ステータスラインの有無を設定します。<br />
     * 
     * @param statusLine
     *            ステータスラインの有無
     */
    public void setStatusLine(final String statusLine) {
        this.statusLine = statusLine;
    }
}
