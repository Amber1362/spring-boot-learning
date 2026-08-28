package com.example.interceptiondemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @PostMapping
    public ResponseEntity<String> createStudent() {

        System.out.println("Student Controller called");

        return ResponseEntity.ok("Student Created!");
    }
}
