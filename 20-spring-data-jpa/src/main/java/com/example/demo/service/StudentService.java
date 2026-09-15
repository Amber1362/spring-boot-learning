package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public Student fetchStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        return studentOptional.get();
    }

    public List<Student> fetchAll(String name) {

        Sort sort = Sort.by("age").descending();

        Pageable pageable = PageRequest.of(0, 3);

        Page<Student> studentList = studentRepository.findAll(pageable);

        for(Student s : studentList.getContent()) {
            System.out.println(s);
        }

        return studentList.getContent();
    }
}
