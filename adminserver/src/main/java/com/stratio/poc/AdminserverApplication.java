package com.stratio.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.config.EnableAdminServer;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@SpringBootApplication
public class AdminserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminserverApplication.class, args);
    }
}
