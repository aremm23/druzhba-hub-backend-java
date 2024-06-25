package by.artsem.druzhbahub.logs.service;

import by.artsem.druzhbahub.logs.model.LogEntity;

public interface MongoLogService<T extends LogEntity> {
    void log(T log);
}
