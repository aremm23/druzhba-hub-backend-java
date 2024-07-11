package by.artsem.druzhbahub.security.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
@Getter
public class EmailTextService {

    private static final String BODY_TEXT = "Чтоб закончить процесс регистрации пожалуйста подтвердите почту перейдя по ссылке: ";

    private static final String SUBJECT_TEXT = "Подтвердите почту";

    @Value("${email.confirm.link}")
    private String link;

    private String subject;

    private String body;

    public void createEmailMessage(String token) {
        if (link == null || link.isEmpty()) {
            throw new IllegalStateException("Confirmation link is not configured");
        }
        subject = SUBJECT_TEXT;
        body = extractHtml(token);
    }

    private String extractHtml(String token) {
        ClassPathResource resource = new ClassPathResource("static/email_confirm_message.html");
        try (InputStream inputStream = resource.getInputStream()) {
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"))
                    .replace("${confirmLink}", link)
                    .replace("${token}", token);
        } catch (IOException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
