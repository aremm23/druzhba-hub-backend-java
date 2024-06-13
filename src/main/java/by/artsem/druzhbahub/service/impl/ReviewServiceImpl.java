package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Review;
import by.artsem.druzhbahub.repository.ReviewRepository;
import by.artsem.druzhbahub.service.ProfileService;
import by.artsem.druzhbahub.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getById(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Review with id %d not found".formatted(id))
        );
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review update(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Review with id %d not found".formatted(id))
        );
        existingReview.setComment(updatedReview.getComment());
        existingReview.setGrade(updatedReview.getGrade());
        return reviewRepository.save(existingReview);
    }

    @Override
    public void delete(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Review with id %d not found".formatted(id))
        );
        reviewRepository.delete(review);
    }

    @Override
    public Review updateComment(Long id, String comment) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Review with id %d not found".formatted(id))
        );
        review.setComment(comment);
        return reviewRepository.save(review);
    }
}

