package com.exercise.demo.service;


import com.exercise.demo.externalApi.ExternalApiClient;
import com.exercise.demo.model.*;
import com.exercise.demo.util.ApiMapper;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class StudentService {

    private final ExternalApiClient externalApiClient;

    //Loading groovy script
    private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    @Autowired
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

        try {
            Class<?> groovyClass = groovyClassLoader.parseClass(new File("src/main/java/com/exercise/demo/util/ApiMapper.groovy"));

            GroovyObject groovyObject = (GroovyObject) groovyClass.getDeclaredConstructor().newInstance();

            return (StudentResponse) groovyClass
                    .getMethod("toStudentResponse", ExternalApiResponse.class)
                    .invoke(groovyObject, externalResponse);
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

}