package org.seasar.jface.impl;

import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.template.TemplateBuilder;

public class TemplateWindowTest extends S2FrameworkTestCase {
    public void test() {
        TemplateBuilder builder = new TemplateBuilder();
        TemplateComponent template = builder
                .build("org/seasar/jface/template/TemplateBuilderTest.xml");

        TemplateWindow templateWindow = new TemplateWindow(template);
        templateWindow.setBlockOnOpen(true);
        templateWindow.open();
    }
}
