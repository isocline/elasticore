package io.elasticore.blueprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import io.elasticore.annotation.*;


@ElastiCore
@EnableCaching
@SpringBootApplication
public class ElCoreBlueprintApp {

    public static void main(String[] args) {
        SpringApplication.run(ElCoreBlueprintApp.class, args);
    }


}
