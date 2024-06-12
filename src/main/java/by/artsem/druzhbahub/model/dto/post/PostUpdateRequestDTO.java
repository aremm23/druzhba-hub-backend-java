package by.artsem.druzhbahub.model.dto.post;

import by.artsem.druzhbahub.model.Likes;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PostUpdateRequestDTO {
    private String summary;
    @NotNull
    private Long eventId;
    private List<Likes> likes;
}
