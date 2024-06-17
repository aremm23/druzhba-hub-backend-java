package by.artsem.druzhbahub.model.dto.review.mapper;

import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.model.Review;
import by.artsem.druzhbahub.model.dto.review.ReviewCreateRequestDTO;
import by.artsem.druzhbahub.model.dto.review.ReviewResponseDto;

public class ReviewMapper {
    public static ReviewResponseDto mapToDto(Review review) {
        return ReviewResponseDto.builder()
                .grade(review.getGrade())
                .profileFromId(review.getProfileFrom().getId())
                .profileToId(review.getProfileTo().getId())
                .comment(review.getComment())
                .id(review.getId())
                .build();
    }

    public static Review mapToEntity(ReviewCreateRequestDTO dto) {
        return Review.builder()
                .comment(dto.getComment())
                .grade(dto.getGrade())
                .profileFrom(Profile.builder().id(dto.getProfileFromId()).build())
                .profileTo(Profile.builder().id(dto.getProfileToId()).build())
                .build();
    }
}
