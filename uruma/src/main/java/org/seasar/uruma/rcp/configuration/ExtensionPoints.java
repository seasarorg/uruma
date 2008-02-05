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
package org.seasar.uruma.rcp.configuration;

/**
 * エクステンション・ポイントの ID を定数として提供するインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ExtensionPoints {
    /**
     * エクステンションポイント：{@value} を表す定数です。<br />
     */
    public static final String APPLICATIONS = "org.eclipse.core.runtime.applications";

    /**
     * エクステンションポイント：{@value} を表す定数です。<br />
     */
    public static final String VIEWS = "org.eclipse.ui.views";

    /**
     * エクステンションポイント：{@value} を表す定数です。<br />
     */
    public static final String PERSPECTIVES = "org.eclipse.ui.perspectives";

    /**
     * エクステンションポイント：{@value} を表す定数です。<br />
     */
    public static final String ACTIONSETS = "org.eclipse.ui.actionSets";
}
