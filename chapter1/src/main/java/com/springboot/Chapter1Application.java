package com.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Chapter1Application {

    @Value("${com.springboot.name}")
    private String name;
    @Value("${com.springboot.want}")
    private String want;

    @RequestMapping("/")
    public String index() {
        return name +","+ want +"and Hello Spring Boot!";
    }
    public static void main(String[] args) {
        SpringApplication.run(Chapter1Application.class, args);
    }

}
