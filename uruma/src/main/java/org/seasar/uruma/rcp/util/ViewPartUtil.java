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
package org.seasar.uruma.rcp.util;

import org.eclipse.ui.IViewPart;
import org.seasar.uruma.core.UrumaConstants;

/**
 * {@link IViewPart} のためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class ViewPartUtil implements UrumaConstants {
    private ViewPartUtil() {

    }

    /**
     * 指定された <code>id</code> からプライマリ ID を取り出します。<br />
     * プライマリ ID とは、<code>:</code> よりも手前の部分です。<br />
     * 
     * @param id
     *            ID
     * @return プライマリID
     */
    public static String getPrimaryId(final String id) {
        if (id != null) {
            int pos = id.lastIndexOf(COLON);
            if (pos > -1) {
                return id.substring(0, pos);
            }
        }
        return id;
    }

    /**
     * プライマリ ID とセカンダリ ID を結合して返します。<br />
     * 
     * @param primaryId
     *            プライマリ ID
     * @param secondaryId
     *            セカンダリ ID
     * @return 結合した ID
     */
    public static String createFullId(final String primaryId,
            final String secondaryId) {
        if (secondaryId != null) {
            return primaryId + COLON + secondaryId;
        } else {
            return primaryId;
        }
    }
}
