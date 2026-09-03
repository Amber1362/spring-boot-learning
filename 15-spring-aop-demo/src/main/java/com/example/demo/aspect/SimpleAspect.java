package com.example.demo.aspect;

import com.example.demo.annotation.TrackExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SimpleAspect {

//    @Before("@annotation(jdk.jfr.Timestamp)")
//    public void logBeforeMethod() {
//        System.out.println("Student is going to be saved");
//    }

    @Around("@annotation(trackExecutionTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint,
                                       TrackExecutionTime trackExecutionTime)
            throws Throwable {

        long startTime = System.currentTimeMillis();

        try {
           return joinPoint.proceed();
        }
        finally {
            long endTime = System.currentTimeMillis();
            long totalDuration = endTime - startTime;

            String operation = trackExecutionTime.operation();

            long warningThreshold = trackExecutionTime.warnAfter();

            if(operation.isBlank()) {
                operation = joinPoint.getSignature().getName();
            }

            if(totalDuration >= warningThreshold) {
                System.out.println("SLOW OPERATION ALERT : "
                        + "Time taken by " + operation + " : " + totalDuration);
            } else {
                System.out.println("Time taken by " + operation + " : " + totalDuration);
            }
        }
    }
}
