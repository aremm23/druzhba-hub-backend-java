package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Event;
import by.artsem.druzhbahub.repository.EventRepository;
import by.artsem.druzhbahub.service.CategoryService;
import by.artsem.druzhbahub.service.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final CategoryService categoryService;

    @Override
    @Transactional
    public Event updateSummary(Long id, String summary) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Event with id %d not found".formatted(id))
        );
        event.setSummary(summary);
        event.setUpdatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event updateCategory(Long id, Long categoryId) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Event with id %d not found".formatted(id))
        );
        event.setCategory(categoryService.getById(categoryId));
        event.setUpdatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event updateLocation(Long id, String location) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Event with id %d not found".formatted(id))
        );
        event.setLocation(location);
        event.setUpdatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event updateStartAt(Long id, LocalDateTime startAt) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Event with id %d not found".formatted(id))
        );
        event.setStartAt(startAt);
        event.setUpdatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event create(Event entity) {
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return eventRepository.save(entity);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Event with id %d not found".formatted(id))
        );
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    @Transactional
    public Event update(Long id, Event updatedEvent) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Event with id %d not found".formatted(id))
        );
        existingEvent.setSummary(updatedEvent.getSummary());
        existingEvent.setCategory(updatedEvent.getCategory());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setStartAt(updatedEvent.getStartAt());
        existingEvent.setUpdatedAt(LocalDateTime.now());
        existingEvent.setEventImages(updatedEvent.getEventImages());
        existingEvent.setPosts(updatedEvent.getPosts());
        return eventRepository.save(existingEvent);
    }

    @Override
    public void delete(Long id) {
        eventRepository.delete(eventRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Event with id %d not found".formatted(id))
        ));
    }
}