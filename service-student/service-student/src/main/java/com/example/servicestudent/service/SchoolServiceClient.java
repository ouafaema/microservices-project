package com.example.servicestudent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SchoolServiceClient {

    private final WebClient webClient;

    @Autowired
    public SchoolServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build(); // URL du service School
    }

    public String getSchoolById(String schoolId) {
        return webClient.get()
                .uri("/api/schools/{id}", schoolId) // Endpoint du service School
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
