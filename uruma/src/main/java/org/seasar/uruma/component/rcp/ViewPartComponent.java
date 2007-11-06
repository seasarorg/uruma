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
package org.seasar.uruma.component.rcp;

import org.eclipse.ui.part.ViewPart;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.base.PartConfigurationElement;

/**
 * {@link ViewPart} のコンポーネント情報を保持するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ViewPartComponent extends PartConfigurationElement {
    /**
     * ビュータイトルです。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String title;

    /**
     * カテゴリ名称です。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String category;

    /**
     * イメージを指定するパスです。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String image;

    /**
     * 複数のオープンを許可するかどうかのフラグです。<br />
     */
    @RenderingPolicy(targetType = TargetType.NONE)
    public String allowMultiple;
}
