package com.example.demo.service;

import com.example.demo.annotation.TrackExecutionTime;
import com.example.demo.dto.Student;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class StudentService {

    @TrackExecutionTime(
            warnAfter = 1500,
            operation = "Get Student Data"
    )
    public Student createStudent(Student student) {

        try {
            Thread.sleep(2000);
        }
        catch (Exception e) {}

        System.out.println("Student saved successfully");

        return student;
    }

    public String getStudent() {
        String s = "Get all data";
        System.out.println(s);
        return s;
    }
}
