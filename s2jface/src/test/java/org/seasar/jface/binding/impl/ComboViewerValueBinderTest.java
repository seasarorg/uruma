/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.binding.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Text;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.ExportSelection;
import org.seasar.jface.annotation.ExportValue;
import org.seasar.jface.annotation.Form;
import org.seasar.jface.annotation.ImportSelection;
import org.seasar.jface.annotation.ImportValue;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.renderer.impl.AbstractRendererTest;

@Form(ComboViewerValueBinderTest.class)
public class ComboViewerValueBinderTest extends AbstractRendererTest {
    @ExportValue
    private List<String> combo1 = new ArrayList<String>();

    @ImportValue(id = "combo1")
    private String combo1value;

    @ImportSelection(id = "combo1")
    private String combo1Selection;

    @ExportValue
    private String[] combo2 = new String[] { "111", "222", "333" };

    @ExportSelection(id = "combo2")
    private String combo2selection = "222";

    @ExportValue
    private String combo3 = "Hello";

    private Text text;

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
