package by.artsem.druzhbahub.controller;

import by.artsem.druzhbahub.model.Event;
import by.artsem.druzhbahub.model.dto.event.*;
import by.artsem.druzhbahub.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<HttpStatus> createEvent(@RequestBody @Valid EventCreateRequestDTO eventDto) {
        eventService.create(modelMapper.map(eventDto, Event.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")//TODO make DTO
    public ResponseEntity<EventResponseDto> updateEvent(
            @PathVariable Long id,
            @RequestBody @Valid Event event
    ) {
        return new ResponseEntity<>(
                modelMapper.map(eventService.update(id, event), EventResponseDto.class),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/summary")
    public ResponseEntity<EventResponseDto> updateSummary(
            @PathVariable Long id,
            @RequestBody @Valid EventUpdateSummaryRequestDto dto
    ) {
        return new ResponseEntity<>(
                modelMapper.map(eventService.updateSummary(id, dto.getSummary()), EventResponseDto.class),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<EventResponseDto> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid EventUpdateCategoryRequestDto dto
    ) {
        return new ResponseEntity<>(
                modelMapper.map(eventService.updateCategory(id, dto.getCategoryId()), EventResponseDto.class),
                HttpStatus.OK);
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<EventResponseDto> updateLocation(
            @PathVariable Long id,
            @RequestBody @Valid EventUpdateLocationRequestDto dto
    ) {
        return new ResponseEntity<>(
                modelMapper.map(eventService.updateLocation(id, dto.getLocation()), EventResponseDto.class),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<EventResponseDto> updateStartAt(
            @PathVariable Long id,
            @RequestBody @Valid EventUpdateStartAtRequestDto dto
    ) {
        return new ResponseEntity<>(
                modelMapper.map(eventService.updateStartAt(id, dto.getStartAt()), EventResponseDto.class),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {
        return new ResponseEntity<>(
                modelMapper.map(eventService.getById(id), EventResponseDto.class),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        return new ResponseEntity<>(
                eventService.getAll().stream().map(
                        event -> modelMapper.map(event, EventResponseDto.class)
                ).toList(),
                HttpStatus.OK
        );
    }
}
