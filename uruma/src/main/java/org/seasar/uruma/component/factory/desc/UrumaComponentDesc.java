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
package org.seasar.uruma.component.factory.desc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <code>urumaComponent</code> 要素の情報を保持するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaComponentDesc {
    private String tagName;

    private String componentClass;

    private String tagHandlerClass;

    private String rendererClass;

    private List<String> tagHandlerArgs;

    private List<String> rendererArgs;

    /**
     * タグ名称を返します。<br />
     * 
     * @return タグ名称
     */
    public String getTagName() {
        return this.tagName;
    }

    /**
     * タグ名称を設定します。<br />
     * 
     * @param tagName
     *            タグ名称
     */
    public void setTagName(final String tagName) {
        this.tagName = tagName;
    }

    /**
     * コンポーネントのクラス名を返します。<br />
     * 
     * @return コンポーネントのクラス名
     */
    public String getComponentClass() {
        return this.componentClass;
    }

    /**
     * コンポーネントのクラス名を設定します。<br />
     * 
     * @param componentClass
     *            コンポーネントのクラス名
     */
    public void setComponentClass(final String componentClass) {
        this.componentClass = componentClass;
    }

    /**
     * タグハンドラのクラス名を返します。<br />
     * 
     * @return タグハンドラのクラス名
     */
    public String getTagHandlerClass() {
        return this.tagHandlerClass;
    }

    /**
     * タグハンドラのクラス名を設定します。<br />
     * 
     * @param tagHandlerClass
     *            タグハンドラのクラス名
     */
    public void setTagHandlerClass(final String tagHandlerClass) {
        this.tagHandlerClass = tagHandlerClass;
    }

    /**
     * レンダラのクラス名を返します。<br />
     * 
     * @return レンダラのクラス名
     */
    public String getRendererClass() {
        return this.rendererClass;
    }

    /**
     * レンダラのクラス名を設定します。<br />
     * 
     * @param rendererClass
     *            レンダラのクラス名
     */
    public void setRendererClass(final String rendererClass) {
        this.rendererClass = rendererClass;
    }

    /**
     * タグハンドラクラスの引数を追加します。<br />
     * 
     * @param arg
     *            引数
     */
    public void addTagHandlerArg(final String arg) {
        if (arg == null) {
            return;
        }
        if (tagHandlerArgs == null) {
            tagHandlerArgs = new ArrayList<String>();
        }
        tagHandlerArgs.add(arg);
    }

    /**
     * タグハンドラクラスの引数リストを返します。<br />
     * 
     * @return 引数リスト
     */
    @SuppressWarnings("unchecked")
    public List<String> getTagHandlerArgs() {
        if (tagHandlerArgs != null) {
            return tagHandlerArgs;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * レンダラクラスの引数を追加します。<br />
     * 
     * @param arg
     *            引数
     */
    public void addRendererArg(final String arg) {
        if (arg == null) {
            return;
        }
        if (rendererArgs == null) {
            rendererArgs = new ArrayList<String>();
        }
        rendererArgs.add(arg);
    }

    /**
     * レンダラクラスの引数リストを返します。<br />
     * 
     * @return 引数リスト
     */
    @SuppressWarnings("unchecked")
    public List<String> getRendererArgs() {
        if (rendererArgs != null) {
            return rendererArgs;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(512);
        builder.append("tagName:" + getTagName());
        builder.append(" tagHandler:" + getTagHandlerClass());
        builder.append(listToString(getTagHandlerArgs()));
        builder.append(" renderer:" + getRendererClass());
        builder.append(listToString(getRendererArgs()));
        return builder.toString();
    }

    private String listToString(final List<?> list) {
        if (list == null) {
            return "()";
        }

        StringBuilder builder = new StringBuilder(512);
        builder.append("(");
        for (Object obj : list) {
            builder.append(obj.toString());
            builder.append(", ");
        }
        if (list.size() > 0) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append(")");
        return builder.toString();
    }
}
