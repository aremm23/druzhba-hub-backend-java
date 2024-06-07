package by.artsem.druzhbahub.controller;


import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile createdProfile = profileService.create(profile);
        return ResponseEntity.ok(createdProfile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Optional<Profile> profile = profileService.getById(id);
        return profile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getAll();
        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        Profile updatedProfile = profileService.update(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}