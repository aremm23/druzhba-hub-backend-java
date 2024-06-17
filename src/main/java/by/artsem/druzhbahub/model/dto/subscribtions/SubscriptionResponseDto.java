package by.artsem.druzhbahub.model.dto.subscribtions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionResponseDto {
    private Long id;
    private Long subscriberId;
    private Long subscribedToId;
    private LocalDateTime createdAt;
}
