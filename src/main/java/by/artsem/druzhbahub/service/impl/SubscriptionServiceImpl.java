package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotCreatedException;
import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.model.Subscription;
import by.artsem.druzhbahub.repository.ProfileRepository;
import by.artsem.druzhbahub.repository.SubscriptionRepository;
import by.artsem.druzhbahub.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final ProfileRepository profileRepository;

    @Override
    public Subscription subscribe(Long subscriberId, Long subscribedToId) {
        Profile subscriber = profileRepository.findById(subscriberId).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(subscriberId))
        );
        Profile subscribedTo = profileRepository.findById(subscribedToId).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(subscribedToId))
        );
        if(subscriptionRepository.existsBySubscriberAndSubscribedTo(subscriber, subscribedTo)) {
            throw new DataNotCreatedException("Subscription already exist");
        }
        Subscription subscription = Subscription.builder()
                .subscriber(subscriber)
                .subscribedTo(subscribedTo)
                .createdAt(LocalDateTime.now())
                .build();
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void unsubscribe(Long subscriberId, Long subscribedToId) {
        Profile subscriber = profileRepository.findById(subscriberId).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(subscriberId))
        );
        Profile subscribedTo = profileRepository.findById(subscribedToId).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(subscribedToId))
        );
        Subscription subscription =
                subscriptionRepository.findBySubscriberAndSubscribedTo(subscriber, subscribedTo).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(subscribedToId))
        );
        subscriptionRepository.delete(subscription);
    }

    @Override
    public List<Long> getSubscribers(Long profileId) {
        return subscriptionRepository.findBySubscribedTo(profileId);
    }

    @Override
    public List<Long> getSubscriptions(Long profileId) {
        return subscriptionRepository.findBySubscriber(profileId);
    }
}
