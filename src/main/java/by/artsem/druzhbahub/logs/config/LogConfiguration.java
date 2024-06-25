package by.artsem.druzhbahub.logs.config;

import by.artsem.druzhbahub.logs.model.AuditLogEntity;
import by.artsem.druzhbahub.logs.model.ExceptionLogEntity;
import by.artsem.druzhbahub.logs.model.LogEntity;
import by.artsem.druzhbahub.logs.service.AuditLogService;
import by.artsem.druzhbahub.logs.service.ExceptionLogService;
import by.artsem.druzhbahub.logs.service.MongoLogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class LogConfiguration {
    @Bean
    public Map<Class<? extends LogEntity>, MongoLogService<? extends LogEntity>> logServices(
            AuditLogService auditLogService,
            ExceptionLogService exceptionLogService) {
        return Map.of(
                AuditLogEntity.class, auditLogService,
                ExceptionLogEntity.class, exceptionLogService
        );
    }
}
