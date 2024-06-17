package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Subscription;

import java.util.List;

public interface SubscriptionService extends EntityService {
    Subscription subscribe(Long subscriberId, Long subscribedToId);
    void unsubscribe(Long subscriberId, Long subscribedToId);
    List<Long> getSubscribers(Long profileId);
    List<Long> getSubscriptions(Long profileId);
}
