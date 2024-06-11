package by.artsem.druzhbahub.model.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateRequestDTO {
    @NotEmpty(message = "Summary is required")
    @Size(max = 350, message = "Summary must be less than 350 characters")
    private String summary;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotEmpty(message = "Location is required")
    private String location;

    @NotNull(message = "Start date and time are required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startAt;

    private List<Long> eventImageIds;
}
