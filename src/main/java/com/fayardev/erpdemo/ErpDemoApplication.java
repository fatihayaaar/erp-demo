package com.fayardev.erpdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
        info = @Info(title = "ERP-Demo API", version = "1.0", description = "ERP-Demo API v1.0")
)
public class ErpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpDemoApplication.class, args);
    }

}
