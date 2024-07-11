package by.artsem.druzhbahub.model.dto.profile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileUpdateGeneralRequestDTO {
    @Size(max = 300)
    private String selfSummary;
    @Min(18)
    @Max(99)
    private Integer age;
    @NotBlank
    private String place;
}
