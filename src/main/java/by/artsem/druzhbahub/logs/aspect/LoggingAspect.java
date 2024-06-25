package by.artsem.druzhbahub.logs.aspect;

import by.artsem.druzhbahub.logs.LogParameters;
import by.artsem.druzhbahub.logs.LoggingLevels;
import by.artsem.druzhbahub.logs.MongoAppLogger;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
public class LoggingAspect {
    private final MongoAppLogger mongoAppLogger;

    /*@Before("execution(* by.artsem.druzhbahub.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        LogParameters parameters = LogParameters.builder()
                .type(LoggingLevels.AUDIT)
                .level("INFO")
                .message("Entering method: " + joinPoint.getSignature())
                .userId(getCurrentUserId())
                .build();

        mongoAppLogger.log(parameters);
    }*/

    @AfterThrowing(pointcut = "execution(* by.artsem.druzhbahub.service.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        LogParameters parameters = LogParameters.builder()
                .type(LoggingLevels.EXCEPTION)
                .level("WARNING")
                .message("Exception in method: " + joinPoint.getSignature() + " with message: " + exception.getMessage())
                .userId(getCurrentUserId())
                .build();

        mongoAppLogger.log(parameters);
    }

    private String getCurrentUserId() {
        // TODO: Implement method to retrieve the current user's ID from the security context
        return "anonymous";
    }
}
