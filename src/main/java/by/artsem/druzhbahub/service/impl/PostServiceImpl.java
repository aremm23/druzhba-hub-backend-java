package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Post;
import by.artsem.druzhbahub.repository.PostRepository;
import by.artsem.druzhbahub.service.EventService;
import by.artsem.druzhbahub.service.PostService;
import by.artsem.druzhbahub.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final EventService eventService;
    private final ProfileService profileService;

    @Override
    public Post create(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Post with id %d not found".formatted(id))
        );
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post update(Long id, Post updatedPost) {
        Post existingPost = postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Post with id %d not found".formatted(id))
        );
        existingPost.setSummary(updatedPost.getSummary());
        existingPost.setEvent(updatedPost.getEvent());
        existingPost.setUpdatedAt(LocalDateTime.now());
        existingPost.setLikes(updatedPost.getLikes());
        return postRepository.save(existingPost);
    }

    @Override
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Post with id %d not found".formatted(id))
        );
        postRepository.delete(post);
    }

    @Override
    public Post updateSummary(Long id, String summary) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Post with id %d not found".formatted(id))
        );
        post.setSummary(summary);
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Post updateEvent(Long id, Long eventId) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Post with id %d not found".formatted(id))
        );
        post.setEvent(eventService.getById(eventId));
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }
}
