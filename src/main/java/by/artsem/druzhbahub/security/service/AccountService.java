package by.artsem.druzhbahub.security.service;

import by.artsem.druzhbahub.exception.DataFormatException;
import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.exception.EmailConfirmedException;
import by.artsem.druzhbahub.exception.TokenExpireException;
import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.model.ConfirmationToken;
import by.artsem.druzhbahub.security.model.Role;
import by.artsem.druzhbahub.security.model.dto.EmailUpdateRequestDTO;
import by.artsem.druzhbahub.security.model.dto.PasswordUpdateRequestDTO;
import by.artsem.druzhbahub.security.model.dto.RegistrationRequestDTO;
import by.artsem.druzhbahub.security.model.dto.RoleUpdateRequestDTO;
import by.artsem.druzhbahub.security.model.dto.mapper.AccountMapper;
import by.artsem.druzhbahub.security.repository.AccountRepository;
import by.artsem.druzhbahub.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProfileService profileService;

    private final ConfirmationTokenService confirmationTokenService;

    private final AccountMapper accountMapper;

    public Account createAccountAndProfile(RegistrationRequestDTO dto) {
        Account account = mapDtoToEntityWithAllFields(dto);
        return accountRepository.save(account);
    }

    private Account mapDtoToEntityWithAllFields(RegistrationRequestDTO dto) {
        Account account = accountMapper.mapDtoToEntity(dto);
        account.setPassword(passwordEncoder.encode(dto.getPassword()));
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setEmailConfirmed(false);
        account.setProfile(profileService.createEmptyProfile(dto.getUsername(), account));
        return account;
    }

    public void confirmEmail(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token);
        if (confirmationTokenService.isExpire(confirmationToken)) {
            throw new TokenExpireException("Expired token");
        }
        Account account = confirmationToken.getAccount();
        if (account.isEmailConfirmed()) {
            throw new EmailConfirmedException("Email is already confirmed");
        }
        account.setEmailConfirmed(true);
        accountRepository.save(account);
    }

    public void updatePassword(Long id, PasswordUpdateRequestDTO passwordUpdateRequestDTO) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("User not founded")
        );
        if (!passwordEncoder.matches(passwordUpdateRequestDTO.getCurrentPassword(), account.getPassword())) {
            throw new AccessDeniedException("Wrong password");
        }
        if (passwordEncoder.matches(passwordUpdateRequestDTO.getUpdatedPassword(), account.getPassword())) {
            throw new DataFormatException("Passwords are identical");
        }
        account.setPassword(passwordEncoder.encode(passwordUpdateRequestDTO.getUpdatedPassword()));
        accountRepository.save(account);
    }

    public void updateEmail(Long id, EmailUpdateRequestDTO emailUpdateRequestDTO) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("User not founded")
        );
        if (accountRepository.existsByEmail(emailUpdateRequestDTO.getEmail())) {
            throw new DataFormatException("Email is already exist");
        }
        account.setEmail(emailUpdateRequestDTO.getEmail());
        accountRepository.save(account);
    }

    public void updateRole(Long id, RoleUpdateRequestDTO roleUpdateRequestDTO) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("User not founded")
        );
        if (!Role.isExist(roleUpdateRequestDTO.getRole())) {
            throw new DataNotFoundedException("Role not founded");
        }
        account.setRole(Role.valueOf(roleUpdateRequestDTO.getRole()));
        accountRepository.save(account);
    }

    public List<Account> findAll() {//TODO make ResponseDTO
        return accountRepository.findAll();
    }

    public Account findById(Long id) {//TODO make ResponseDTO
        return accountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("User not founded")
        );
    }

    public void delete(Long id) {
        accountRepository.delete(
                accountRepository.findById(id).orElseThrow(
                        () -> new DataNotFoundedException("User not founded")
                )
        );
    }

}
