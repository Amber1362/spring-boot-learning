package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Profile;
import com.example.demo.model.Student;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private DepartmentRepository departmentRepository;
    private ProfileRepository profileRepository;

    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository, ProfileRepository profileRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public void createStudent(Student student) {

        Department department = new Department();
        department.setName("CSE");

        Profile profile = new Profile();
        profile.setBio("Charpata Gang");

        student.setDepartment(department);
        student.setProfile(profile);

        departmentRepository.save(department);
        profileRepository.save(profile);
        studentRepository.save(student);
    }

    public Student fetchStudentById(Long id) {
        return studentRepository.findById(id);
    }
}
