package com.example.jparelationship.controller;

import com.example.jparelationship.model.Student;
import com.example.jparelationship.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/{deptId}")
    public ResponseEntity<String> createStudent(@RequestBody Student student, @PathVariable Long deptId) {

        studentService.createStudent(student, deptId);
        return ResponseEntity.ok("DONE");
    }

    @PostMapping("/departmentname")
    public ResponseEntity<String> createStudent(@RequestBody Student student, @RequestParam String deptName) {

        studentService.createStudent(student, deptName);
        return ResponseEntity.ok("DONE");
    }
}
