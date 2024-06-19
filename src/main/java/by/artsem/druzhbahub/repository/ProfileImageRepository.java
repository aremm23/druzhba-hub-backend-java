package by.artsem.druzhbahub.repository;

import by.artsem.druzhbahub.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    List<ProfileImage> findByProfileId(Long profileId);

    @Query("SELECT COUNT(pi) FROM ProfileImage pi WHERE pi.profile.id = :profileId")
    Integer countImagesByProfileId(@Param("profileId") Long profileId);
}
