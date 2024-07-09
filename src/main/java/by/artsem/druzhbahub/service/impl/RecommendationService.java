package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.model.Post;
import by.artsem.druzhbahub.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final PostRepository postRepository;

    public List<Post> getRecommendedPostsByProfileId(Long profileId) {
        //TODO sort with user location and age
        LocalDateTime now = LocalDateTime.now();
        List<Post> allPosts = postRepository.findAll();

        List<Post> filteredPosts = allPosts.stream()
                .filter(post -> post.getEvent().getStartAt().isAfter(now))
                .filter(post -> !post.getProfile().getId().equals(profileId))
                .toList();

        List<Post> sortedPosts = filteredPosts.stream()
                .sorted(Comparator.comparing((Post post) -> post.getProfile().getRate(), Comparator.reverseOrder())
                        .thenComparing(post -> post.getEvent().getStartAt()))
                .limit(30)
                .collect(Collectors.toList());

        return sortedPosts;
    }
}
