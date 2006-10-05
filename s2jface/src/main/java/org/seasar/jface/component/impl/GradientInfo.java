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

public class GradientInfo extends AbstractUIElement {

    private String vertical;

    private String startColor;

    private List<GradientItem> items = new ArrayList<GradientItem>();

    public String getStartColor() {
        return this.startColor;
    }

    public void setStartColor(String startColor) {
        this.startColor = startColor;
    }

    public String getVertical() {
        return this.vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public void addGradientItem(GradientItem item) {
        items.add(item);
    }

    public List<GradientItem> getGradientItems() {
        return items;
    }
}
