package com.example.__fillters_demo.controller;

import com.example.__fillters_demo.dto.StudentDto;
import com.example.__fillters_demo.dto.StudentResponseDto;
import com.example.__fillters_demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentDto student) {
        StudentResponseDto studentResponseDto = studentService.createStudent(student);
        return ResponseEntity.ok(studentResponseDto);
    }
}
