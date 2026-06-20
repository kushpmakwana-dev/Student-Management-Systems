package org.kushPmakwana.StudentManagement.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.kushPmakwana.StudentManagement.enums.Role;
import org.kushPmakwana.StudentManagement.security.users.AdminUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserPrincipal implements UserDetails {

    private final String email;
    private final Role role;

    private final Long adminId;

    private final AdminUser adminUser;

    public UserPrincipal(
            String email,
            Role role,
            Long adminId,
            AdminUser adminUser
    ){
        this.email = email;
        this.role = role;
        this.adminId = adminId;
        this.adminUser = adminUser;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return "";
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
