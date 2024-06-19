package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.dto.image.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService extends EntityService {
    void uploadProfileImage(MultipartFile file, Long profileId);
    void uploadEventImage(MultipartFile file, Long profileId);
    List<ImageResponseDto> getProfileImageUrls(Long profileId);
    List<ImageResponseDto> getEventImages(Long eventId);
    void deleteProfileImage(Long imageId);
    void deleteEventImage(Long imageId);
}
