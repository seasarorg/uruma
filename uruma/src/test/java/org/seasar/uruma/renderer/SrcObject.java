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
package org.seasar.uruma.renderer;

import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.ACCELERATOR;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.BOOLEAN;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.CHAR;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.COLOR;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.IMAGE;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.INT;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.INT_ARRAY;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.STRING;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.SWT_CONST;
import static org.seasar.uruma.annotation.RenderingPolicy.ConversionType.TEXT;
import static org.seasar.uruma.annotation.RenderingPolicy.TargetType.FIELD;
import static org.seasar.uruma.annotation.RenderingPolicy.TargetType.NONE;

import org.seasar.uruma.component.base.AbstractUIElement;
import org.seasar.uruma.annotation.RenderingPolicy;

/**
 * {@link RendererSupportUtil} のテストのためのクラスです。<br />
 * 
 * @author y-komori
 */
public class SrcObject extends AbstractUIElement {

    @RenderingPolicy(targetType = NONE)
    private String nonTargetField = "NonTarget";

    @RenderingPolicy(targetType = FIELD, conversionType = STRING)
    private String stringField = "StringField1";

    @RenderingPolicy(targetType = FIELD, conversionType = TEXT)
    private String textField = "Text\\tField1\\nText\\tField1\\n";

    @RenderingPolicy(targetType = FIELD, conversionType = INT)
    private String intField = "123";

    @RenderingPolicy(targetType = FIELD, conversionType = INT_ARRAY)
    private String intArrayField = "1, 2, 3";

    @RenderingPolicy(targetType = FIELD, conversionType = BOOLEAN)
    private String booleanField = "true";

    @RenderingPolicy(targetType = FIELD, conversionType = COLOR)
    private String colorField = "#FFFFFF";

    @RenderingPolicy(targetType = FIELD, conversionType = SWT_CONST)
    private String swtConstField = "YES";

    @RenderingPolicy(targetType = FIELD, conversionType = IMAGE)
    private String imageField = "/images/container.gif";

    @RenderingPolicy(targetType = FIELD, conversionType = ACCELERATOR)
    private String acceleratorField = "Ctrl+Alt+A";

    @RenderingPolicy(targetType = FIELD, conversionType = CHAR)
    private String charField = "A";

    @RenderingPolicy(conversionType = STRING)
    private String stringProperty = "StringField2";

    @RenderingPolicy(conversionType = TEXT)
    private String textProperty = "Text\\tField2\\nText\\tField2\\n";

    @RenderingPolicy(conversionType = INT)
    private String intProperty = "456";

    @RenderingPolicy(conversionType = INT_ARRAY)
    private String intArrayProperty = "3";

    @RenderingPolicy(conversionType = BOOLEAN)
    private String booleanProperty = "false";

    @RenderingPolicy(conversionType = COLOR)
    private String colorProperty = "#000000";

    @RenderingPolicy(conversionType = SWT_CONST)
    private String swtConstProperty = "NO";

    @RenderingPolicy(conversionType = IMAGE)
    private String imageProperty = "../../../../images/container.gif";

    @RenderingPolicy(conversionType = ACCELERATOR)
    private String acceleratorProperty = "F2";

    @RenderingPolicy(conversionType = CHAR)
    private String charProperty = "x";

    public String getNonTargetField() {
        return this.nonTargetField;
    }

    public String getBooleanField() {
        return this.booleanField;
    }

    public String getBooleanProperty() {
        return this.booleanProperty;
    }

    public String getColorField() {
        return this.colorField;
    }

    public String getColorProperty() {
        return this.colorProperty;
    }

    public String getIntField() {
        return this.intField;
    }

    public String getIntProperty() {
        return this.intProperty;
    }

    public String getIntArrayField() {
        return this.intArrayField;
    }

    public String getIntArrayProperty() {
        return this.intArrayProperty;
    }

    public String getStringField() {
        return this.stringField;
    }

    public String getStringProperty() {
        return this.stringProperty;
    }

    public String getSwtConstField() {
        return this.swtConstField;
    }

    public String getSwtConstProperty() {
        return this.swtConstProperty;
    }

    public String getTextField() {
        return this.textField;
    }

    public String getTextProperty() {
        return this.textProperty;
    }

    public String getImageField() {
        return this.imageField;
    }

    public String getImageProperty() {
        return this.imageProperty;
    }

    public String getAcceleratorField() {
        return this.acceleratorField;
    }

    public String getAcceleratorProperty() {
        return this.acceleratorProperty;
    }

    public String getCharField() {
        return this.charField;
    }

    public String getCharProperty() {
        return this.charProperty;
    }

}
