package com.exercise.demo.model;

import lombok.Data;

@Data
public class StudentResponse {
    private boolean success;
    private String message;
    private StudentDetails studentDetails;

   @Data
    public static class StudentDetails {
        private String studentId;
        private String studentName;
        private String gender;
        private String className;
    }
}
