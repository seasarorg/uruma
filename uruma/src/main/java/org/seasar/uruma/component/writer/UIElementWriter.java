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

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;

import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.UIElementContainer;
import org.seasar.uruma.component.UIElementVisitor;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.util.AnnotationUtil;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link UIElement} を XML ファイルへ出力するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class UIElementWriter implements UIElementVisitor, UrumaConstants {
    protected Writer writer;

    private int indentLevel;

    private static final String INDENT = "  ";

    /**
     * {@link UIElementWriter} を構築します。<br />
     * 
     * @param writer
     *            XML ファイルの出力先 {@link Writer} オブジェクト。
     */
    public UIElementWriter(final Writer writer) {
        AssertionUtil.assertNotNull("writer", writer);
        this.writer = writer;
    }

    /*
     * @see org.seasar.uruma.component.UIElementVisitor#visit(org.seasar.uruma.component.UIElement)
     */
    public void visit(final UIElement element) {
        AssertionUtil.assertNotNull("element", element);

        try {
            writeStartTag(element, true);
        } catch (IOException ex) {
            // TODO 自動生成された catch ブロック
            ex.printStackTrace();
        }
    }

    /*
     * @see org.seasar.uruma.component.UIElementVisitor#visit(org.seasar.uruma.component.UIElementContainer)
     */
    public void visit(final UIElementContainer container) {
        AssertionUtil.assertNotNull("container", container);

        try {
            writeStartTag(container, false);

            indent();
            for (UIElement element : container.getChildren()) {
                element.accept(this);
            }
            deindent();

            writeEndTag(container);
        } catch (IOException ex) {
            // TODO 自動生成された catch ブロック
            ex.printStackTrace();
        }
    }

    /*
     * @see org.seasar.uruma.component.UIElementVisitor#visit(org.seasar.uruma.component.Template)
     */
    public void visit(final Template template) {
        AssertionUtil.assertNotNull("template", template);
        try {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer
                    .write("<template xmlns=\"http://uruma.sandbox.seasar.org\">\n");
            indent();

            UIComponentContainer root = template.getRootComponent();
            if (root != null) {
                root.accept(this);
            }

            deindent();
            writer.write("</template>\n");
            writer.flush();
        } catch (IOException ex) {
            // TODO: handle exception
        }
    }

    protected void writeStartTag(final UIElement element,
            final boolean startTagOnly) throws IOException {
        String elementName = getElementName(element.getClass());
        if (elementName == null) {
            // TODO Warning 出力
            return;
        }

        writeIndent();
        writer.write('<');
        writer.write(elementName);

        List<Field> fields = AnnotationUtil.getAnnotatedFields(element
                .getClass(), ComponentAttribute.class);

        for (Field field : fields) {
            String attrName = getAttributeName(field);
            String value = FieldUtil.getString(field, element);
            if (value == null) {
                ComponentAttribute attr = field
                        .getAnnotation(ComponentAttribute.class);
                if (attr.required()) {
                    value = NULL_STRING;
                }
            }

            if (value != null) {
                writer.write(' ');
                writer.write(attrName);
                writer.write("=\"");
                writer.write(value);
                writer.write('"');
            }
        }

        if (startTagOnly) {
            writer.write(" />\n");
        } else {
            writer.write(" >\n");
        }
        writer.flush();
    }

    protected void writeEndTag(final UIElement element) throws IOException {
        String elementName = getElementName(element.getClass());
        if (elementName == null) {
            // TODO Warning 出力
            return;
        }

        writeIndent();
        writer.write("</");
        writer.write(elementName);
        writer.write(">\n");
        writer.flush();
    }

    /**
     * クラスオブジェクトから要素名を取得します。<br />
     * {@link ComponentElement} アノテーションを読み取り、その値を要素名として返します。<br />
     * {@link ComponentElement} アノテーションの値がない場合、クラス名のサフィックス
     * <code>Component</code> を除いた名称をデキャピタライズしたものを要素名とします。<br />
     * 【例】クラス名が ButtonComponent の場合、button が要素名になります。<br />
     * 
     * @param clazz
     *            要素名を取得する {@link Class} オブジェクト
     * @return 要素名。取得に失敗した場合は <code>null</code>。
     */
    protected String getElementName(final Class<?> clazz) {
        ComponentElement anno = clazz.getAnnotation(ComponentElement.class);
        if (anno == null) {
            return null;
        }

        String elementName = anno.value();
        if (NULL_STRING.equals(elementName)) {
            String className = clazz.getSimpleName();
            if (className.endsWith("Component")) {
                elementName = className.substring(0, className.length()
                        - "Component".length());
                elementName = StringUtil.decapitalize(elementName);
            } else {
                return null;
            }
        }

        return elementName;
    }

    protected String getAttributeName(final Field field) {
        ComponentAttribute attr = field.getAnnotation(ComponentAttribute.class);
        if (attr == null) {
            return null;
        }

        String attrName = attr.name();
        if (NULL_STRING.equals(attrName)) {
            attrName = field.getName();
        }
        return attrName;
    }

    protected void writeIndent() throws IOException {
        StringBuffer buf = new StringBuffer(INDENT.length() * indentLevel);
        for (int i = 0; i < indentLevel; i++) {
            buf.append(INDENT);
        }
        writer.write(buf.toString());
    }

    /**
     * インデントをリセットします。<br />
     */
    public void resetIndent() {
        indentLevel = 0;
    }

    /**
     * インデントレベルを下げます。<br />
     */
    public void indent() {
        indentLevel++;
    }

    /**
     * インデントレベルを上げます。<br />
     */
    public void deindent() {
        indentLevel--;
    }
}
