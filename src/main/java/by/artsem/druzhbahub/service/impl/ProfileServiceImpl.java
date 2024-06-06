package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.repository.ProfileRepository;
import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.service.ProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;


@Service
public class ProfileServiceImpl extends CRUDServiceImpl<Profile, Long> implements ProfileService {
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        super(profileRepository);
    }

    @Override
    public Profile createEmptyProfile(String username, Account account) {
        return repository.save(
                Profile.builder()
                        .account(account)
                        .profileImages(Collections.emptyList())
                        .rate(0)
                        .friends(Collections.emptyList())
                        .likes(Collections.emptyList())
                        .posts(Collections.emptyList())
                        .selfSummary("")
                        .username(username)
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}
