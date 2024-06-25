package by.artsem.druzhbahub.logs.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "audit_logs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditLogEntity implements LogEntity {
    @Id
    private String id;
    private String level;
    private String message;
    private LocalDateTime timestamp;
    private String userId;
}
