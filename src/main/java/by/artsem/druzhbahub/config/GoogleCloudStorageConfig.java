package by.artsem.druzhbahub.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class GoogleCloudStorageConfig {
    @Value("${gcp.project-id}")
    private String projectId;

    @Value("${gcp.credentials.file-path}")
    private String credentialsFilePath;

    @Bean
    public Storage googleCloudStorage() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsFilePath));
        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(credentials)
                .build().getService();
    }
}
