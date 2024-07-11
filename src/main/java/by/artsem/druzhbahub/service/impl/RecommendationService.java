package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.model.Post;
import by.artsem.druzhbahub.repository.PostRepository;
import by.artsem.druzhbahub.security.service.AccountService;
import by.artsem.druzhbahub.service.ProfileService;
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

    private final AccountService accountService;

    private final ProfileService profileService;

    public List<Post> getRecommendedPostsByProfileId(Long profileId) {
        LocalDateTime now = LocalDateTime.now();
        List<Post> allPosts = postRepository.findAll();

        List<Post> filteredPosts = allPosts.stream()
                .filter(post -> post.getEvent().getStartAt().isAfter(now))
                .filter(post -> !post.getProfile().getId().equals(profileId))
                .toList();

        return filteredPosts.stream()
                .sorted(Comparator.comparing((Post post) -> post.getProfile().getRate(), Comparator.reverseOrder())
                        .thenComparing(this::compareLocation)
                        .thenComparing(post -> post.getProfile().getAge())
                        .thenComparing(post -> post.getEvent().getStartAt()))
                .limit(30)
                .collect(Collectors.toList());
    }

    private int compareLocation(Post post) {
        return distanceMatrixService.getDistanceValue(post.getEvent().getLocation(), getCurrentProfileLocation());
    }

    private String getCurrentProfileLocation() {
        return profileService.getById(accountService.getCurrentUser().getId()).getPlace();
    }
}
