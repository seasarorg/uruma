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

import org.eclipse.swt.widgets.DateTime;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;

/**
 * {@link DateTime} に対応するコンポーネントです。<br />
 * 
 * @author yazaki
 */
public class DateTimeComponent extends ControlComponent {

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("時")
    public String hours;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("分")
    public String minutes;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("秒")
    public String seconds;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("年")
    public String year;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("月")
    public String month;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("日")
    public String day;

}
