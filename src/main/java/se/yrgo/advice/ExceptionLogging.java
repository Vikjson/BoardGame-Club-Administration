package se.yrgo.advice;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLogging {

    @AfterThrowing(
            pointcut = "execution(* se.yrgo..*(..))",
            throwing = "ex")
    public void logException(Exception ex) {

        System.out.println(
                "EXCEPTION: " + ex.getClass().getSimpleName()
                        + " - " + ex.getMessage()
        );
    }
}
