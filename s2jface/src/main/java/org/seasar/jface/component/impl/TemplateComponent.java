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
package org.seasar.jface.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;

/**
 * @author y-komori
 * 
 */
public class TemplateComponent extends UIComponentBase {
    private String sourcePath;

    private String name;

    private String extendsPath;

    private List<ExtendPoint> extendPoints = new ArrayList<ExtendPoint>();

    /**
     * 画面定義ファイルの読み込み元パスを取得します。</br>
     * 
     * @return 画面定義ファイルの読み込み元パス
     */
    public String getSourcePath() {
        return this.sourcePath;
    }

    public void setSourcePath(final String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getExtends() {
        return this.extendsPath;
    }

    public void setExtends(final String extendsPath) {
        this.extendsPath = extendsPath;
    }

    public void addExtendPoint(final String id, final boolean replace) {
        extendPoints.add(new ExtendPoint(id, replace));
    }

    public void addExtendPoint(final String id, final String attribute,
            final String value) {
        extendPoints.add(new ExtendPoint(id, attribute, value));
    }

    public List<ExtendPoint> getExtendPoints() {
        return extendPoints;
    }

    public WindowComponent getWindowComponent() {
        for (UIComponent uiComponent : children) {
            if (uiComponent instanceof WindowComponent) {
                return (WindowComponent) uiComponent;
            }
        }
        return null;
    }

    public void render(Composite parent, WindowContext context) {
    }

    public class ExtendPoint {
        private String id;

        private String property;

        private boolean replace;

        private String value;

        ExtendPoint(final String id, final boolean replace) {
            this.id = id;
            this.replace = replace;
        }

        ExtendPoint(final String id, final String property, final String value) {
            this.id = id;
            this.property = property;
            this.value = value;
        }

        public String getProperty() {
            return this.property;
        }

        public void setProperty(final String property) {
            this.property = property;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean getReplace() {
            return this.replace;
        }

        public void setReplace(boolean replace) {
            this.replace = replace;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
}
