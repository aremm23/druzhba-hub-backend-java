package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Like;

import java.util.List;

public interface LikeService extends EntityService {
    List<Like> getAllByProfileId(Long aLong);

    List<Like> getAllByPostId(Long aLong);

    List<Like> getAll();

    void deleteByPostAndProfileId(Long postId, Long profileId);

    Like create(Like like);

    void delete(Long id);
}
