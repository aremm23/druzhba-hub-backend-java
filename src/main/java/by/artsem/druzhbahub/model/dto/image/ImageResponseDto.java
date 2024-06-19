package by.artsem.druzhbahub.model.dto.image;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponseDto {
    private String url;
    private Long id;
}
