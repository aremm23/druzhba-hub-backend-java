package by.artsem.druzhbahub.controller;

import by.artsem.druzhbahub.model.Like;
import by.artsem.druzhbahub.model.dto.like.CreateLikeRequestDto;
import by.artsem.druzhbahub.model.dto.like.LikeDeleteDto;
import by.artsem.druzhbahub.model.dto.like.LikeResponseDto;
import by.artsem.druzhbahub.model.dto.like.mapper.LikeMapper;
import by.artsem.druzhbahub.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<HttpStatus> createLike(@RequestBody @Valid CreateLikeRequestDto dto) {
        likeService.create(LikeMapper.mapToEntity(dto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLike(LikeDeleteDto likeDeleteDto) {
        likeService.deleteByPostAndProfileId(likeDeleteDto.getPostId(), likeDeleteDto.getProfileId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<LikeResponseDto>> getAllLikes() {
        List<Like> likes = likeService.getAll();
        return new ResponseEntity<>(
                likes.stream().map(LikeMapper::mapToDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<List<LikeResponseDto>> getAllByProfile(@PathVariable Long id) {
        List<Like> likes = likeService.getAllByProfileId(id);
        return new ResponseEntity<>(
                likes.stream().map(LikeMapper::mapToDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<LikeResponseDto>> getAllByPost(@PathVariable Long id) {
        List<Like> likes = likeService.getAllByPostId(id);
        return new ResponseEntity<>(
                likes.stream().map(LikeMapper::mapToDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

}
