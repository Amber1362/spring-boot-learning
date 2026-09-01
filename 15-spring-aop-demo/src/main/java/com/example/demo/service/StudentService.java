package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public String createStudent() {
        System.out.println("Student saved successfully");

        return "Student saved";

//        throw new RuntimeException("Some Error Occurred");

//        try {
//            throw new RuntimeException("Some Error Occurred");
//        }
//        catch (RuntimeException e) {
//            return  "Student saved";
//        }
    }
}
