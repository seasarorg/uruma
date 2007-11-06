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
package org.seasar.uruma.renderer.impl;

import org.eclipse.swt.widgets.ProgressBar;
import org.seasar.uruma.component.jface.ProgressBarComponent;

/**
 * {@link ProgressBar} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class ProgressBarRenderer extends
        AbstractControlRenderer<ProgressBarComponent, ProgressBar> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractControlRenderer#doRenderControl(org.seasar.uruma.component.jface.ControlComponent,
     *      org.eclipse.swt.widgets.Control)
     */
    @Override
    protected void doRenderControl(final ProgressBarComponent controlComponent,
            final ProgressBar control) {
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<ProgressBar> getWidgetType() {
        return ProgressBar.class;
    }
}
