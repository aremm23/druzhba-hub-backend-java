package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.security.model.Account;

import java.util.List;

public interface ProfileService extends CRUDService<Profile, Long> {//TODO add images

    Profile createEmptyProfile(String username, Account account);

    Profile updateSelfSummary(Long id, String selfSummary);

    Profile updateUsername(Long id, String username);

    void updateRating(Long id, double averageGrade);

    List<Profile> getRecommended(Long profileId);
}
