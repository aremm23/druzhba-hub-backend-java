package by.artsem.druzhbahub.model.dto.like;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeResponseDto {
    private Long id;
    private Long profileId;
    private Long postId;
}
