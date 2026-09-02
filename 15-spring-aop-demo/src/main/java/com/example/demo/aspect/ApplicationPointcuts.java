package com.example.demo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ApplicationPointcuts {

    @Pointcut("within(com.example.demo.controller..*)")
    public void controllerLayer() {
        //empty body
    }

    @Pointcut("within(com.example.demo.service..*)")
    public void serviceLayer() {
        //empty body
    }

    @Pointcut("execution(public * * (..))")
    public void publicMethod() {
        //empty body
    }

    @Pointcut("serviceLayer() && publicMethod()")
    public void publicServiceMethod() {
        //empty body
    }
}
