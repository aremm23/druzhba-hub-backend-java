package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
