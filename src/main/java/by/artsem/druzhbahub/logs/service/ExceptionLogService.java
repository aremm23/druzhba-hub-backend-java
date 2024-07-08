package by.artsem.druzhbahub.logs.service;

import by.artsem.druzhbahub.logs.model.ExceptionLogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExceptionLogService implements MongoLogService<ExceptionLogEntity> {

    private final MongoRepository<ExceptionLogEntity, String> repository;

    @Override
    public void log(ExceptionLogEntity log) {
        repository.save(log);
    }
}