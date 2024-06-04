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
@Table(name = "category")
@NoArgsConstructor
public class Category {
    @Id
    @SequenceGenerator(name = "categoryIdSeqGen", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "categoryIdSeqGen")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Event> events;
}
