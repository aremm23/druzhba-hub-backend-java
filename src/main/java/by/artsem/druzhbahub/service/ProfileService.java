package by.artsem.druzhbahub.service;

import by.artsem.druzhbahub.model.Profile;
import by.artsem.druzhbahub.security.model.Account;

public interface ProfileService extends CRUDService<Profile, Long>{
    Profile createEmptyProfile(String username, Account account);
}
