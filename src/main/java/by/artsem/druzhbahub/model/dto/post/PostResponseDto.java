package by.artsem.druzhbahub.model.dto.post;

import by.artsem.druzhbahub.model.Likes;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDto {
    private Long id;
    private Long profileId;
    private Long eventId;
    private String summary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Likes> likes;
}
