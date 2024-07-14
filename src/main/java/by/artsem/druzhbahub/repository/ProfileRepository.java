package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByUsername(String username);

    @Query("SELECT p FROM Profile p WHERE p.age BETWEEN :age - 3 AND :age + 3 AND p.id <> :profileId")
    List<Profile> findProfilesWithinAgeRange(@Param("age") Integer age, @Param("profileId") Long profileId);

}
