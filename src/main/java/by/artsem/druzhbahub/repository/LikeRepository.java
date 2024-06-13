package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByProfileId(Long profileId);
    List<Like> findAllByPostId(Long postId);
}
