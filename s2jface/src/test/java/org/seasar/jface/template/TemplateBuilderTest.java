package org.seasar.jface.template;


import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;

public class TemplateBuilderTest extends S2FrameworkTestCase {
    protected TemplateBuilder builder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        builder = new TemplateBuilder();
    }

    public void testBuild() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath("TemplateBuilderTest.xml"));
        
        assertNotNull(template);
        
        WindowComponent window = (WindowComponent)template.getChild(0);
        assertNotNull(window);
        
        System.out.println(template.getName());
    }

}
