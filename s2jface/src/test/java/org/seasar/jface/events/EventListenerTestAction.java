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
package org.seasar.jface.events;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.jface.annotation.EventListener;

@Component(name = "eventListenerTestAction")
public class EventListenerTestAction {
    @EventListener(id = "go")
    public void go() {
        System.out.println("Go!");
    }

    @EventListener(id = "stop")
    public void stop() {
        System.out.println("Stop!");
    }
}
