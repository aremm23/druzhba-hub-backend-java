package by.artsem.druzhbahub.model.dto.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponseDto {
    private Long id;
    private Long profileFromId;
    private Long profileToId;
    private String comment;
    private Integer grade;
}