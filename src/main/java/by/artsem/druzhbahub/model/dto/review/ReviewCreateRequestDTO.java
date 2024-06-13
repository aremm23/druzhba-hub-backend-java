package by.artsem.druzhbahub.model.dto.review;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewCreateRequestDTO {
    @NotNull
    private Long profileFromId;
    @NotNull
    private Long profileToId;
    private String comment;
    @NotNull
    private Integer grade;
}