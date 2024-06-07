package by.artsem.druzhbahub.security.model.dto;

import lombok.Data;

@Data
public class RegistrationRequestDTO {
    private String email;
    private String password;
    private String role;
    private String username;
}
