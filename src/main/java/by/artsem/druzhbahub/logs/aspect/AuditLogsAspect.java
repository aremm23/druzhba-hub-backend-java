package by.artsem.druzhbahub.logs.aspect;

import by.artsem.druzhbahub.logs.LogParameters;
import by.artsem.druzhbahub.logs.LoggingLevels;
import by.artsem.druzhbahub.logs.MongoAppLogger;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class AuditLogsAspect {

    private final MongoAppLogger mongoAppLogger;

    @Pointcut("execution(* by.artsem.druzhbahub.security.controller.SecurityController.register(..))")
    public void registerMethod() {
    }

    @Pointcut("execution(* by.artsem.druzhbahub.security.service.EmailService.sendEmail(..))")
    public void sendEmailMethod() {
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

    @AfterReturning("registerMethod()")
    public void logAfterRegister(JoinPoint joinPoint) {
        logAction(joinPoint, "User registered");
    }

    @AfterReturning("confirmEmailWithToken()")
    public void logAfterConfirming(JoinPoint joinPoint) {
        logAction(joinPoint, "User email confirmed");
    }

    @AfterReturning("sendEmailMethod()")
    public void logAfterSendEmail(JoinPoint joinPoint) {
        logAction(joinPoint, "Email sent");
    }

    @AfterReturning("updatePasswordMethod()")
    public void logAfterUpdatePassword(JoinPoint joinPoint) {
        logAction(joinPoint, "Password updated");
    }

    @AfterReturning("updateEmailMethod()")
    public void logAfterUpdateEmail(JoinPoint joinPoint) {
        logAction(joinPoint, "Email updated");
    }

    private void logAction(JoinPoint joinPoint, String action) {
        //TODO: get user id
        String userId = "0";
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
}
