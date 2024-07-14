package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Post;
import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.repository.PostRepository;
import by.artsem.druzhbahub.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final DistanceMatrixService distanceMatrixService;

    private final PostRepository postRepository;

    private final ProfileRepository profileRepository;

    private String currentProfileLocation;

    public List<Post> getRecommendedPostsByProfileId(Long profileId) {
        Profile currentProfile = getProfile(profileId);
        currentProfileLocation = currentProfile.getPlace();
        LocalDateTime now = LocalDateTime.now();
        List<Post> allPosts = postRepository.findAll();
        List<Post> filteredPosts = allPosts.stream()
                .filter(post -> post.getEvent().getStartAt().isAfter(now))
                .filter(post -> !post.getProfile().getId().equals(profileId))
                .toList();

        return filteredPosts.stream()
                .sorted(Comparator.comparing((Post post) -> post.getProfile().getRate(), Comparator.reverseOrder())
                        .thenComparing(this::comparePostLocation)
                        .thenComparing(post -> post.getProfile().getAge())
                        .thenComparing(post -> post.getEvent().getStartAt()))
                .limit(30)
                .collect(Collectors.toList());
    }

    public List<Profile> getRecommendedProfilesByProfileId(Long profileId) {
        Profile currentProfile = getProfile(profileId);
        currentProfileLocation = currentProfile.getPlace();
        List<Profile> profiles = profileRepository.findProfilesWithinAgeRange(currentProfile.getAge(), profileId);
        List<Profile> filteredProfiles = profiles.stream()
                .filter(profile -> !profile.getId().equals(profileId))
                .toList();

        return filteredProfiles.stream()
                .sorted(Comparator.comparing(this::compareProfileLocation)
                        .thenComparing(Profile::getRate, Comparator.reverseOrder()))
                .limit(6)
                .collect(Collectors.toList());
    }

    private Profile getProfile(Long profileId) {
        return profileRepository.findById(profileId).orElseThrow(
                () -> new DataNotFoundedException("Profile not found")
        );
    }

    private int comparePostLocation(Post post) {
        return distanceMatrixService.getDistanceValue(post.getEvent().getLocation(), currentProfileLocation);
    }

    private int compareProfileLocation(Profile profile) {
        return distanceMatrixService.getDistanceValue(profile.getPlace(), currentProfileLocation);
    }
}
