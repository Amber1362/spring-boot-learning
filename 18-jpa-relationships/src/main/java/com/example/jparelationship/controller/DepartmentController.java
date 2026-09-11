package com.example.jparelationship.controller;

import com.example.jparelationship.model.Department;
import com.example.jparelationship.service.DepartmentService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {

        departmentService.createDepartment(department);
        return ResponseEntity.ok("DONE");
    }

    @PostMapping("/studentname")
    public ResponseEntity<String> createDepartment(@RequestBody Department department,
                                                   @RequestParam String studentName) {

        departmentService.createDepartment(department, studentName);
        return ResponseEntity.ok("DONE");
    }
}
