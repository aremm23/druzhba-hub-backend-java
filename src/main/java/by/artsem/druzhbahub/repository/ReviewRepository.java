package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT AVG(r.grade) FROM Review r WHERE r.profileTo.id = :profileId")
    Optional<Double> findAverageGradeByProfileId(@Param("profileId") Long profileId);

    boolean existsByProfileFromIdAndProfileToId(Long profileFrom, Long profileTo);
    List<Review> findByProfileToId(Long profileToId);
}
