package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author hasee
 */
@SpringBootApplication
public class StudentsScoreSystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StudentsScoreSystemApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StudentsScoreSystemApplication.class);
    }
}
