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
package org.seasar.jface.renderer;

import java.util.HashMap;
import java.util.Map;

import org.seasar.jface.exception.NotFoundException;

/**
 * @author y-komori
 * 
 */
public class RendererFactrory {
    protected static final Map<String, Renderer> rendererMap = new HashMap<String, Renderer>();

    static {
        rendererMap.put("button", new ButtonRenderer());
        rendererMap.put("label", new LabelRenderer());
        rendererMap.put("text", new TextRenderer());
        rendererMap.put("textArea", new TextAreaRenderer());

        rendererMap.put("box", new BoxRenderer());

        rendererMap.put("window", new WindowRenderer());
    }

    public static Renderer getRenderer(final String type) {
        Renderer renderer = rendererMap.get(type);
        if (renderer != null) {
            return renderer;
        } else {
            throw new NotFoundException(NotFoundException.RENDERER, type);
        }
    }
}
