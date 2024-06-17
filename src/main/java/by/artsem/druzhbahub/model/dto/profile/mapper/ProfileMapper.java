package by.artsem.druzhbahub.model.dto.profile.mapper;

import by.artsem.druzhbahub.model.*;
import by.artsem.druzhbahub.model.dto.profile.ProfileResponseDto;

public class ProfileMapper {
    public static ProfileResponseDto mapToDto(Profile profile) {
        return ProfileResponseDto.builder()
                .id(profile.getId())
                .rate(profile.getRate())
                .username(profile.getUsername())
                .selfSummary(profile.getSelfSummary())
                .posts(profile.getPosts().stream().map(Post::getId).toList())
                .subscribedTo(profile.getSubscribeTo().size())
                .subscribers(profile.getSubscribers().size())
                .reviewsFromThis(profile.getReviewsFrom().stream().map(Review::getId).toList())
                .reviewsOnThis(profile.getReviewsTo().stream().map(Review::getId).toList())
                .build();
    }
}
