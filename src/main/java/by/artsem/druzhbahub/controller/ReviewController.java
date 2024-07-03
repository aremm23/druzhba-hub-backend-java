package by.artsem.druzhbahub.controller;

import by.artsem.druzhbahub.model.Review;
import by.artsem.druzhbahub.model.dto.review.ReviewCreateRequestDTO;
import by.artsem.druzhbahub.model.dto.review.ReviewResponseDto;
import by.artsem.druzhbahub.model.dto.review.ReviewUpdateCommentRequestDTO;
import by.artsem.druzhbahub.model.dto.review.ReviewUpdateRequestDTO;
import by.artsem.druzhbahub.model.dto.review.mapper.ReviewMapper;
import by.artsem.druzhbahub.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin
public class ReviewController {

    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<HttpStatus> createReview(@RequestBody @Valid ReviewCreateRequestDTO reviewDto) {
        reviewService.create(ReviewMapper.mapToEntity(reviewDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable Long id,
            @RequestBody @Valid ReviewUpdateRequestDTO reviewDto
    ) {
        Review updatedReview = reviewService.update(id, modelMapper.map(reviewDto, Review.class));
        return new ResponseEntity<>(
                ReviewMapper.mapToDto(updatedReview),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/comment")
    public ResponseEntity<ReviewResponseDto> updateComment(
            @PathVariable Long id,
            @RequestBody @Valid ReviewUpdateCommentRequestDTO dto
    ) {
        Review updatedReview = reviewService.updateComment(id, dto.getComment());
        return new ResponseEntity<>(
                ReviewMapper.mapToDto(updatedReview),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getById(id);
        return new ResponseEntity<>(
                ReviewMapper.mapToDto(review),
                HttpStatus.OK
        );
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewByProfileToId(@PathVariable Long id) {
        List<Review> reviews = reviewService.getReviewsByProfileToId(id);
        return new ResponseEntity<>(
                reviews.stream().map(ReviewMapper::mapToDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        List<Review> reviews = reviewService.getAll();
        return new ResponseEntity<>(
                reviews.stream().map(ReviewMapper::mapToDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}