package by.artsem.druzhbahub.controller;

import by.artsem.druzhbahub.model.Subscription;
import by.artsem.druzhbahub.model.dto.subscribtions.SubscriptionResponseDto;
import by.artsem.druzhbahub.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@CrossOrigin
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final ModelMapper modelMapper;

    @PostMapping("/{subscriberId}/{subscribedToId}")
    public ResponseEntity<SubscriptionResponseDto> subscribe(
            @PathVariable Long subscriberId,
            @PathVariable Long subscribedToId) {
        Subscription subscription = subscriptionService.subscribe(subscriberId, subscribedToId);
        return new ResponseEntity<>(modelMapper.map(subscription, SubscriptionResponseDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{subscriberId}/{subscribedToId}")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable Long subscriberId,
            @PathVariable Long subscribedToId) {
        subscriptionService.unsubscribe(subscriberId, subscribedToId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/subscribers/{profileId}")
    public ResponseEntity<List<Long>> getSubscribers(@PathVariable Long profileId) {
        List<Long> subscribers = subscriptionService.getSubscribers(profileId);
        return new ResponseEntity<>(subscribers, HttpStatus.OK);
    }

    @GetMapping("/subscriptions/{profileId}")
    public ResponseEntity<List<Long>> getSubscriptions(@PathVariable Long profileId) {
        List<Long> subscriptions = subscriptionService.getSubscriptions(profileId);
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}