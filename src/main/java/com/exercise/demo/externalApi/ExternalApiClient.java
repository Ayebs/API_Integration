package com.exercise.demo.externalApi;

import com.exercise.demo.model.ExternalApiResponse;
import com.exercise.demo.model.ExternalApiRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ExternalApiClient {

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Value("${external.api.auth}")
    private String externalApiAuth;

    private final RestTemplate restTemplate;

    public ExternalApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        System.out.println("ExternalApiClient initialized with URL: " + externalApiUrl);
    }

   public ExternalApiResponse getStudentDetails(ExternalApiRequest request) {

       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization", externalApiAuth);
       headers.setContentType(MediaType.APPLICATION_JSON);

       HttpEntity<ExternalApiRequest> entity = new HttpEntity<>(request, headers);

       return restTemplate.exchange(externalApiUrl, HttpMethod.POST, entity, ExternalApiResponse.class).getBody();

    }
}
