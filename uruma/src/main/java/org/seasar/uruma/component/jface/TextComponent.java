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

import org.eclipse.swt.widgets.Text;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link Text} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
@ComponentElement
public class TextComponent extends ControlComponent {

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @ComponentAttribute
    @FieldDescription("テキスト")
    public String text;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("ダブルクリック許可状態")
    public String doubleClickEnabled;

    @RenderingPolicy(conversionType = ConversionType.CHAR)
    @ComponentAttribute
    @FieldDescription("エコーキャラクタ")
    public String echoChar;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("編集可不可状態")
    public String editable;

    @RenderingPolicy(conversionType = ConversionType.SWT_CONST)
    @ComponentAttribute
    @FieldDescription("文字方向")
    public String orientation;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("選択開始位置")
    public String selectionStart;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("選択終了位置")
    public String selectionEnd;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("タブ数")
    public String tabs;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("最大文字数")
    public String textLimit;

    @RenderingPolicy(conversionType = ConversionType.INT, setTiming = SetTiming.RENDER_AFTER)
    @ComponentAttribute
    @FieldDescription("先頭表示位置")
    public String topIndex;
}
