package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
