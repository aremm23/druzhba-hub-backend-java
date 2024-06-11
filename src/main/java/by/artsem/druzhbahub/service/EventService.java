package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Event;

import java.time.LocalDateTime;

public interface EventService extends CRUDService<Event, Long> {
    Event updateSummary(Long id, String summary);
    Event updateCategory(Long id, Long categoryId);
    Event updateLocation(Long id, String location);
    Event updateStartAt(Long id, LocalDateTime startAt);
}
