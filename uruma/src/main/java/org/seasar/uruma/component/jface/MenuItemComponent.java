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
package org.seasar.uruma.component.jface;

import java.io.Writer;
import java.util.Collections;
import java.util.List;

import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.base.AbstractItemComponent;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;
import org.seasar.uruma.rcp.configuration.ConfigurationWriterFactory;

/**
 * メニュー項目情報を保持するためのコンポーネントです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 * @see <a
 *      href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_actionSets.html">ActionSets</a>
 */
public class MenuItemComponent extends AbstractItemComponent implements
        EnablesDependable, ConfigurationElement {
    protected ConfigurationWriter configurationWriter;

    /**
     * {@link MenuItemComponent} を構築します。<br />
     */
    public MenuItemComponent() {
        super();
        setConfigurationWriter(ConfigurationWriterFactory
                .getConfigurationWriter(getClass()));
    }

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
    @ConfigurationAttribute
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
    @ConfigurationAttribute(name = "disabledIcon")
    public String disabledImage;

    /**
     * 選択時のイメージパスです。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    @ConfigurationAttribute(name = "hoverIcon")
    public String hoverImage;

    /**
     * 説明テキストです。
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    @ConfigurationAttribute(name = "tooltip")
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

    /**
     * RCP 環境における ID です。
     */
    @ConfigurationAttribute(name = "id", required = true)
    private String rcpId;

    /**
     * RCP 環境における表示ラベルです。<br />
     */
    @ConfigurationAttribute(required = true)
    public String label;

    /**
     * アイコンのパスです。<br />
     */
    @ConfigurationAttribute
    public String icon;

    /*
     * @see org.seasar.uruma.component.base.AbstractItemComponent#setText(java.lang.String)
     */
    @Override
    public void setText(final String text) {
        super.setText(text);
        this.label = text;
    }

    /*
     * @see org.seasar.uruma.component.base.AbstractItemComponent#setImage(java.lang.String)
     */
    @Override
    public void setImage(final String image) {
        super.setImage(image);
        this.icon = image;
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

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#getElements()
     */
    @SuppressWarnings("unchecked")
    public List<ConfigurationElement> getElements() {
        return Collections.EMPTY_LIST;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#getRcpId()
     */
    public String getRcpId() {
        return this.rcpId;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#setRcpId(java.lang.String)
     */
    public void setRcpId(final String rcpId) {
        this.rcpId = rcpId;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#setConfigurationWriter(org.seasar.uruma.rcp.configuration.ConfigurationWriter)
     */
    public void setConfigurationWriter(final ConfigurationWriter writer) {
        this.configurationWriter = writer;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#writeConfiguration(java.io.Writer)
     */
    public void writeConfiguration(final Writer writer) {
        if (configurationWriter != null) {
            configurationWriter.writeStartTag(this, writer);
            configurationWriter.writeEndTag(this, writer);
        }
    }
}
