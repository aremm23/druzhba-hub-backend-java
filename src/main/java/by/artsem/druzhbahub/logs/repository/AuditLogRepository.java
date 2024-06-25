package by.artsem.druzhbahub.logs.repository;

import by.artsem.druzhbahub.logs.model.AuditLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLogEntity, String> {
}
