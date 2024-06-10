package by.artsem.druzhbahub.security.repository;

import by.artsem.druzhbahub.security.model.ConfirmationToken;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);

    boolean existsByToken(String token);
}
