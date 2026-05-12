package se.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethod {
    @Around(
            "execution(* se.yrgo.controller.*.*(..)) || " +
                    "execution(* se.yrgo.service.*.*(..))")
    public Object logMethodCall(ProceedingJoinPoint method) throws Throwable {

        System.out.println(
                "[LOG] Calling method "
                        + method.getSignature().getName()
                        + " from class "
                        + method.getTarget().getClass().getName());
        try {
            Object value = method.proceed();

            System.out.println("[LOG] Method "
                    + method.getSignature().getName()
                    + " executed successfully");
            return value;
        } catch (Throwable throwable) {
            System.out.println(
                    "[ERROR] Method "
                            + method.getSignature().getName()
                            + " threw exception: "
                            + throwable.getMessage());
            throw throwable;
        }
    }

}
