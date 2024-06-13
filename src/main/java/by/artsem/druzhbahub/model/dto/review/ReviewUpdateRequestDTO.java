package by.artsem.druzhbahub.model.dto.review;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewUpdateRequestDTO {
    private String comment;
    @NotNull
    private Integer grade;
}

