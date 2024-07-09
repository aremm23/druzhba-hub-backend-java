package by.artsem.druzhbahub.controller;


import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.model.dto.image.ImageResponseDto;
import by.artsem.druzhbahub.model.dto.profile.*;
import by.artsem.druzhbahub.model.dto.profile.mapper.ProfileMapper;
import by.artsem.druzhbahub.service.ImageService;
import by.artsem.druzhbahub.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@CrossOrigin
public class ProfileController {

    private final ProfileService profileService;

    private final ImageService imageService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<HttpStatus> createProfile(@RequestBody @Valid ProfileCreateRequestDTO profileDto) {
        profileService.create(modelMapper.map(profileDto, Profile.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> updateProfile(
            @PathVariable Long id,
            @RequestBody @Valid ProfileUpdateRequestDTO profileDto
    ) {
        Profile updatedProfile = profileService.update(id, modelMapper.map(profileDto, Profile.class));
        return new ResponseEntity<>(
                ProfileMapper.mapToDto(updatedProfile),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/summary")
    public ResponseEntity<ProfileResponseDto> updateSelfSummary(
            @PathVariable Long id,
            @RequestBody @Valid ProfileUpdateSelfSummaryRequestDTO dto
    ) {
        Profile updatedProfile = profileService.updateSelfSummary(id, dto.getSelfSummary());
        return new ResponseEntity<>(
                ProfileMapper.mapToDto(updatedProfile),
                HttpStatus.OK
        );
    }


    @GetMapping("/{id}/summary")
    public ResponseEntity<ProfileSummaryResponseDto> selfSummary(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                ProfileSummaryResponseDto.builder()
                        .selfSummary(profileService.getById(id).getSelfSummary())
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<ProfileResponseDto> updateUsername(
            @PathVariable Long id,
            @RequestBody @Valid ProfileUpdateUsernameRequestDTO dto
    ) {
        Profile updatedProfile = profileService.updateUsername(id, dto.getUsername());
        return new ResponseEntity<>(
                ProfileMapper.mapToDto(updatedProfile),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProfile(@PathVariable Long id) {
        profileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> getProfileById(@PathVariable Long id) {
        Profile profile = profileService.getById(id);
        return new ResponseEntity<>(
                ProfileMapper.mapToDto(profile),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDto>> getAllProfiles() {
        List<Profile> profiles = profileService.getAll();
        return new ResponseEntity<>(
                profiles.stream().map(ProfileMapper::mapToDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/recommended/{profileId}")
    public ResponseEntity<List<ShortProfileResponseDto>> getRecommendedProfiles(@PathVariable Long profileId) {
        List<Profile> profiles = profileService.getRecommended(profileId);
        return new ResponseEntity<>(
                profiles.stream().map(profile -> {
                    List<ImageResponseDto> images = imageService.getProfileImageUrls(profile.getId());
                    if(!images.isEmpty())
                        return ProfileMapper.mapToShortDto(profile, images.get(images.size() - 1).getUrl());
                    else
                        return ProfileMapper.mapToShortDto(profile, "https://friconix.com/png/fi-cnluxx-anonymous-user-circle.png");
                }).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}