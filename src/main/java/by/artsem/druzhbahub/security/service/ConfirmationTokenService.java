package by.artsem.druzhbahub.security.service;

import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.model.ConfirmationToken;
import by.artsem.druzhbahub.security.repository.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationToken create(Account account) {
        return confirmationTokenRepository.save(buildConfirmationToken(account));
    }

    private ConfirmationToken buildConfirmationToken(Account account) {
        return ConfirmationToken.builder()
                .token(UUID.randomUUID().toString())
                .account(account)
                .expireAt(LocalDateTime.now().plusHours(1))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(
                () -> new DataNotFoundedException("Token not found")
        );
    }

    public boolean isExpire(ConfirmationToken confirmationToken) {
        return confirmationToken.getExpireAt().isBefore(LocalDateTime.now());
    }
}
