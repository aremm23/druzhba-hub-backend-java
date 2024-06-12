package by.artsem.druzhbahub.model.dto.post.mapper;

import by.artsem.druzhbahub.model.Event;
import by.artsem.druzhbahub.model.Post;
import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.model.dto.post.PostCreateRequestDTO;

public class PostCreateRequestDtoMapper {//TODO add likes
    public static Post mapToPost(PostCreateRequestDTO dto){
        return Post.builder()
                .event(Event.builder().id(dto.getEventId()).build())
                .profile(Profile.builder().id(dto.getProfileId()).build())
                .summary(dto.getSummary())
                .build();
    }
}
