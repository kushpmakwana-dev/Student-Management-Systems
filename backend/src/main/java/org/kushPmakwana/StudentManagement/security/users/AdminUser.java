package org.kushPmakwana.StudentManagement.security.users;

public record AdminUser(
        Long id,
        String name,
        String email,
        String adminCode
) {
}
