package by.artsem.druzhbahub.model.dto.event;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventUpdateLocationRequestDto {
    @NotNull
    private String location;
}
