package by.artsem.druzhbahub.model;

import java.time.LocalDateTime;

public interface Image {
    Long getId();
    String getGcsFileName();
    LocalDateTime getUploadTime();
}
