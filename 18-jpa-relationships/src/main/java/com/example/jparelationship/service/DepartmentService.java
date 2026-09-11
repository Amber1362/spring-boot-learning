package com.example.jparelationship.service;

import com.example.jparelationship.model.Department;
import com.example.jparelationship.model.Student;
import com.example.jparelationship.repository.DepartmentRepository;
import com.example.jparelationship.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private StudentRepository studentRepository;

    public DepartmentService(DepartmentRepository departmentRepository, StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public void createDepartment(Department department, String studentName) {

        System.out.println("Department name: " + department.getName());
        System.out.println("Students list: " + department.getStudents());

        Student student = new Student();
        student.setName(studentName);
        student.setDepartment(department);

        department.getStudents().add(student);

        departmentRepository.save(department);
        studentRepository.save(student);
    }
}
