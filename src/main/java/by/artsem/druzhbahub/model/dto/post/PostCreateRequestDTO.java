package by.artsem.druzhbahub.model.dto.post;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostCreateRequestDTO {
    @NotNull
    private Long profileId;
    @NotNull
    private Long eventId;
    private String summary;
}
