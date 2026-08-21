package com.example.crudSpringBootDemo.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {

    @NotBlank(message = "Name cannot be null, empty or blank")
    @Size(min = 3, max= 30, message = "Student name must be within 3 to 30 character long")
    private String name;

    @NotBlank(message = "Student email cannot be blank")
    @Email(message = "Student email be valid")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 15, message = "Student must be at least 15 years old")
    private Integer age;

    @NotNull(message = "Roll No is required")
    private Integer rollNo;

    @NotBlank(message = "Subject is required")
    private String subject;

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

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
