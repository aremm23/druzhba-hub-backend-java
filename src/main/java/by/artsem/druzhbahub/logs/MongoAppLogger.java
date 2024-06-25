package by.artsem.druzhbahub.logs;

import by.artsem.druzhbahub.logs.model.LogEntity;
import by.artsem.druzhbahub.logs.service.MongoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MongoAppLogger {

    private final LogStrategyFactory logStrategyFactory;

    private final LogFactory logFactory;

    public void log(LogParameters parameters) {
        LogEntity logEntity = createLogEntity(parameters);
        logToService(logEntity);
    }

    private LogEntity createLogEntity(LogParameters parameters) {
        return switch (parameters.getType()) {
            case EXCEPTION -> logFactory.createExceptionLogger(parameters);
            case AUDIT -> logFactory.createAuditLogger(parameters);
        };
    }

    private <T extends LogEntity> void logToService(T logEntity) {
        MongoLogService<T> logService = logStrategyFactory.getLogService((Class<T>) logEntity.getClass());
        logService.log(logEntity);
    }
}
