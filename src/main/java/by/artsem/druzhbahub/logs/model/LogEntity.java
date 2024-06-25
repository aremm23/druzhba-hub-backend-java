package by.artsem.druzhbahub.logs.model;

import java.time.LocalDateTime;

public interface LogEntity {
    String getId();
    String getLevel();
    String getMessage();
    LocalDateTime getTimestamp();
}
