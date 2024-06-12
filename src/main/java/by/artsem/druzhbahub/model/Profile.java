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

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private Account account;

    @Column(nullable = false, unique = true)
    private String username;

    private Integer rate;

    private LocalDateTime updatedAt;

    @Column(length = 350)
    private String selfSummary;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<ProfileImage> profileImages;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "profileFrom", cascade = CascadeType.ALL)
    private List<Review> reviewsFrom;

    @OneToMany(mappedBy = "profileTo", cascade = CascadeType.ALL)
    private List<Review> reviewsTo;

    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "first_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "second_profile_id"))
    private List<Profile> friends;
}
