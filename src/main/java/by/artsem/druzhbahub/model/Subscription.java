package by.artsem.druzhbahub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString(exclude = {"subscriber", "subscribedTo"})
@EqualsAndHashCode()
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @SequenceGenerator(name = "subscriptionIdSeqGen", sequenceName = "subscriptions_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "subscriptionIdSeqGen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Profile subscriber;

    @ManyToOne
    @JoinColumn(name = "subscribed_to_id")
    private Profile subscribedTo;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
