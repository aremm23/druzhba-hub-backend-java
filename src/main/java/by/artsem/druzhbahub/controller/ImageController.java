package by.artsem.druzhbahub.controller;

import by.artsem.druzhbahub.model.dto.image.ImageResponseDto;
import by.artsem.druzhbahub.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/profile/{profileId}")
    public ResponseEntity<HttpStatus> uploadProfileImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable("profileId") Long profileId
    ) {
        imageService.uploadProfileImage(file, profileId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<ImageResponseDto>> getProfileImageUrls(@PathVariable Long profileId) {
        List<ImageResponseDto> imageDtos = imageService.getProfileImageUrls(profileId);
        return ResponseEntity.ok(imageDtos);
    }

    @PostMapping("/event/{eventId}")
    public ResponseEntity<HttpStatus> uploadEventImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable("eventId") Long eventId
    ) {
        imageService.uploadEventImage(file, eventId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<ImageResponseDto>> getEventImageUrls(@PathVariable Long eventId) {
        List<ImageResponseDto> imageDtos = imageService.getEventImages(eventId);
        return ResponseEntity.ok(imageDtos);
    }

    @DeleteMapping("/profile/{imageId}")
    public ResponseEntity<Void> deleteProfileImage(@PathVariable Long imageId) {
        imageService.deleteProfileImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/event/{imageId}")
    public ResponseEntity<HttpStatus> deleteEventImage(@PathVariable Long imageId) {
        imageService.deleteEventImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}