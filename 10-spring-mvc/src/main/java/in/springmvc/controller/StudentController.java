package in.springmvc.controller;

import in.springmvc.entity.Student;
import in.springmvc.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student studentReq) {
        Student studentRes = studentService.createStudent(studentReq);

        return ResponseEntity.ok(studentReq);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        Student studentRes = studentService.getStudent(id);

        if(studentRes == null) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentRes);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentRes = studentService.getAllStudent();

        if(studentRes == null) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentRes);
    }
}
