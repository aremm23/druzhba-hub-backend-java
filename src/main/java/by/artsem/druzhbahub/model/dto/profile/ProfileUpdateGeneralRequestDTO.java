package by.artsem.druzhbahub.model.dto.profile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileUpdateGeneralRequestDTO {
    private String selfSummary;
    private Integer age;
    private String place;
}
