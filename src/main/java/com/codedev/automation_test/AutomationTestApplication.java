package com.codedev.automation_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Automation Jenkins Test
 */
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

    /**
     * The entry point of the Automation Test Spring Boot application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(AutomationTestApplication.class, args);
    }

}
