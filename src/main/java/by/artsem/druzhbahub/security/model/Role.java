package by.artsem.druzhbahub.security.model;

import java.util.Arrays;

public enum Role {
    ADMIN, USER;

    public static boolean isExist(String role) {
        return Arrays.stream(Role.values())
                .anyMatch(r -> r.name().equalsIgnoreCase(role));
    }
}
