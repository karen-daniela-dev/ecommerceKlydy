package com.ecommerce.klydy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ChatConfig {
    @Value("${GEMINI_API_KEY}")
    private String geminiApiKey;

    public String getGeminiApiKey() {
        return geminiApiKey;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
