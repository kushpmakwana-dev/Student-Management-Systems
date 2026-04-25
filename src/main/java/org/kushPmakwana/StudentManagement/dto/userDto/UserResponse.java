package org.kushPmakwana.StudentManagement.dto.userDto;

import lombok.Data;
import org.kushPmakwana.StudentManagement.enums.Role;
@Data
public class UserResponse {
    private String email;
    private String phone;
    private Role role;
}
