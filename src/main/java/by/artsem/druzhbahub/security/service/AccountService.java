package by.artsem.druzhbahub.security.service;

import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.model.dto.RegistrationRequestDTO;
import by.artsem.druzhbahub.security.model.dto.mapper.AccountMapper;
import by.artsem.druzhbahub.security.repository.AccountRepository;
import by.artsem.druzhbahub.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AccountService {

    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;

    private ProfileService profileService;

    private AccountMapper accountMapper;

    public Account createAccountAndProfile(RegistrationRequestDTO dto) {
        Account account = toEntityAllFields(dto, dto.getPassword());
        return accountRepository.save(account);
    }

    private Account toEntityAllFields(RegistrationRequestDTO dto, String password) {
        Account account = accountMapper.mapDtoToEntity(dto);
        account.setPassword(passwordEncoder.encode(password));
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setEmailConfirmed(false);
        account.setProfile(profileService.createEmptyProfile(dto.getUsername(), account));
        return account;
    }

}
