package by.artsem.druzhbahub.controller;

import by.artsem.druzhbahub.model.EventImage;
import by.artsem.druzhbahub.model.ProfileImage;
import by.artsem.druzhbahub.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/profile/{profileId}")
    public ResponseEntity<String> uploadProfileImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable("profileId") Long profileId
    ) {
        ProfileImage profileImage = imageService.uploadProfileImage(file, profileId);
        return ResponseEntity.ok("Profile image uploaded with name: " + profileImage.getGcsFileName());
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<String>> getProfileImageUrls(@PathVariable Long profileId) {
        List<String> imageUrls = imageService.getProfileImageUrls(profileId);
        return ResponseEntity.ok(imageUrls);
    }

    @PostMapping("/event/{eventId}")
    public ResponseEntity<String> uploadEventImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable("eventId") Long eventId
    ) {
        EventImage eventImage = imageService.uploadEventImage(file, eventId);
        return ResponseEntity.ok("Profile image uploaded with name: " + eventImage.getGcsFileName());
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<String>> getEventImageUrls(@PathVariable Long eventId) {
        List<String> imageUrls = imageService.getEventImageUrls(eventId);
        return ResponseEntity.ok(imageUrls);
    }



}