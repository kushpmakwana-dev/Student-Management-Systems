package org.kushPmakwana.StudentManagement.dtos.update;

public record AdminUpdateDTO(
        String email,
    String password,
    String adminName,
    String adminCode,
    String phoneNumber
) {
}
