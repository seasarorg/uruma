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
package org.seasar.uruma.binding.value.binder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Text;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.ImportValue;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.renderer.impl.AbstractGUITest;

@Form(ComboViewerValueBinderGUITest.class)
public class ComboViewerValueBinderGUITest extends AbstractGUITest {
    @ExportValue
    public List<String> combo1 = new ArrayList<String>();

    @ImportValue(id = "combo1")
    public String combo1value;

    @ImportSelection(id = "combo1")
    public String combo1Selection;

    @ExportValue
    public String[] combo2 = new String[] { "111", "222", "333" };

    @ExportSelection(id = "combo2")
    public String combo2selection = "222";

    @ExportValue
    public String combo3 = "Hello";

    public Text text;

    @InitializeMethod
    public void initialize() {
        combo1.add("AAA");
        combo1.add("BBB");
        combo1.add("CCC");
    }

    @EventListener(id = "select")
    public void selectAction() {
        text.setText("Combo1Value=" + combo1value + "\n" + "Combo1Selection="
                + combo1Selection);
    }
}
