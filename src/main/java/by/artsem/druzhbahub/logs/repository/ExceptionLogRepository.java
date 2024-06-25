package by.artsem.druzhbahub.logs.repository;

import by.artsem.druzhbahub.logs.model.ExceptionLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExceptionLogRepository extends MongoRepository<ExceptionLogEntity, String> {
}
