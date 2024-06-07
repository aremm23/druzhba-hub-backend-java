package by.artsem.druzhbahub.security.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class EmailTextService {

    private static final String BODY_TEXT = "Please click the link to confirm your email: ";

    private static final String SUBJECT_TEXT = "Confirm your email";

    @Value("${email.confirm.link}")
    private String link;

    private String subject;

    private String body;

    public void createEmailMessage(String token) {
        if (link == null || link.isEmpty()) {
            throw new IllegalStateException("Confirmation link is not configured");
        }
        body = BODY_TEXT + link + token;
        subject = SUBJECT_TEXT;
    }
}
