package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

//    void save(Student student);
//
//    Optional<Student> findById(Long id);
}
