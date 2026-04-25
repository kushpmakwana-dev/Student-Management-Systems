package org.kushPmakwana.StudentManagement.dto.userDto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.kushPmakwana.StudentManagement.enums.Role;

@Data
public class UserRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Size(max = 10, min = 10)
    private String phone;

    private Role role;

    private String bloodGroup;

    private String gender;
}
