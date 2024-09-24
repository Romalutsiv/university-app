package org.university.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${service.urls.course}")
    private String COURSE_URL;
    @Bean
    public WebClient courseWebClient(){
        return WebClient.builder()
                .baseUrl(COURSE_URL)
                .build();
    }
}
