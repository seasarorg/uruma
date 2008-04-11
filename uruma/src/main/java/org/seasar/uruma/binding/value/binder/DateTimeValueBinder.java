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
package org.seasar.uruma.binding.value.binder;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.DateTime;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.component.UIComponent;

/**
 * {@link DateTime} のための {@link ValueBinder} です。<br />
 * 
 * @author shhirose
 */
public class DateTimeValueBinder extends AbstractValueBinder<DateTime> {

    /**
     * {@link DateTimeValueBinder} を構築します。<br />
     */
    public DateTimeValueBinder() {
        super(DateTime.class);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doImportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    protected void doImportValue(final DateTime widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {

        Date date = null;
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        DecimalFormat dFormat = new DecimalFormat("00");
        try {
            date = sdFormat.parse(widget.getYear()
                    + dFormat.format(widget.getMonth() + 1)
                    + dFormat.format(widget.getDay())
                    + dFormat.format(widget.getHours())
                    + dFormat.format(widget.getMinutes())
                    + dFormat.format(widget.getSeconds()));
        } catch (ParseException ex) {
            date = new Date();
        }

        logBinding(IMPORT_VALUE, formObj, propDesc, widget, propDesc, date);
        propDesc.setValue(formObj, date);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doExportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    protected void doExportValue(final DateTime widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {
        Object value = propDesc.getValue(formObj);
        if (value == null || !(value instanceof Date)) {
            value = new Date();
        }

        logBinding(EXPORT_VALUE, formObj, propDesc, widget, propDesc, value);

        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) value);
        widget.setYear(cal.get(Calendar.YEAR));
        widget.setMonth(cal.get(Calendar.MONTH));
        widget.setDay(cal.get(Calendar.DATE));
        widget.setHours(cal.get(Calendar.HOUR_OF_DAY));
        widget.setMinutes(cal.get(Calendar.MINUTE));
        widget.setSeconds(cal.get(Calendar.SECOND));
    }
}
