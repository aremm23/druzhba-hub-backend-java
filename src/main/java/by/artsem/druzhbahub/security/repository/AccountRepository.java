package by.artsem.druzhbahub.security.repository;

import by.artsem.druzhbahub.security.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);

    @Query("SELECT a.isEmailConfirmed FROM Account a WHERE a.email = :email")
    boolean isEmailConfirmedByEmail(@Param("email") String email);

    @Query("SELECT a.isEmailConfirmed FROM Account a WHERE a.id = :id")
    boolean isEmailConfirmedById(@Param("id") Long id);
}
