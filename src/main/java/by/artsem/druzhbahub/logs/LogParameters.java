package by.artsem.druzhbahub.logs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogParameters {
    private LoggingLevels type;
    private String level;
    private String message;
    private String userId;
}
