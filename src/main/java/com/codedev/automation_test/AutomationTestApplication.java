package com.codedev.automation_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class AutomationTestApplication {

    /**
     * @return welcome message
     */
    @GetMapping
    public String welcome() {
        return "Welcome to Automation Test";
    }

    public static void main(String[] args) {
        SpringApplication.run(AutomationTestApplication.class, args);
    }

}
