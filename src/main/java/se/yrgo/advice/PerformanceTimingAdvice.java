package se.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceTimingAdvice {

    @Around("execution(* se.yrgo.service..*(..)) || execution(* se.yrgo.data..*(..))")
    public Object measureMethodTime(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();

        try {
            return pjp.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;

            System.out.println(
                    pjp.getTarget().getClass().getSimpleName()
                            + "."
                            + pjp.getSignature().getName()
                            + " took "
                            + time
                            + " ms"
            );
        }
    }
}

