package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT s.subscriber.id FROM Subscription s WHERE s.subscribedTo.id = :subscribedToId")
    List<Long> findBySubscribedTo(@Param("subscribedToId") Long subscribedToId);

    @Query("SELECT s.subscribedTo.id FROM Subscription s WHERE s.subscriber.id = :subscriberId")
    List<Long> findBySubscriber(@Param("subscriberId") Long subscriberId);

    Optional<Subscription> findBySubscriberAndSubscribedTo(Profile subscriber, Profile subscribedTo);

    boolean existsBySubscriberAndSubscribedTo(Profile subscriber, Profile subscribedTo);
}
