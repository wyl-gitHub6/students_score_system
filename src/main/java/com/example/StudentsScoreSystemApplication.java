package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 学生成绩系统应用
 *
 * @author Wangyl
 * @date 2022/04/27
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
