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
@Table(name = "event_image")
@NoArgsConstructor
public class EventImage implements Image {
    @Id
    @SequenceGenerator(name = "eventImageIdSeqGen", sequenceName = "event_image_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "eventImageIdSeqGen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String gcsFileName;

    @Column(nullable = false)
    private LocalDateTime uploadTime;
}
