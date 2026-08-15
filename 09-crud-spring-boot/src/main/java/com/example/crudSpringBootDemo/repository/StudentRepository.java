package com.example.crudSpringBootDemo.repository;

import com.example.crudSpringBootDemo.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {

    public Student saveStudent(Student studentReq) {
        System.out.println("Inside student repository");
        System.out.println("Exiting Student repository");

        Student s1 = new Student();
        s1.setName("Ayush");
        s1.setAge((22));
        s1.setEmail("ayush@gmail.com");
        s1.setId(2);
        s1.setRollNo(102);
        s1.setSubject("Java core");
        return s1;
    }
}
