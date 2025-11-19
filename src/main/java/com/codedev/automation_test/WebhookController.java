package com.codedev.automation_test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String JENKINS_URL = "http://your-jenkins-server:8080";
    private final String JOB_NAME = "pipeline";
    private final String JENKINS_TOKEN = "your-jenkins-token";
    private final String JENKINS_USER = "your-jenkins-user";
    private final String JENKINS_API_TOKEN = "your-jenkins-api-token";


}

