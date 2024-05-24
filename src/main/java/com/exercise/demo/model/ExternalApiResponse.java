package com.exercise.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalApiResponse {
    private boolean status;
    private ResponseData data;
    private String message;

    @Data
    public static class ResponseData {
        private String billReference;
        private String customerName;
        private String customerSegment;
        private Details details;

        @Data
        public static class Details {
            @JsonProperty("Gender")
            private String gender;
        }
    }
}
