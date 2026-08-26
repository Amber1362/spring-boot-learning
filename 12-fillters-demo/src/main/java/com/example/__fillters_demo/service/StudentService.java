package com.example.__fillters_demo.service;

import com.example.__fillters_demo.dto.StudentDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void createStudent(StudentDto student) {
        System.out.println("Student created");
        System.out.println(student.getName());
        System.out.println(student.getEmail());

        try {
            Thread.sleep(3000);
        } catch (Exception e) {}
    }
}
