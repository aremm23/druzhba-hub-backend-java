package by.artsem.druzhbahub.security.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class EmailTextService {
    private final String BODY_TEXT = "Please click the link to confirm your email: ";
    private final String SUBJECT_TEXT = "Confirm your email";
    private final String LINK = "http://localhost:8080/api/register/confirm?token=";

    private String subject;
    private String body;

    public void createEmailMessage(String token) {
        body = BODY_TEXT + LINK + token;
        subject = SUBJECT_TEXT;
    }
}
