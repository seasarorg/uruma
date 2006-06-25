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
package org.seasar.jface.exception;

/**
 * @author y-komori
 * 
 */
public class NotFoundException extends S2JFaceRuntimeException {

    private static final long serialVersionUID = -8913237517246706901L;

    public static final String LAYOUT = "EJFC0101";

    public static final String LAYOUT_DATA = "EJFC0102";

    public static final String RENDERER = "EJFC0103";

    public static final String SWT_EVENT_LISTENER = "EJFC0104";

    public static final String WIDGET = "EJFC0105";

    public static final String WINDOW = "EJFC0300";

    public static final String EXTEND_TARGET_COMPONENT = "EJFC0107";

    public static final String EXTEND_TARGET_PROPERTY = "EJFC0108";

    public static final String UICOMPONENT = "EJFC0109";

    public NotFoundException(String code, String name) {
        super(code, name);
    }
}
