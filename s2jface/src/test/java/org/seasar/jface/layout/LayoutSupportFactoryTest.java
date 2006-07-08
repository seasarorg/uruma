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
package org.seasar.jface.layout;

import junit.framework.TestCase;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;

/**
 * @author y-komori
 * 
 */
public class LayoutSupportFactoryTest extends TestCase {
    public void testGetLayoutSupport() {
        assertTrue(
                "1",
                LayoutSupportFactory.getLayoutSupport("fill") instanceof FillLayoutSupport);
        assertTrue(
                "2",
                LayoutSupportFactory.getLayoutSupport("row") instanceof RowLayoutSupport);
        assertTrue(
                "3",
                LayoutSupportFactory.getLayoutSupport("grid") instanceof GridLayoutSupport);

        assertTrue(
                "4",
                LayoutSupportFactory.getLayoutSupport(FillLayout.class) instanceof FillLayoutSupport);
        assertTrue(
                "5",
                LayoutSupportFactory.getLayoutSupport(RowLayout.class) instanceof RowLayoutSupport);
        assertTrue(
                "6",
                LayoutSupportFactory.getLayoutSupport(GridLayout.class) instanceof GridLayoutSupport);
    }
}
