package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.InvalidFileException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileVerifyingService {
    public void isImageUnderTwoMb(MultipartFile file) {
        if (file.isEmpty() || file.getSize() > 2 * 1024 * 1024) { // 2MB limit
            throw new InvalidFileException("File is empty or exceeds the size limit!");
        }

        String contentType = file.getContentType();
        if (!isImage(contentType)) {
            throw new InvalidFileException("Only image files are allowed!");
        }

        if (!isValidImage(file)) {
            throw new InvalidFileException("File is not a valid image!");
        }
    }

    private boolean isImage(String contentType) {
        return contentType != null && (
                contentType.equals(MediaType.IMAGE_JPEG_VALUE) ||
                        contentType.equals(MediaType.IMAGE_PNG_VALUE)
        );
    }

    private boolean isValidImage(MultipartFile file) {
        try (InputStream input = file.getInputStream()) {
            BufferedImage image = ImageIO.read(input);
            return image != null;
        } catch (IOException e) {
            throw new InvalidFileException("File is not a valid image!");
        }
    }
}
