package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Post;

import java.util.List;

public interface PostService extends CRUDService<Post, Long> {
    Post updateSummary(Long id, String summary);
    Post updateEvent(Long id, Long eventId);

    List<Post> getRecommendedPostsByProfileId(Long profileId);
}
