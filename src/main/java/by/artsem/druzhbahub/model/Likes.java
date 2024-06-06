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
@Table(name = "likes")
@NoArgsConstructor
public class Likes {
    @Id
    @SequenceGenerator(name = "likeIdSeqGen", sequenceName = "like_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "likeIdSeqGen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
