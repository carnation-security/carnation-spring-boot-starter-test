package com.violetfreesia.carnation.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author violetfreesia
 * @date 2021-06-18
 */
@SpringBootApplication
public class CarnationSpringBootStarterTestApplication {
    public static void main(String[] args) {
        SpringApplication springApplication =
                new SpringApplication(CarnationSpringBootStarterTestApplication.class);
        springApplication.run(args);
    }
}
