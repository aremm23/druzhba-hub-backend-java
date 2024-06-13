package by.artsem.druzhbahub.model.dto.post.mapper;

import by.artsem.druzhbahub.model.Event;
import by.artsem.druzhbahub.model.Post;
import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.model.dto.post.PostCreateRequestDTO;
import by.artsem.druzhbahub.model.dto.post.PostResponseDto;

public class PostMapper {
    public static Post mapToEntity(PostCreateRequestDTO dto){
        return Post.builder()
                .event(Event.builder().id(dto.getEventId()).build())
                .profile(Profile.builder().id(dto.getProfileId()).build())
                .summary(dto.getSummary())
                .build();
    }

    public static PostResponseDto mapToDto(Post post) {
        return PostResponseDto.builder()
                .profileId(post.getProfile().getId())
                .eventId(post.getEvent().getId())
                .amountOfLikes(post.getLikes().size())
                .summary(post.getSummary())
                .id(post.getId())
                .build();
    }
}
