package com.example.demotest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoTestApplication.class, args);
    }
}
