package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataFormatException;
import by.artsem.druzhbahub.exception.DataNotCreatedException;
import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.repository.ProfileRepository;
import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.service.ProfileService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Profile createEmptyProfile(String username, Account account) {
        if (profileRepository.existsByUsername(username)) {
            throw new DataNotCreatedException("Username is already taken");
        }
        return profileRepository.save(
                Profile.builder()
                        .account(account)
                        .profileImages(Collections.emptyList())
                        .rate(0d)
                        .subscribers(Collections.emptyList())
                        .subscribeTo(Collections.emptyList())
                        .likes(Collections.emptyList())
                        .posts(Collections.emptyList())
                        .selfSummary("")
                        .username(username)
                        .reviewsFrom(Collections.emptyList())
                        .reviewsTo(Collections.emptyList())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    @Transactional
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
    @Transactional
    public Profile update(Long id, Profile updatedProfile) {
        Profile existingProfile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        parseUpdatedToExisted(updatedProfile, existingProfile);
        return profileRepository.save(existingProfile);
    }

    private void parseUpdatedToExisted(Profile updatedProfile, Profile existingProfile) {
        existingProfile.setUsername(updatedProfile.getUsername());
        existingProfile.setSelfSummary(updatedProfile.getSelfSummary());
        existingProfile.setRate(updatedProfile.getRate());
        existingProfile.setUpdatedAt(LocalDateTime.now());
        existingProfile.setProfileImages(updatedProfile.getProfileImages());
        existingProfile.setSubscribers(updatedProfile.getSubscribers());
        existingProfile.setLikes(updatedProfile.getLikes());
        existingProfile.setPosts(updatedProfile.getPosts());
        existingProfile.setReviewsFrom(updatedProfile.getReviewsFrom());
        existingProfile.setReviewsTo(updatedProfile.getReviewsTo());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        profileRepository.delete(profile);
    }

    @Override
    @Transactional
    public Profile updateSelfSummary(Long id, String selfSummary) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        profile.setSelfSummary(selfSummary);
        profile.setUpdatedAt(LocalDateTime.now());
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public Profile updateUsername(Long id, String username) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        if (profileRepository.existsByUsername(username)) {
            throw new DataFormatException("Username is already taken");
        }
        profile.setUsername(username);
        profile.setUpdatedAt(LocalDateTime.now());
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void updateRating(Long id, double averageGrade) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Profile with id %d not found".formatted(id))
        );
        profile.setRate(averageGrade);
        profile.setUpdatedAt(LocalDateTime.now());
        profileRepository.save(profile);
    }

}
