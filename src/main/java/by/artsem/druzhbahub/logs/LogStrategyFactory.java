package by.artsem.druzhbahub.logs;

import by.artsem.druzhbahub.logs.model.LogEntity;
import by.artsem.druzhbahub.logs.service.MongoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class LogStrategyFactory {

    private final Map<Class<? extends LogEntity>, MongoLogService<? extends LogEntity>> logServices;

    @SuppressWarnings("unchecked")
    public <T extends LogEntity> MongoLogService<T> getLogService(Class<T> logClass) {
        MongoLogService<? extends LogEntity> service = logServices.get(logClass);
        if (service == null) {
            throw new IllegalArgumentException("No log service found for class: " + logClass.getName());
        }
        return (MongoLogService<T>) service;
    }
}
