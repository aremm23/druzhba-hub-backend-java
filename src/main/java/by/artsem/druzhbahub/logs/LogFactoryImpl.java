package by.artsem.druzhbahub.logs;

import by.artsem.druzhbahub.logs.model.AuditLogEntity;
import by.artsem.druzhbahub.logs.model.ExceptionLogEntity;
import by.artsem.druzhbahub.logs.model.LogEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LogFactoryImpl implements LogFactory {
    public LogEntity createLogger(LogParameters parameters) {
        return switch (parameters.getType()) {
            case AUDIT -> createAuditLogger(parameters);
            case EXCEPTION -> createExceptionLogger(parameters);
        };
    }

    public ExceptionLogEntity createExceptionLogger(LogParameters parameters) {
        return ExceptionLogEntity.builder()
                .id("exception-%s".formatted(LocalDateTime.now()))
                .timestamp(LocalDateTime.now())
                .level("EXCEPTION")
                .message(parameters.getMessage())
                .build();
    }

    public AuditLogEntity createAuditLogger(LogParameters parameters) {
        return AuditLogEntity.builder()
                .userId(parameters.getUserId())
                .id("user#%s-%s".formatted(parameters.getUserId(), LocalDateTime.now()))
                .level("AUDIT")
                .timestamp(LocalDateTime.now())
                .message(parameters.getMessage())
                .build();
    }
}
