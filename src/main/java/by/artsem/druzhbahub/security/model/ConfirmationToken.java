package by.artsem.druzhbahub.security.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString()
@EqualsAndHashCode()
@Builder
@AllArgsConstructor
@Entity
@Table(name = "confirm_token")
@NoArgsConstructor
public class ConfirmationToken {
    @Id
    @SequenceGenerator(name = "tokenIdSeqGen", sequenceName = "confirm_token_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "tokenIdSeqGen")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;

}
