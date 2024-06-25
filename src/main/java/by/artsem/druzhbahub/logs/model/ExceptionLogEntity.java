package by.artsem.druzhbahub.logs.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "exception_logs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionLogEntity implements LogEntity {
    @Id
    private String id;
    private String level;
    private String message;
    private LocalDateTime timestamp;
}
