package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Like;

import java.util.List;

public interface LikeService extends EntityService {
    List<Like> getAllByProfileId(Long aLong);

    List<Like> getAllByPostId(Long aLong);

    void delete(Long id);

    List<Like> getAll();

    Like create(Like like);
}
