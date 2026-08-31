package com.example.demo.controller;

import com.example.demo.dto.Student;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    public StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(Student student) {
         studentService.createStudent(student);

         return ResponseEntity.ok("DONE");
    }
}
