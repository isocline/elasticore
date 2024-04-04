package io.elasticore.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import io.elasticore.annotation.*;


@ElastiCore
@EnableCaching
@SpringBootApplication
public class MainTestApp {

    public static void main(String[] args) {
        SpringApplication.run(MainTestApp.class, args);
    }


}
