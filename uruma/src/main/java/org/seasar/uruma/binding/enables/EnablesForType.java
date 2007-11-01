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
package org.seasar.uruma.binding.enables;

/**
 * {@link EnablesDependingDef} のための選択状態を表す列挙型です。<br />
 * 
 * @author bskuroneko
 */
public enum EnablesForType {

    /** 選択が１つでも存在する場合に true */
    SELECTION,

    /** 選択が１つの場合にのみ true */
    SINGLE,

    /** 選択が２つの場合にのみ true */
    PAIR,

    /** 選択が１つもされていない場合に true */
    NONE,

    /** 選択が２つ以上の場合にのみ true */
    MULTI
}
