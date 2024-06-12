package by.artsem.druzhbahub.model.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostUpdateSummaryRequestDTO {
    @NotBlank
    private String summary;
}
