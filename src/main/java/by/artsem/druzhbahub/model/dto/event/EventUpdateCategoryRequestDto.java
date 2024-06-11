package by.artsem.druzhbahub.model.dto.event;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventUpdateCategoryRequestDto {
    @NotNull
    private Long categoryId;
}
