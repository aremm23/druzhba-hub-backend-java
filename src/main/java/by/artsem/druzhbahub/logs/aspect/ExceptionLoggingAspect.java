package by.artsem.druzhbahub.logs.aspect;

import by.artsem.druzhbahub.logs.LogParameters;
import by.artsem.druzhbahub.logs.LoggingLevels;
import by.artsem.druzhbahub.logs.MongoAppLogger;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
public class ExceptionLoggingAspect {
    private final MongoAppLogger mongoAppLogger;

    @AfterThrowing(pointcut = "execution(* by.artsem.druzhbahub.service.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        LogParameters parameters = LogParameters.builder()
                .type(LoggingLevels.EXCEPTION)
                .level("WARNING")
                .message("Exception in method: " + joinPoint.getSignature() + " with message: " + exception.getMessage())
                .build();

        mongoAppLogger.log(parameters);
    }
}
