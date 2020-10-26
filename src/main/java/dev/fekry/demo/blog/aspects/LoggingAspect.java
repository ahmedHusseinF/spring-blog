package dev.fekry.demo.blog.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

  @Around("within(dev.fekry.demo.blog.controllers..*)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long b4 = System.currentTimeMillis();

    Object proceed = joinPoint.proceed();

    long execTime = System.currentTimeMillis() - b4;
    System.out.println("Method " + joinPoint.getSignature() + " took " + execTime + " millis");
    return proceed;
  }
}
