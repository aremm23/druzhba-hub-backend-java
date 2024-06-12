package by.artsem.druzhbahub.model.dto.post;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostUpdateEventRequestDTO {
    @NotNull
    private Long eventId;
}

