package dev.aminnorouzi.mcqueen.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandler {

    @AfterThrowing(pointcut = "within(*.*)", throwing = "t")
    public void log(Throwable t) {
        System.out.println("here");
        System.out.println(t.getMessage());
    }
}