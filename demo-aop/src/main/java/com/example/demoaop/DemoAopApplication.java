package com.example.demoaop;

import com.example.demoaop.demo.AnnotatedAspect;
import com.example.demoaop.demo.MyObject;
import com.example.demoaop.demo.MyObjectController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAopApplication.class, args);
    }

}

@Configuration
@EnableAspectJAutoProxy
class AppConfig {
}

@Aspect
@Component
class MyFilterObject {

    @Pointcut("execution(* com.example.demoaop.demo.MyObjectController.getObjectById(..))")
    public void getObjectByIdPointcut() {
    }

    @Before("getObjectByIdPointcut()")
    public void filterId(JoinPoint jp) {
        Integer pathVariablePassed = (Integer) jp.getArgs()[0];
        System.out.println("Path variable passed value is: " + pathVariablePassed);
        if (pathVariablePassed < 0) {
            throw new RuntimeException("Invalid Parameter was passed");
        }
    }

    @AfterReturning(value = "getObjectByIdPointcut()",
            returning = "result")
    public void validateReturning(ResponseEntity<MyObject> result) {
        System.out.println("Returned object: " + result.getBody());
    }

    @AfterThrowing(value = "getObjectByIdPointcut()", throwing = "ex")
    public void invalidParameter(Exception ex) {
        System.out.println("The class of thrown exception is: " + ex.getClass());
    }

    @After("getObjectByIdPointcut()")
    public void finallyExecution(){
        System.out.println("I will run regardless throwing or not");
    }

    @Around("getObjectByIdPointcut()")
    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Proceed the execution
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " ms");

        return result;
    }

    @After("@annotation(annotatedAspect)")
    public void runAfterAnnotated(AnnotatedAspect annotatedAspect){
        System.out.println("Annotated works, value is: " + annotatedAspect.someValue());
    }
}


