package com.example.hibernateDemo.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true,
            nullable = false,
            length = 100)
    private String email;

    private int age;

//    @Column(precision = 5, scale = 2)
//    private BigDecimal percentage;
//
//    private LocalDate dateOfBirth;
//
//    private LocalDateTime createdAt;
//
//    @Enumerated(EnumType.STRING)
//    private StudentStatus status;

    public Student() {}

    public Student(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
//        this.percentage = percentage;
//        this.dateOfBirth = dateOfBirth;
//        this.createdAt = createdAt;
//        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public BigDecimal getPercentage() {
//        return percentage;
//    }
//
//    public void setPercentage(BigDecimal percentage) {
//        this.percentage = percentage;
//    }
//
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public StudentStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(StudentStatus status) {
//        this.status = status;
//    }
}
