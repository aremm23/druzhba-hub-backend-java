package by.artsem.druzhbahub.model.dto.profile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfileSummaryResponseDto {
    private String selfSummary;
}
