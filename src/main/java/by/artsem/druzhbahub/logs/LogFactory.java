package by.artsem.druzhbahub.logs;

import by.artsem.druzhbahub.logs.model.AuditLogEntity;
import by.artsem.druzhbahub.logs.model.ExceptionLogEntity;
import by.artsem.druzhbahub.logs.model.LogEntity;

public interface LogFactory {
    LogEntity createLogger(LogParameters parameters);
    ExceptionLogEntity createExceptionLogger(LogParameters parameters);
    AuditLogEntity createAuditLogger(LogParameters parameters);
}
