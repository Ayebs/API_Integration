package com.exercise.demo.service;


import com.exercise.demo.externalApi.ExternalApiClient;
import com.exercise.demo.model.*;
import com.exercise.demo.util.ApiMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

    private final ExternalApiClient externalApiClient;

    public StudentService(ExternalApiClient externalApiClient) {
        this.externalApiClient = externalApiClient;
    }

    public StudentResponse getStudentDetails(StudentRequest request) {
        String requestId = UUID.randomUUID().toString();

        ExternalApiRequest externalRequest = new ExternalApiRequest();
        externalRequest.setRequestId(requestId);
        externalRequest.setServiceCode(request.getSchoolCode());
        externalRequest.setReference(request.getStudentId());

        ExternalApiResponse externalResponse = externalApiClient.getStudentDetails(externalRequest);

//        System.out.println(request);
        System.out.println("Api Mapper" + ApiMapper.toStudentResponse(externalResponse));
        return ApiMapper.toStudentResponse(externalResponse);
    }
}