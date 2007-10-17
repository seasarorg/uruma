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

import org.eclipse.ui.part.ViewPart;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link ViewPart} のコンポーネント情報を保持するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ViewPartComponent extends CompositeComponent {
    @RenderingPolicy(targetType = TargetType.NONE)
    private String name;

    @RenderingPolicy(targetType = TargetType.NONE)
    private String category;

    @RenderingPolicy(targetType = TargetType.NONE)
    private String image;

    @RenderingPolicy(targetType = TargetType.NONE)
    private boolean allowMultiple;

    /**
     * name を取得します。<br />
     */
    public String getName() {
        return this.name;
    }

    /**
     * name を設定します。<br />
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * category を取得します。<br />
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * category を設定します。<br />
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * image を取得します。<br />
     */
    public String getImage() {
        return this.image;
    }

    /**
     * image を設定します。<br />
     */
    public void setImage(final String image) {
        this.image = image;
    }

    /**
     * allowMultiple を取得します。<br />
     */
    public boolean getAllowMultiple() {
        return this.allowMultiple;
    }

    /**
     * allowMultiple を設定します。<br />
     */
    public void setAllowMultiple(final boolean allowMultiple) {
        this.allowMultiple = allowMultiple;
    }
}
