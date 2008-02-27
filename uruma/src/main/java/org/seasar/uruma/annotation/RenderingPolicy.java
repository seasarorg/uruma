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
package org.seasar.uruma.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * コンポーネントから SWT のウィジットに対するレンダリング方法を指定するアノテーションです。<br />
 * 
 * @author y-komori
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RenderingPolicy {
    /**
     * レンダリング対象を指定するための列挙型です。<br />
     * 
     * @author y-komori
     */
    public enum TargetType {
        /**
         * 対象がプロパティであることを示します。<br />
         * セッターを使用して値を設定します。
         */
        PROPERTY,
        /**
         * 対象がフィールドであることを示します。<br />
         */
        FIELD,
        /**
         * 自動設定を行わないことを示します。<br />
         */
        NONE
    }

    /**
     * レンダリング対象属性の型を表す列挙型です。<br />
     * 
     * @author y-komori
     */
    public enum ConversionType {
        /** 対象が {@link String} 型であることを示します。<br /> */
        STRING,
        /** 対象が改行やタブを含む {@link String} 型であることを示します。<br /> */
        TEXT,
        /** 対象が int 型であることを示します。<br /> */
        INT,
        /** 対象が boolean 型であることを示します。<br /> */
        BOOLEAN,
        /** 対象が char 型であることを示します。<br /> */
        CHAR,
        /** 対象が int の配列型であることを示します。転送元は数値のカンマ区切り文字列で表します。<br /> */
        INT_ARRAY,
        /**
         * 対象が {@link org.eclipse.swt.graphics.Color} 型であることを示します。転送元は #RRGGBB
         * 形式の文字列で表します。<br />
         */
        COLOR,
        /** 対象が int 型の {@link org.eclipse.swt.SWT} 定数であることを示します。転送元は定数名の文字列で表します。<br /> */
        SWT_CONST,
        /**
         * 対象が {@link org.eclipse.swt.graphics.Image}
         * 型であることを示します。転送元は画像ファイルへのパス文字列で表します。<br />
         */
        IMAGE,
        /**
         * 対象がアクセラレータであることを示します。 転送元は {@link org.eclipse.jface.action.Action}
         * で使用するアクセラレータ表記文字列で表します。
         */
        ACCELERATOR
    }

    /**
     * 属性を設定するタイミングを表す列挙型です。<br />
     * 
     * @author y-komori
     */
    public enum SetTiming {
        /** コンポーネントのレンダリング時に設定することを示します。<br/> */
        RENDER,
        /** 子コンポーネントのレンダリング後に設定することを示します。<br/> */
        RENDER_AFTER
    }

    /**
     * 対象への設定方式を表します。<br />
     * 
     * @return 対象への設定方式
     */
    public TargetType targetType() default TargetType.PROPERTY;

    /**
     * 対象への変換方式を表します。<br />
     * 
     * @return 対象への変換方式
     */
    public ConversionType conversionType() default ConversionType.STRING;

    /**
     * 対象への設定タイミングを表します。<br />
     * 
     * @return 対象への設定タイミング
     */
    public SetTiming setTiming() default SetTiming.RENDER;
}
