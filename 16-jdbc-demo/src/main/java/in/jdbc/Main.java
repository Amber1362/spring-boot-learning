package in.jdbc;

import in.jdbc.model.Student;
import in.jdbc.repository.StudentRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        StudentRepository studentRepository = new StudentRepository();
        studentRepository.createStudent(new Student("Ayush", "ayush@gmail.com", 20));

//        studentRepository.updateStudent();

//        studentRepository.deleteStudent();

//        studentRepository.getStudentById();
    }
}
