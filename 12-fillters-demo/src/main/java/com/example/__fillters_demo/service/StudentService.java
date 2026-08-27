package com.example.__fillters_demo.service;

import com.example.__fillters_demo.dto.StudentDto;
import com.example.__fillters_demo.dto.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public StudentResponseDto createStudent(StudentDto student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setName(student.getName());
        studentResponseDto.setMessage("Student is save successfully");

        return studentResponseDto;

//        try {
//            Thread.sleep(3000);
//        } catch (Exception e) {}
    }
}
