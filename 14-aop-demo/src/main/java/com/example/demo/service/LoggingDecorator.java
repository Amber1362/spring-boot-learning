package com.example.demo.service;

import com.example.demo.dto.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class LoggingDecorator implements StudentService {

    private StudentServiceImpl studentServiceimpl;

    public LoggingDecorator(StudentServiceImpl studentServiceimpl) {
        this.studentServiceimpl = studentServiceimpl;
    }

    @Override
    public void createStudent(Student student) {

        LoggingServiceUtil.logStart(
                "StudentServiceImpl", "createStudent");

        studentServiceimpl.createStudent(student);

        LoggingServiceUtil.logEnd(
                "StudentServiceImpl", "createStudent");
    }
}
