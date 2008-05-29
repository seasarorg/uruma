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
package org.seasar.uruma.rcp.binding.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.seasar.uruma.rcp.binding.CommandDesc;
import org.seasar.uruma.rcp.binding.CommandRegistry;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link CommandRegistry} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class CommandRegistryImpl implements CommandRegistry {
    private List<CommandDesc> commandDescList = new ArrayList<CommandDesc>();

    /*
     * @see
     * org.seasar.uruma.rcp.binding.CommandRegistry#registerCommandDesc(org.
     * seasar.uruma.rcp.binding.CommandDesc)
     */
    public void registerCommandDesc(final CommandDesc desc) {
        AssertionUtil.assertNotNull("desc", desc);
        commandDescList.add(desc);
    }

    /*
     * @see org.seasar.uruma.rcp.binding.CommandRegistry#getCommandDescs()
     */
    public List<CommandDesc> getCommandDescs() {
        return Collections.unmodifiableList(commandDescList);
    }

    /*
     * @see
     * org.seasar.uruma.rcp.binding.CommandRegistry#getCommandDesc(java.lang
     * .String)
     */
    public CommandDesc getCommandDesc(final String commandId) {
        for (CommandDesc desc : commandDescList) {
            if (desc.getCommandId().equals(commandId)) {
                return desc;
            }
        }
        return null;
    }
}
