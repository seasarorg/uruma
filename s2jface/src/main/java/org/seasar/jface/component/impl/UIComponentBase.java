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

import static org.seasar.jface.component.Inheritance.CHILD;
import static org.seasar.jface.component.Inheritance.CHILD_ONLY;
import static org.seasar.jface.component.Inheritance.DESCENDANT;
import static org.seasar.jface.component.Inheritance.DESCENDANT_ONLY;
import static org.seasar.jface.component.Inheritance.NONE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.component.Inheritance;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.renderer.Renderer;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 * 
 */
public abstract class UIComponentBase implements UIComponent {

    private String id;

    protected UIComponent parent;

    protected List<UIComponent> children = new ArrayList<UIComponent>();

    protected Renderer renderer;

    private String renderType;

    private Map<String, Property> properties = new HashMap<String, Property>();

    private String basePath;

    private Widget widget;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void addChild(final UIComponent child) {
        AssertionUtil.assertNotNull("child", child);
        children.add(child);
        child.setParent(this);
    }

    public UIComponent getParent() {
        return parent;
    }

    public String getRendererType() {
        return renderType;
    }

    public void setParent(final UIComponent parent) {
        AssertionUtil.assertNotNull("parent", parent);
        this.parent = parent;
        inheritProperty();
    }

    public void setRendererType(final String type) {
        AssertionUtil.assertNotNull("rendererType", type);
        this.renderType = type;
    }

    public UIComponent getChild(final int index) {
        return children.get(index);
    }

    public int getChildLength() {
        return children.size();
    }

    public Widget getWidget() {
        return widget;
    }

    public Iterator<UIComponent> iterator() {
        return children.iterator();
    }

    protected void setWidget(final Widget widget) {
        AssertionUtil.assertNotNull("widget", widget);
        this.widget = widget;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(final Renderer renderer) {
        AssertionUtil.assertNotNull("renderer", renderer);
        this.renderer = renderer;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(final String basePath) {
        this.basePath = basePath;
    }

    public void addProperty(final Property property) {
        String name = property.getName();
        if (properties.containsKey(name)) {
            Property existence = properties.get(name);
            existence.setValue(property.getValue());
            existence.setInheritance(property.getInheritance());
        } else {
            properties.put(name, property);
        }
    }

    public Property getProperty(final String name) {
        Property property = properties.get(name);
        if (property != null) {
            Inheritance inheritance = property.getInheritance();
            if (inheritance == NONE || inheritance == CHILD
                    || inheritance == DESCENDANT) {
                return property;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String getPropertyValue(final String name) {
        String value = null;
        Property property = getProperty(name);
        if (property != null) {
            value = property.getValue();
        }
        return value;
    }

    public Collection<Property> getProperties() {
        return properties.values();
    }

    protected void createProperty(String name) {
        addProperty(new PropertyComponent(name));
    }

    protected void createProperty(String name, String value) {
        addProperty(new PropertyComponent(name, value));
    }

    protected void inheritProperty() {
        for (Property property : parent.getProperties()) {
            Inheritance inheritance = property.getInheritance();
            if (inheritance == CHILD) {
                addProperty(property.cloneProperty(NONE));
            } else if (inheritance == CHILD_ONLY) {
                addProperty(property.cloneProperty(NONE));
            } else if (inheritance == DESCENDANT) {
                addProperty(property.cloneProperty(DESCENDANT));
            } else if (inheritance == DESCENDANT_ONLY) {
                addProperty(property.cloneProperty(DESCENDANT));
            }
        }
    }
}
