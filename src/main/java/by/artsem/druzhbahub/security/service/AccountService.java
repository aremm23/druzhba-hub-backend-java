package by.artsem.druzhbahub.security.service;

import by.artsem.druzhbahub.exception.DataFormatException;
import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.exception.EmailConfirmedException;
import by.artsem.druzhbahub.exception.TokenExpireException;
import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.model.ConfirmationToken;
import by.artsem.druzhbahub.security.model.Role;
import by.artsem.druzhbahub.security.model.dto.*;
import by.artsem.druzhbahub.security.model.dto.mapper.AccountMapper;
import by.artsem.druzhbahub.security.repository.AccountRepository;
import by.artsem.druzhbahub.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final ProfileService profileService;

    private final ConfirmationTokenService confirmationTokenService;

    private final AccountMapper accountMapper;

    public Account createAccountAndProfile(RegistrationRequestDTO dto) {
        if (accountRepository.existsByEmail(dto.getEmail())) {
            throw new DataFormatException("Email is already exist");
        }
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
        account.setEmailConfirmed(false);
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

    public List<AccountResponseDTO> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(
                account ->
                        AccountResponseDTO.builder()
                                .email(account.getEmail())
                                .role(account.getRole().name())
                                .createdAt(account.getCreatedAt().toString())
                                .updatedAt(account.getUpdatedAt().toString())
                                .id(account.getId())
                                .build()
        ).toList();
    }

    public AccountResponseDTO findById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("User not founded")
        );
        return AccountResponseDTO.builder()
                .email(account.getEmail())
                .role(account.getRole().name())
                .createdAt(account.getCreatedAt().toString())
                .updatedAt(account.getUpdatedAt().toString())
                .id(account.getId())
                .build();
    }

    public JwtTokenResponseDto authenticate(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new DataNotFoundedException("Unknown email")
                );
        var jwtToken = jwtService.generateToken(account);
        return JwtTokenResponseDto.builder()
                .token(jwtToken)
                .id(account.getId())
                .build();
    }

    public void delete(Long id) {
        accountRepository.delete(
                accountRepository.findById(id).orElseThrow(
                        () -> new DataNotFoundedException("User not founded")
                )
        );
    }

}
