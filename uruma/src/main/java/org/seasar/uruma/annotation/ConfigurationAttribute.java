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
package org.seasar.uruma.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.uruma.rcp.configuration.writer.GenericConfigurationWriter;

/**
 * {@link GenericConfigurationWriter} が属性として出力するフィールドを表すアノテーションです。<br />
 * 
 * @author y-komori
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
public @interface ConfigurationAttribute {
    /**
     * @return 属性名称 (省略時はフィールド名称がそのまま利用されます)
     */
    public String name() default "";

    /**
     * @return <code>true</code> の場合、必須属性であることを示します。
     */
    public boolean required() default false;
}
