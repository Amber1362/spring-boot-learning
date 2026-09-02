package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

//    @Before("execution(String com.example.demo.service.StudentService.createStudent())")
//    public void logBeforeMethod(JoinPoint joinPoint) {
//        System.out.println("Student is going to be saved");
//
//        Object[] arr = joinPoint.getArgs();
////        boolean allowed = false;
////
////        if(!allowed) {
////            throw new RuntimeException("Method Execution is not allowed");
////        }
//    }

//    @AfterReturning(
//            value = "execution(String com.example.demo.service.StudentService.createStudent())",
//            returning = "result")
//    public void logAfterReturningMethod(String result) {
////        System.out.println("LogAfterReturningMethod called");
//
//        String s = "Target Method returned: " + result;
//
//        System.out.println(s);
//    }

//    @AfterThrowing(
//            value = "execution(String com.example.demo.service.StudentService.createStudent())",
//    throwing = "exception")
//    public void logAfterThrowingMethod(Throwable exception) {
//        System.out.println("Exception type: " + exception.getClass().getName());
//        System.out.println("Exception message: " + exception.getMessage());
//    }
//
//    @After(
//            value = "execution(String com.example.demo.service.StudentService.createStudent())")
//    public void logAfterMethod() {
//        System.out.println("LogAfterMethod executed");
//    }

//    @Around(
//            value = "execution(String com.example.demo.service.StudentService.createStudent())")
//    public String logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("Starting: " + joinPoint.getSignature().getName());
//
//        try {
//            String student = (String) joinPoint.proceed();
//            System.out.println("Execution successful");
//            return student;
//        } catch (Exception e) {
//            System.out.println("Execution failed: " + e.getMessage());
//            throw e;
//        }
//        finally {
//            System.out.println("Execution completed");
//        }
//    }

    @Before("com.example.demo.aspect.ApplicationPointcut.publicServiceMethod()")
    public void logBeforeMethod() {
        System.out.println("Method Intercepted");
    }

//    @Before("execution(com.example.demo.dto.Student " +
//            "com.example.demo.service.StudentService.createStudent(" +
//            "com.example.demo.dto.Student))")
//    public void logBeforeMethod2() {
//        System.out.println("Method Intercepted");
//    }
}
