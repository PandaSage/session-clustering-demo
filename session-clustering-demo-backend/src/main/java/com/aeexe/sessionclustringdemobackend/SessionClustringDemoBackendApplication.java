package com.aeexe.sessionclustringdemobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.aeexe.sessionclustringdemobackend.domain")
@SpringBootApplication
public class SessionClustringDemoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionClustringDemoBackendApplication.class, args);
    }
}
