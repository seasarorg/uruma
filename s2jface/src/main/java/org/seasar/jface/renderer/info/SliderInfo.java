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
package org.seasar.jface.renderer.info;

import org.seasar.jface.component.Inheritance;

/**
 * @author dkameya
 */
public class SliderInfo extends ControlInfo {
    public static final String MIN_PROP = "min";

    public static final Inheritance min_INHERITANCE = Inheritance.NONE;

    public static final String MAX_PROP = "max";

    public static final Inheritance max_INHERITANCE = Inheritance.NONE;
    
    public static final String SELECTION_PROP = "selection";

    public static final Inheritance selection_INHERITANCE = Inheritance.NONE;

    public static final String INCREMENT_PROP = "increment";

    public static final Inheritance increment_INHERITANCE = Inheritance.NONE;
}
