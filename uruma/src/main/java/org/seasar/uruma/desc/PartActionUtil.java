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
package org.seasar.uruma.desc;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.S2ContainerUtil;

/**
 * パートアクションクラスに関するユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class PartActionUtil {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(PartActionUtil.class);

    private PartActionUtil() {
    }

    /**
     * パートアクションクラスを準備します。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     * @param id
     *            対応するパートの ID
     * @param container
     *            クラスを検索する {@link S2Container} オブジェクト
     * @return パートアクションクラスが見つかった場合、そのオブジェクト。<br />
     *         見つからなかった場合は <code>null</code>
     */
    public static Object setupPartAction(final PartContext context,
            final String id, final S2Container container) {
        String actionComponentName = StringUtil.decapitalize(id)
                + UrumaConstants.PART_ACTION_SUFFIX;

        Object partActionComponent = S2ContainerUtil.getComponentNoException(
                actionComponentName, container);
        if (partActionComponent != null) {
            context.setPartActionObject(partActionComponent);
            PartActionDesc desc = PartActionDescFactory
                    .getPartActionDesc(partActionComponent.getClass());
            context.setPartActionDesc(desc);

            logger.log(UrumaMessageCodes.PART_ACTION_CLASS_FOUND, id,
                    actionComponentName, partActionComponent.getClass()
                            .getName());

            return partActionComponent;
        } else {
            return null;
        }
    }
}
