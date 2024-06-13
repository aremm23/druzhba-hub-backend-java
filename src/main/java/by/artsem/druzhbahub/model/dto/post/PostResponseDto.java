package by.artsem.druzhbahub.model.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponseDto {
    private Long id;
    private Long profileId;
    private Long eventId;
    private String summary;
    private Integer amountOfLikes;
}
