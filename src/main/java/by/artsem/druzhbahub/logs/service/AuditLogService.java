package by.artsem.druzhbahub.logs.service;

import by.artsem.druzhbahub.logs.model.AuditLogEntity;
import by.artsem.druzhbahub.logs.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuditLogService implements MongoLogService<AuditLogEntity> {

    private final AuditLogRepository repository;

    @Override
    public void log(AuditLogEntity log) {
        repository.save(log);
    }
}
