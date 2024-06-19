package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.EventImage;
import by.artsem.druzhbahub.model.ProfileImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService extends EntityService {
    ProfileImage uploadProfileImage(MultipartFile file, Long profileId);
    EventImage uploadEventImage(MultipartFile file, Long profileId);
    List<String> getProfileImageUrls(Long profileId);
    List<String> getEventImageUrls(Long eventId);
}
