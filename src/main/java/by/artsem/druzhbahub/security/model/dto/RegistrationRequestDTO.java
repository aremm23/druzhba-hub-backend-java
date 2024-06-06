package by.artsem.druzhbahub.security.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationRequestDTO {
    private String email;
    private String password;
    private String role;
    private String username;
}
