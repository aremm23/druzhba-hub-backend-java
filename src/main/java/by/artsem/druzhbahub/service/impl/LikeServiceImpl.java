package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.model.Like;
import by.artsem.druzhbahub.repository.LikeRepository;
import by.artsem.druzhbahub.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public List<Like> getAllByProfileId(Long profileId) {
        return likeRepository.findAllByProfileId(profileId);
    }

    @Override
    public List<Like> getAllByPostId(Long postId) {
        return likeRepository.findAllByPostId(postId);
    }

    @Override
    @Transactional
    public Like create(Like like) {
        like.setCreatedAt(LocalDateTime.now());
        return likeRepository.save(like);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();
    }
}
