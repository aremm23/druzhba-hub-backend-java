package by.artsem.druzhbahub.security.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokenResponseDto {
    private String token;
    private Long id;
}
