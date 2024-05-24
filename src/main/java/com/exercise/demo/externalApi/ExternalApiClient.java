package com.exercise.demo.externalApi;

import com.exercise.demo.model.ExternalApiResponse;
import com.exercise.demo.model.ExternalApiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//import java.net.http.HttpHeaders;

@Component
public class ExternalApiClient {

//    @Value("${external.api.url}")
//    private String externalApiUrl;
    String exUrl = "https://business.mykowri.com/billing/lookup";

    private final RestTemplate restTemplate;

    public ExternalApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

   public ExternalApiResponse getStudentDetails(ExternalApiRequest request) {

       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization", "Basic dXNlcjp0MzV0cEA1NQ==");
       headers.setContentType(MediaType.APPLICATION_JSON);

       HttpEntity<ExternalApiRequest> entity = new HttpEntity<>(request, headers);

       return restTemplate.exchange(exUrl, HttpMethod.POST, entity, ExternalApiResponse.class).getBody();

    }
}
