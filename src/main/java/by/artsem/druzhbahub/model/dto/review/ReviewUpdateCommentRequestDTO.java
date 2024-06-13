package by.artsem.druzhbahub.model.dto.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewUpdateCommentRequestDTO {
    @NotBlank
    private String comment;
}
