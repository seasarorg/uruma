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
package org.seasar.uruma.renderer.impl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.seasar.uruma.component.jface.DateTimeComponent;

/**
 * {@link DateTime} のレンダリングを行うクラスです。<br />
 * 
 * @author yazaki
 */
public class DateTimeRenderer extends
        AbstractControlRenderer<DateTimeComponent, DateTime> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractControlRenderer#doRenderControl(org.seasar.uruma.component.jface.ControlComponent,
     *      org.eclipse.swt.widgets.Control)
     */
    @Override
    protected void doRenderControl(final DateTimeComponent controlComponent,
            final DateTime control) {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<DateTime> getWidgetType() {
        return DateTime.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#getDefaultStyle()
     */
    @Override
    protected int getDefaultStyle() {
        return SWT.CALENDAR | SWT.MEDIUM;
    }

}
