package org.kushPmakwana.StudentManagement.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email field cannot be blank")
    private String email;

    @NotBlank(message = "Password field cannot be blank")
    private String password;
}
