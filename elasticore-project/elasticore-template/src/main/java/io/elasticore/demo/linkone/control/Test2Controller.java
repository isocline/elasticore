package io.elasticore.demo.linkone.control;


import io.elasticore.demo.linkone.service.TestService2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/linkone/test")
@Slf4j
@RequiredArgsConstructor

public class Test2Controller {


    private final TestService2 testService2;

    @GetMapping("/case1")
    public String case1() {

        testService2.test();

        return "OK";
    }
}

