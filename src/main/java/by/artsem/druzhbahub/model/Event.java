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
@Table(name = "event")
@NoArgsConstructor
public class Event {
    @Id
    @SequenceGenerator(name = "eventIdSeqGen", sequenceName = "event_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "eventIdSeqGen")
    private Long id;

    @Column(length = 350)
    private String summary;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String location;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventImage> eventImages;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Post> posts;
}
