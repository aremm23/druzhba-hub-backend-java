package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.repository.ProfileRepository;
import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.service.AccountService;
import by.artsem.druzhbahub.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public Profile createEmptyProfile(String username, Account account) {
        return profileRepository.save(
                Profile.builder()
                        .account(account)
                        .profileImages(Collections.emptyList())
                        .rate(0)
                        .friends(Collections.emptyList())
                        .likes(Collections.emptyList())
                        .posts(Collections.emptyList())
                        .selfSummary("")
                        .username(username)
                        .reviewsFrom(Collections.emptyList())
                        .reviewsTo(Collections.emptyList())
                        .build()
        );
    }

    @Override
    @Deprecated
    public Profile create(Profile profile) {
        profile.setUpdatedAt(LocalDateTime.now());
        return profileRepository.save(profile);
    }


    @Override
    public Profile getById(Long id) {
        return profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
    }

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile update(Long id, Profile updatedProfile) {
        Profile existingProfile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        existingProfile.setUsername(updatedProfile.getUsername());
        existingProfile.setSelfSummary(updatedProfile.getSelfSummary());
        existingProfile.setRate(updatedProfile.getRate());
        existingProfile.setUpdatedAt(LocalDateTime.now());
        existingProfile.setProfileImages(updatedProfile.getProfileImages());
        existingProfile.setFriends(updatedProfile.getFriends());
        existingProfile.setLikes(updatedProfile.getLikes());
        existingProfile.setPosts(updatedProfile.getPosts());
        existingProfile.setReviewsFrom(updatedProfile.getReviewsFrom());
        existingProfile.setReviewsTo(updatedProfile.getReviewsTo());
        return profileRepository.save(existingProfile);
    }

    @Override
    public void delete(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        profileRepository.delete(profile);
    }

    @Override
    public Profile updateSelfSummary(Long id, String selfSummary) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        profile.setSelfSummary(selfSummary);
        profile.setUpdatedAt(LocalDateTime.now());
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateUsername(Long id, String username) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        profile.setUsername(username);
        profile.setUpdatedAt(LocalDateTime.now());
        return profileRepository.save(profile);
    }
}
