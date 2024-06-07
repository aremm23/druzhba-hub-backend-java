package by.artsem.druzhbahub.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString()
@EqualsAndHashCode()
@Builder
@AllArgsConstructor
@Entity
@Table(name = "review")
@NoArgsConstructor
public class Review {
    @Id
    @SequenceGenerator(name = "reviewIdSeqGen", sequenceName = "review_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "reviewIdSeqGen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_from")
    private Profile profileFrom;

    @ManyToOne
    @JoinColumn(name = "profile_to")
    private Profile profileTo;

    @Column
    private String comment;

    @Column(nullable = false)
    private Integer grade;
}
