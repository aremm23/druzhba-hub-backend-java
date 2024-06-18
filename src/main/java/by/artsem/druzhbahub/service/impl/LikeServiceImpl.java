package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotCreatedException;
import by.artsem.druzhbahub.exception.DataNotFoundedException;
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
        if(likeRepository.existsByProfileAndPost(like.getProfile(), like.getPost())) {
            throw new DataNotCreatedException("Like is already exist");
        }
        like.setCreatedAt(LocalDateTime.now());
        return likeRepository.save(like);
    }

    @Override
    @Transactional
    public void deleteByPostAndProfileId(Long postId, Long profileId) {
        likeRepository.delete(likeRepository.findByProfileIdAndPostId(profileId, postId).orElseThrow(
                () -> new DataNotFoundedException("Like not found")
        ));
    }

    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();
    }
}
