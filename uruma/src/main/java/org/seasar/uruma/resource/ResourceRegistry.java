/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
package org.seasar.uruma.resource;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * 各種リソースを管理するためのインターフェースです。<br />
 * Uruma が管理するリソースは、本インターフェースを経由して取得してください。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public interface ResourceRegistry {
    /**
     * キーに対応したイメージを取得します。<br />
     * キーに対応したイメージが登録されていない場合、未登録イメージ(赤い矩形)を返します。 本メソッドは、
     * {@link #getImage(String, URL)} メソッドで {@code parentUrl} に {@code null}
     * を指定した場合と同じです。
     * 
     * @param key
     *        キー
     * @return イメージ
     */
    public Image getImage(String key);

    /**
     * 親 URL とパスを指定してイメージを取得します。<br />
     * 親 URL に {@code null} を指定した場合、まずパスをキーと見なして登録されたイメージを検索し、見つからなければ親 URL
     * とパスからイメージを検索します。<br />
     * 
     * @param path
     *        パス
     * @param parentUrl
     *        親 URL
     * @return イメージ
     */
    public Image getImage(String path, URL parentUrl);

    /**
     * キーに対応した {@link ImageDescriptor} を取得します。<br />
     * キーに対応した {@link ImageDescriptor} が登録されていない場合、未登録イメージ(赤い矩形)の
     * {@link ImageDescriptor} を返します。<br />
     * 
     * @param key
     *        キー
     * @return {@link ImageDescriptor}
     */
    public ImageDescriptor getImageDescriptor(String key);

    /**
     * 親 URL とパスを指定して {@link ImageDescriptor} を取得します。<br />
     * 親 URL に {@code null} を指定した場合、まずパスをキーと見なして登録された {@link ImageDescriptor}
     * を検索し、見つからなければ親 URL とパスから {@link ImageDescriptor} を検索します。<br />
     * 
     * @param path
     *        パス
     * @param parentUrl
     *        親 URL
     * @return イメージ
     */
    public ImageDescriptor getImageDescriptor(String path, URL parentUrl);

    /**
     * キーに対応した {@link ImageDescriptor} を登録します。<br />
     * 
     * @param key
     *        キー
     * @param descriptor
     *        {@link ImageDescriptor} オブジェクト
     */
    public void putImage(String key, ImageDescriptor descriptor);
}
