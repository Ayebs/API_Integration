package com.exercise.demo.controller;

import com.exercise.demo.model.StudentRequest;
import com.exercise.demo.model.StudentResponse;
import com.exercise.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/details")
    public ResponseEntity<StudentResponse> getStudentDetails(@RequestBody StudentRequest request) {
        StudentResponse response = studentService.getStudentDetails(request);
        return ResponseEntity.ok(response);
    }

}
