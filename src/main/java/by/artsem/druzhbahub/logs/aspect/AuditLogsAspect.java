package by.artsem.druzhbahub.logs.aspect;

import by.artsem.druzhbahub.logs.LogParameters;
import by.artsem.druzhbahub.logs.LoggingLevels;
import by.artsem.druzhbahub.logs.MongoAppLogger;
import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class AuditLogsAspect {

    private final MongoAppLogger mongoAppLogger;

    private final AccountService accountService;

    @Pointcut("execution(* by.artsem.druzhbahub.security.service.AccountService.createAccountAndProfile(..))")
    public void registerMethod() {
    }

    @Pointcut("execution(* by.artsem.druzhbahub.security.service.AccountService.updatePassword(..))")
    public void updatePasswordMethod() {
    }

    @Pointcut("execution(* by.artsem.druzhbahub.security.service.AccountService.updateEmail(..))")
    public void updateEmailMethod() {
    }

    @Pointcut("execution(* by.artsem.druzhbahub.security.service.AccountService.confirmEmail(..))")
    public void confirmEmailWithToken() {
    }

    @Pointcut("execution(* by.artsem.druzhbahub.service.PostService.create(..))")
    public void createPost() {
    }

    @AfterReturning(value = "registerMethod()", returning = "result")
    public void logAfterRegister(JoinPoint joinPoint, Account result) {
        logAction(joinPoint, "User registered", result.getId().toString());
    }

    @AfterReturning(value = "confirmEmailWithToken()", returning = "result")
    public void logAfterConfirming(JoinPoint joinPoint, Account result) {
        logAction(joinPoint, "User email confirmed", result.getId().toString());
    }

    @AfterReturning("updatePasswordMethod()")
    public void logAfterUpdatePassword(JoinPoint joinPoint) {
        logAction(joinPoint, "Password updated", getUserId());
    }

    @After(value = "createPost()")
    public void logAfterCreatingPost(JoinPoint joinPoint) {
        logAction(joinPoint, "Post created", getUserId());
    }

    @AfterReturning("updateEmailMethod()")
    public void logAfterUpdateEmail(JoinPoint joinPoint) {
        logAction(joinPoint, "Email updated", getUserId());
    }

    private void logAction(JoinPoint joinPoint, String action, String userId) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String message = String.format("Action: %s, Method: %s, Class: %s, User: %s", action, methodName, className, userId);
        mongoAppLogger.log(
                LogParameters.builder()
                        .userId(userId)
                        .level("AUDIT")
                        .type(LoggingLevels.AUDIT)
                        .message(message)
                        .build()
        );
    }

    private String getUserId() {
        return accountService.getCurrentUser().getId().toString();
    }
}
