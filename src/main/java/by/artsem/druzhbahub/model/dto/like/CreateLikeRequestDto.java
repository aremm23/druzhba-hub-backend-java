package by.artsem.druzhbahub.model.dto.like;

import lombok.Data;

@Data
public class CreateLikeRequestDto {
    private Long profileId;
    private Long postId;
}
