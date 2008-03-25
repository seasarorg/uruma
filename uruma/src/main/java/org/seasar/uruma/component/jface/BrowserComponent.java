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

import org.eclipse.swt.browser.Browser;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * {@link Browser} を表すコンポーネントです。<br />
 * 
 * @author y.sugigami
 */
public class BrowserComponent extends ControlComponent {

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @FieldDescription("テキスト")
    public String text;

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @FieldDescription("URL")
    public String url;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("前ぺージに戻るの許可状態")
    public String backEnabled;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("次ぺージに戻るの許可状態")
    public String forwardEnabled;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("フォーカスの許可状態")
    public String focusControl;
}
