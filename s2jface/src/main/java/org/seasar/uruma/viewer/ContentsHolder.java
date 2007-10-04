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
package org.seasar.uruma.viewer;

/**
 * コンテンツプロバイダ用のコンテンツを保持するクラスです。<br />
 * 
 * @author y-komori
 */
public abstract class ContentsHolder implements ContentsSettable {
    protected Object[] contents;

    /*
     * @see org.seasar.uruma.viewer.ContentsSettable#setContents(java.lang.Object[])
     */
    public void setContents(final Object[] contents) {
        this.contents = new Object[contents.length];
        System.arraycopy(contents, 0, this.contents, 0, contents.length);
    }
}
