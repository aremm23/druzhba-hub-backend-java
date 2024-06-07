package by.artsem.druzhbahub.security.service;

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

    public ConfirmationToken createToken(Account account) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(UUID.randomUUID().toString());
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpireAt(LocalDateTime.now().plusHours(1));
        confirmationToken.setAccount(account);
        confirmationTokenRepository.save(confirmationToken);
        return confirmationToken;
    }

    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(
                () -> new RuntimeException() //TODO
        );
    }

    public boolean isExpire(ConfirmationToken confirmationToken) {
        return confirmationToken.getExpireAt().isBefore(LocalDateTime.now());
    }
}
