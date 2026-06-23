package org.kushPmakwana.StudentManagement.utils;

import org.kushPmakwana.StudentManagement.enums.Role;
import org.kushPmakwana.StudentManagement.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Utils {
    public static Optional<UserPrincipal> getPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal)){
            return Optional.empty();
        }

        return Optional.of((UserPrincipal) authentication.getPrincipal());
    }

    public static Optional<UserPrincipal> getAuthenticatedAdmin(){
        return getPrincipal().filter(p -> Role.ADMIN.equals(p.getRole()));
    }
}
