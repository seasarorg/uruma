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
package org.seasar.uruma.procedure;

import java.io.IOException;

import org.seasar.uruma.procedure.entity.Procedures;

/**
 * プロシジャに関する定義情報を管理するためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ProcedureManager {
    /**
     * プロシジャ定義を追加します。<br />
     * 
     * @param id
     *            画面 ID
     * @param procedures
     *            プロシジャ定義
     */
    public void addProcedureDef(String id, Procedures procedures);

    /**
     * 指定された画面 ID に対応するプロシジャ定義を削除します。<br />
     * 
     * @param id
     *            画面 ID
     */
    public void removeProcedureDef(String id);

    /**
     * 指定された画面 ID に対応するプロシジャ定義を取得します。<br />
     * 
     * @param id
     *            画面 ID
     */
    public void getProcedureDef(String id);

    /**
     * 指定されたパスからプロシジャ定義ファイルを読み込みます。<br />
     * 読み込んだプロシジャ定義は、 <code>id</code> で指定された画面 ID と関連づけて保持しますので、あとから
     * {@link #getProcedureDef(String)} メソッドによって取得することができます。
     * 
     * @param path
     *            パス(クラスパス上のパスです。ファイルシステムから読み込む場合は、 file:// をつけて指定してください)
     * @return 読み込んだプロシジャ定義
     */
    public Procedures loadProcedureDef(String id, String path);

    /**
     * プロシジャ定義ファイルを書き出します。<br />
     * 
     * @param path
     *            書き出し先パス
     * @throws IOException
     *             ファイルの書き出しに失敗した場合
     */
    public void writeProcedureDef(Procedures procedures, String path)
            throws IOException;
}
