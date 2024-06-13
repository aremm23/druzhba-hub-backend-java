package by.artsem.druzhbahub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString()
@EqualsAndHashCode()
@Builder
@AllArgsConstructor
@Entity
@Table(name = "post")
@NoArgsConstructor
public class Post {
    @Id
    @SequenceGenerator(name = "postIdSeqGen", sequenceName = "post_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "postIdSeqGen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(length = 350)
    private String summary;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;
}
