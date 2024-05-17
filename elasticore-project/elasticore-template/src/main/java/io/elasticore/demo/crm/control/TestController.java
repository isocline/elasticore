package io.elasticore.demo.crm.control;

import io.elasticore.demo.crm.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor

public class TestController {


    private final TestService testService;

    @GetMapping("/case1")
    public String case1() {

        testService.test();

        return "OK";
    }
}

