package by.artsem.druzhbahub.model.dto.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileUpdateUsernameRequestDTO {
    @NotBlank
    private String username;
}
