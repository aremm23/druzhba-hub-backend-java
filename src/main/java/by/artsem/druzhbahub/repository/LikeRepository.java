package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Like;
import by.artsem.druzhbahub.model.Post;
import by.artsem.druzhbahub.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByProfileId(Long profileId);

    List<Like> findAllByPostId(Long postId);

    Optional<Like> findByProfileIdAndPostId(Long profileId, Long postId);

    boolean existsByProfileAndPost(Profile profile, Post post);
}
