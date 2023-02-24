package com.testproject.todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ToDoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToDoApiApplication.class, args);
    }

}
