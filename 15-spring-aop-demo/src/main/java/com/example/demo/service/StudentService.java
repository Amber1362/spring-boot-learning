package com.example.demo.service;

import com.example.demo.dto.Student;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class StudentService {

    public Student createStudent(Student student) {
        System.out.println("Student saved successfully");

        return student;
    }

    @Transient
    public String getStudent() {
        String s = "Get all data";
        System.out.println(s);
        return s;
    }
}
