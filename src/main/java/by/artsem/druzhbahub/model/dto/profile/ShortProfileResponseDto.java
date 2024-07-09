package by.artsem.druzhbahub.model.dto.profile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShortProfileResponseDto {
    private String avatarUrl;
    private Long id;
    private String username;
}
