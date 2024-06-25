package by.artsem.druzhbahub.logs.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SimpleLoggingAspect {
    @Pointcut("within(by.artsem.druzhbahub.service.impl.*)")
    public static void logPointCut(){}

    @Before(value = "logPointCut()")
    public void logAtTheBeginning(JoinPoint joinPoint) {
        log.info("Started: {}", joinPoint.getSignature());
    }

    @After(value = "logPointCut()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Finish: {}.", joinPoint.getSignature());
    }

    @AfterReturning(value = "logPointCut()", returning = "retVal")
    public void logAfterReturn(JoinPoint joinPoint, Object retVal) {
        if(retVal == null) {
            log.info("Returned null from {}", joinPoint.getSignature());
        }
        else {
            log.info("Returned from {}, value {}", joinPoint.getSignature() ,retVal);
        }
    }
}
