package io.elasticore.demo.crm.controller;

import io.elasticore.demo.crm.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {


    private final TestService testService;

    @GetMapping("/case1")
    public String test() {

        testService.test();
        return "ok";

    }
}
