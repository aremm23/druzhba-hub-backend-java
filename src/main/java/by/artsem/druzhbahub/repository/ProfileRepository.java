package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByUsername(String username);
}
