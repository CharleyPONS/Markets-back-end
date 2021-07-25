package com.info.markets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarketsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketsApplication.class, args);
    }

}
