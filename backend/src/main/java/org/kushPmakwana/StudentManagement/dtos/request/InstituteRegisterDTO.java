package org.kushPmakwana.StudentManagement.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InstituteRegisterDTO {
    // Tenant register information
    @NotBlank
    private String collegeName;

    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotBlank
    private String pinCode;

    @NotBlank
    private String aisheCode;

    // User fields
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must consist minimum 6 character")
    private String password;

    // Admin related fields
    @NotBlank
    private String adminName;

    @NotBlank
    private String adminCode;

    @NotBlank
    @Size(min = 10, max = 10, message = "enter the valid phone number")
    private String phoneNumber;

}
