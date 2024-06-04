package by.artsem.druzhbahub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString()
@EqualsAndHashCode()
@Builder
@AllArgsConstructor
@Entity
@Table(name = "friends")
@NoArgsConstructor
public class Friends {
    @Id
    @SequenceGenerator(name = "friendsIdSeqGen", sequenceName = "friends_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "friendsIdSeqGen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_profile_id", nullable = false)
    private Profile firstProfile;

    @ManyToOne
    @JoinColumn(name = "second_profile_id", nullable = false)
    private Profile secondProfile;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
