package by.artsem.druzhbahub.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponseDTO implements Serializable {
    private Long id;
    private String email;
    private String role;
    private String createdAt;
    private String updatedAt;
}
