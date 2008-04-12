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
package org.seasar.uruma.component.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.seasar.uruma.component.Template;
import org.seasar.uruma.util.AssertionUtil;

/**
 * Uruma のコンポーネントモデルを画面定義 XML ファイルへ出力するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ComponentModelWriter {
    private ComponentModelWriter() {

    }

    /**
     * {@link Template} オブジェクトを画面定義 XML ファイルへ出力します。<br />
     * 
     * @param template
     *            {@link Template} オブジェクト
     * @param path
     *            出力先パス
     * @throws IOException
     *             出力に失敗した場合
     */
    public static void writeTemplate(final Template template, final String path)
            throws IOException {
        AssertionUtil.assertNotNull("template", template);
        AssertionUtil.assertNotEmpty("path", path);

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        UIElementWriter uiElementWriter = new UIElementWriter(writer);
        template.accept(uiElementWriter);
    }
}