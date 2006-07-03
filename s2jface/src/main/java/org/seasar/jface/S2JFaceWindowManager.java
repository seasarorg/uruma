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
package org.seasar.jface;

/**
 * ウィンドウを管理するクラスのためのインターフェースです。<br>
 * 
 * @author y-komori
 */
public interface S2JFaceWindowManager {
    /**
     * 指定されたパスの画面定義XMLを読み込み、ウィンドウを開きます。</br>
     * 
     * @param templatePath
     *            画面定義XMLのパス
     * @param blockOnOpen
     *            <code>true</code>の場合、画面が閉じるまでスレッドをブロックします。
     */
    public void open(String templatePath, boolean blockOnOpen);

    /**
     * 指定されたパスの画面定義XMLを読み込み、ウィンドウを開きます。</br>
     * <p>
     * ウィンドウオープン時、スレッドのブロックは行いません。(<code>open(templatePath, false)</code>
     * を実行したのと同じ動作をします)
     * </p>
     * 
     * @param templatePath
     *            画面定義XMLのパス
     * @throws org.seasar.jface.exception.NotFoundException
     *             指定された画面名称を持つウィンドウが登録されていなかった場合。
     */
    public void open(String templatePath);
}
