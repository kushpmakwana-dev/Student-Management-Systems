package org.kushPmakwana.StudentManagement.dtos.response.user;

import lombok.Builder;
import lombok.Data;
import org.kushPmakwana.StudentManagement.enums.Role;

@Data
@Builder
public class UserResponseDTO {
    private String email;
    private Role role;
}
