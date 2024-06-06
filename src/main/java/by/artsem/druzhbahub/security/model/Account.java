package by.artsem.druzhbahub.security.model;

import by.artsem.druzhbahub.model.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString()
@EqualsAndHashCode()
@Builder
@AllArgsConstructor
@Entity
@Table(name = "account")
@NoArgsConstructor
public class Account {
    @Id
    @SequenceGenerator(name = "accountIdSeqGen", sequenceName = "account_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "accountIdSeqGen")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_email_confirmed")
    private boolean isEmailConfirmed;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Profile profile;


}
