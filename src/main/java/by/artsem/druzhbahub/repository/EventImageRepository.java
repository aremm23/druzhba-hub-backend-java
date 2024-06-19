package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventImageRepository extends JpaRepository<EventImage, Long> {
    List<EventImage> findByEventId(Long eventId);

    @Query("SELECT COUNT(ei) FROM EventImage ei WHERE ei.event.id = :eventId")
    Integer countImagesByEventId(@Param("eventId") Long eventId);

}