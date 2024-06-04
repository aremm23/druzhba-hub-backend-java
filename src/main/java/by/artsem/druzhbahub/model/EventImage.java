package by.artsem.druzhbahub.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString()
@EqualsAndHashCode()
@Builder
@AllArgsConstructor
@Entity
@Table(name = "event_image")
@NoArgsConstructor
public class EventImage implements Image {
    @Id
    @SequenceGenerator(name = "eventImageIdSeqGen", sequenceName = "event_image_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "eventImageIdSeqGen")
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    private String imageName;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
