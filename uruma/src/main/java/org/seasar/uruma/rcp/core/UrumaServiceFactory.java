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
package org.seasar.uruma.rcp.core;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.seasar.uruma.rcp.UrumaService;

/**
 * {@link UrumaService} のための {@link ServiceFactory} です。
 * 
 * @author y-komori
 */
public class UrumaServiceFactory implements ServiceFactory {
    protected static final Map<String, UrumaService> serviceMap = new HashMap<String, UrumaService>();

    UrumaServiceFactory() {

    }

    /*
     * @see org.osgi.framework.ServiceFactory#getService(org.osgi.framework.Bundle,
     *      org.osgi.framework.ServiceRegistration)
     */
    public Object getService(final Bundle bundle,
            final ServiceRegistration registration) {
        String symbolicName = bundle.getSymbolicName();
        UrumaService service = serviceMap.get(symbolicName);
        if (service == null) {
            service = new UrumaServiceImpl(bundle);
            serviceMap.put(symbolicName, service);
        }
        return service;
    }

    /*
     * @see org.osgi.framework.ServiceFactory#ungetService(org.osgi.framework.Bundle,
     *      org.osgi.framework.ServiceRegistration, java.lang.Object)
     */
    public void ungetService(final Bundle bundle,
            final ServiceRegistration registration, final Object service) {
        String symbolicName = bundle.getSymbolicName();
        UrumaServiceImpl urumaService = (UrumaServiceImpl) serviceMap
                .get(symbolicName);
        if (urumaService != null) {
            urumaService.destroy();
            serviceMap.remove(symbolicName);
        }
    }
}
