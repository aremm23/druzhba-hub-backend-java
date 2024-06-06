package by.artsem.druzhbahub.model;

import by.artsem.druzhbahub.security.model.Account;
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
@Table(name = "profile")
@NoArgsConstructor
public class Profile {
    @Id
    @SequenceGenerator(name = "profileIdSeqGen", sequenceName = "profile_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "profileIdSeqGen")
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Column(nullable = false, unique = true)
    private String username;

    private Integer rate;

    @Column(length = 350)
    private String selfSummary;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<ProfileImage> profileImages;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "first_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "second_profile_id"))
    private List<Profile> friends;
}
