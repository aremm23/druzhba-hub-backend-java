package by.artsem.druzhbahub.model.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewUpdateRequestDTO {
    @NotNull
    private String comment;
    @NotNull
    @Min(0)
    @Max(5)
    private Integer grade;
}

