package by.artsem.druzhbahub.security.model.dto;

import lombok.Data;

@Data
public class PasswordUpdateRequestDTO {
    //TODO validate data
    private String updatedPassword;
    private String currentPassword;
}
