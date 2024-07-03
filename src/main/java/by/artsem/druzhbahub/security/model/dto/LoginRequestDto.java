package by.artsem.druzhbahub.security.model.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String password;
    private String email;
}
