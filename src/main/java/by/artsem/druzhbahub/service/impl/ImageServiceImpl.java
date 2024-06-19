package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotCreatedException;
import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Event;
import by.artsem.druzhbahub.model.EventImage;
import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.model.ProfileImage;
import by.artsem.druzhbahub.model.dto.image.ImageResponseDto;
import by.artsem.druzhbahub.repository.EventImageRepository;
import by.artsem.druzhbahub.repository.EventRepository;
import by.artsem.druzhbahub.repository.ProfileImageRepository;
import by.artsem.druzhbahub.repository.ProfileRepository;
import by.artsem.druzhbahub.service.ImageService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final Storage storage;

    private final EventImageRepository eventImageRepository;

    private final ProfileImageRepository profileImageRepository;

    private final ProfileRepository profileRepository;

    private final EventRepository eventRepository;

    private final FileVerifyingService imageVerifyingService;

    @Value("${gcp.bucket-name}")
    private String bucketName;

    @Override
    public void uploadProfileImage(MultipartFile file, Long profileId) {
        if (!profileRepository.existsById(profileId)) {
            throw new DataNotFoundedException("Profile doesn't exist");
        }
        if (profileImageRepository.countImagesByProfileId(profileId) > 3) {
            throw new DataNotCreatedException("Can't upload more than 4 images");
        }
        String uniqueFileName = saveImageToGoogleCloudStorage(file);
        saveProfileImage(profileId, uniqueFileName);
    }

    private String saveImageToGoogleCloudStorage(MultipartFile file) {
        imageVerifyingService.isImageUnderTwoMb(file);
        String uniqueFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        BlobId blobId = BlobId.of(bucketName, uniqueFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .setContentDisposition("inline")
                .build();
        createInStorage(file, blobInfo);
        return uniqueFileName;
    }

    private void createInStorage(MultipartFile file, BlobInfo blobInfo) {
        try {
            storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            throw new DataNotCreatedException("Error with uploaded file. " + e.getMessage());
        }
    }

    private void saveProfileImage(Long profileId, String uniqueFileName) {
        ProfileImage profileImage = ProfileImage.builder()
                .profile(Profile.builder().id(profileId).build())
                .gcsFileName(uniqueFileName)
                .uploadTime(LocalDateTime.now())
                .build();
        profileImageRepository.save(profileImage);
    }

    @Override
    public List<ImageResponseDto> getProfileImageUrls(Long profileId) {
        List<ProfileImage> images = profileImageRepository.findByProfileId(profileId);
        return images.stream().map(
                        image -> ImageResponseDto.builder()
                                .id(image.getId())
                                .url(getImageUrl(image.getGcsFileName()))
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    public void uploadEventImage(MultipartFile file, Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new DataNotFoundedException("Event doesn't exist");
        }
        if (eventImageRepository.countImagesByEventId(eventId) > 3) {
            throw new DataNotCreatedException("Can't upload more than 3 images");
        }
        String uniqueFileName = saveImageToGoogleCloudStorage(file);
        saveEventImage(eventId, uniqueFileName);
    }

    private void saveEventImage(Long eventId, String uniqueFileName) {
        EventImage eventImage = EventImage.builder()
                .event(Event.builder().id(eventId).build())
                .gcsFileName(uniqueFileName)
                .uploadTime(LocalDateTime.now())
                .build();
        eventImageRepository.save(eventImage);
    }

    @Override
    public List<ImageResponseDto> getEventImages(Long eventId) {
        List<EventImage> images = eventImageRepository.findByEventId(eventId);
        return images.stream().map(
                image -> ImageResponseDto.builder()
                        .id(image.getId())
                        .url(getImageUrl(image.getGcsFileName()))
                        .build())
                .collect(Collectors.toList());
    }

    private String getImageUrl(String gcsFileName) {
        Blob blob = storage.get(BlobId.of(bucketName, gcsFileName));
        return blob.signUrl(1, TimeUnit.HOURS).toString();
    }
}

