package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Post;

public interface PostService extends CRUDService<Post, Long> {
    Post updateSummary(Long id, String summary);
    Post updateEvent(Long id, Long eventId);
}
