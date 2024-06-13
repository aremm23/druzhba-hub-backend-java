package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Review;

public interface ReviewService extends CRUDService<Review, Long> {
    Review updateComment(Long id, String comment);
}
