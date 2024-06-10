package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
