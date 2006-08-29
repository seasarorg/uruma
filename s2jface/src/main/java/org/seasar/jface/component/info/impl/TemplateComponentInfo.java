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
package org.seasar.jface.component.info.impl;

import org.seasar.jface.annotation.xml.Attribute;
import org.seasar.jface.annotation.xml.ComponentClass;
import org.seasar.jface.annotation.xml.ComponentProperties;
import org.seasar.jface.annotation.xml.ElementName;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.info.ComponentInfo;

/**
 * template 要素のコンポーネント情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
@ComponentClass(TemplateComponent.class)
@ElementName("template")
@ComponentProperties( { @Attribute("name"), @Attribute("extends") })
public class TemplateComponentInfo implements ComponentInfo {
}
