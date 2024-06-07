package by.artsem.druzhbahub.security.model.dto.mapper;

import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.model.Role;
import by.artsem.druzhbahub.security.model.dto.RegistrationRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account mapDtoToEntity(RegistrationRequestDTO dto) {
        return Account.builder()
                .email(dto.getEmail())
                .role(Role.valueOf(dto.getRole()))
                .build();
    }
}
