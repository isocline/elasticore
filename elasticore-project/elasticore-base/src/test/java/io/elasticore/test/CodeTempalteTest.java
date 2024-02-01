package io.elasticore.test;

import io.elasticore.base.util.CodeTemplate;
import org.junit.jupiter.api.Test;

public class CodeTempalteTest {

    @Test
    public void test() {


        CodeTemplate template = CodeTemplate.newInstance()
                .line(" test")
                .line("package ${packages};", true)
                .line()
                .line("class ${className} ex ${className} {")
                .line("}")
                .line("// test");


        CodeTemplate.Parameters p = CodeTemplate.newParameters()
                .set("packages", "test")
                .set("className", "CX");


        String result = template.toString(p);

        System.err.println(result);


        CodeTemplate.Value v = CodeTemplate.newValue();
        v.add("pgk1").add("pkg2");

        p = CodeTemplate.newParameters()
                .set("packages",v)
                .set("className", "CX3");

        System.err.println(template.toString(p));


    }
}
