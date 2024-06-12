package by.artsem.druzhbahub.model.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfileCreateRequestDTO {
    @NotBlank
    private String username;
    @NotNull
    private Long accountId;
}
