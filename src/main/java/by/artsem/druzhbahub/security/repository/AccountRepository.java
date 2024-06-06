package by.artsem.druzhbahub.security.repository;

import by.artsem.druzhbahub.security.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
