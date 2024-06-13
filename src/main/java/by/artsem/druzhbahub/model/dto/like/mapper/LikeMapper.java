package by.artsem.druzhbahub.model.dto.like.mapper;

import by.artsem.druzhbahub.model.Like;
import by.artsem.druzhbahub.model.Post;
import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.model.dto.like.CreateLikeRequestDto;
import by.artsem.druzhbahub.model.dto.like.LikeResponseDto;

public class LikeMapper {
    public static Like mapToEntity(CreateLikeRequestDto dto) {
        return Like.builder()
                .post(Post.builder().id(dto.getPostId()).build())
                .profile(Profile.builder().id(dto.getProfileId()).build())
                .build();
    }

    public static LikeResponseDto mapToDto(Like like) {
        return LikeResponseDto.builder()
                .postId(like.getPost().getId())
                .profileId(like.getProfile().getId())
                .id(like.getId())
                .build();
    }
}
