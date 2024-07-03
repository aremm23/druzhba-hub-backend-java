package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Review;

import java.util.List;

public interface ReviewService extends CRUDService<Review, Long> {
    Review updateComment(Long id, String comment);
    double getAverageRating(Long profileId);
    List<Review> getReviewsByProfileToId(Long id);
}
