package com.exercise.demo.model;

import lombok.Data;

@Data
public class ExternalApiRequest {
    private String requestId;
    private String serviceCode;
    private String reference;
}
