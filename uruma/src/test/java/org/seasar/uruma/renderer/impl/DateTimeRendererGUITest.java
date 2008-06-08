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

import java.util.Calendar;
import java.util.Date;

import junit.framework.AssertionFailedError;

import org.eclipse.swt.widgets.DateTime;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.InitializeMethod;

/**
 * {@link DateTimeRenderer} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class DateTimeRendererGUITest extends AbstractGUITest {
    @ImportExportValue(id = "time")
    public Date timeValue;

    @ImportExportValue(id = "date")
    public Date dateValue;

    public DateTime date;

    public DateTime time;

    @InitializeMethod
    public void initialize() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1977);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 12);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 15);

        dateValue = calendar.getTime();
        timeValue = calendar.getTime();
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractGUITest#okButtonAction()
     */
    @Override
    @EventListener(id = "okButton")
    public void okButtonAction() {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(dateValue);
            assertEquals("1", date.getYear(), calendar.get(Calendar.YEAR));
            assertEquals("2", date.getMonth(), calendar.get(Calendar.MONTH));
            assertEquals("3", date.getDay(), calendar.get(Calendar.DATE));

            calendar.setTime(timeValue);
            assertEquals("4", time.getHours(), calendar
                    .get(Calendar.HOUR_OF_DAY));
            assertEquals("5", time.getMinutes(), calendar.get(Calendar.MINUTE));
            assertEquals("6", time.getSeconds(), calendar.get(Calendar.SECOND));

            super.okButtonAction();
        } catch (AssertionFailedError error) {
            shell.close();
            result = false;
            throw error;
        }
    }
}
