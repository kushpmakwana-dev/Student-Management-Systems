package org.kushPmakwana.StudentManagement.services;

import lombok.RequiredArgsConstructor;
import org.kushPmakwana.StudentManagement.dtos.update.AdminUpdateDTO;
import org.kushPmakwana.StudentManagement.exceptions.AdminNotFoundException;
import org.kushPmakwana.StudentManagement.exceptions.AlreadyFoundException;
import org.kushPmakwana.StudentManagement.exceptions.UserAlreadyExist;
import org.kushPmakwana.StudentManagement.exceptions.UserDisabledException;
import org.kushPmakwana.StudentManagement.models.Admin;
import org.kushPmakwana.StudentManagement.models.User;
import org.kushPmakwana.StudentManagement.repository.AdminRepository;
import org.kushPmakwana.StudentManagement.repository.UserRepository;
import org.kushPmakwana.StudentManagement.utils.Utils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public void update(AdminUpdateDTO updateDTO){
        var loggedInUser = Utils.getAuthenticatedAdmin()
                .orElseThrow(()-> new AdminNotFoundException("Admin not found"));

        Admin admin = adminRepository.findById(loggedInUser.getAdminUser().id())
                .orElseThrow(() -> new AdminNotFoundException("Admin not registered"));

        User user = admin.getUser();

        if(!user.isEnabled()){
            throw new UserDisabledException("User is disabled");
        }

        if(updateDTO.email() != null && userRepository.existsByEmail(updateDTO.email())){
            throw new UserAlreadyExist("Email Id " + updateDTO.email() + " already in use");
        }

        if(updateDTO.phoneNumber() != null && adminRepository.existsByPhoneNumber(updateDTO.phoneNumber())){
            throw new AlreadyFoundException("Phone number " + updateDTO.phoneNumber() + " already in use");
        }

        if(updateDTO.password() != null){
            if(updateDTO.password().equals(user.getPassword())){
                throw new AlreadyFoundException("cannot use the same password");
            }
        }

        // If user related fields are not null then update those
        user.setPassword(updateDTO.password());
        user.setEmail(updateDTO.email());

        userRepository.save(user);

        // Admin related fields update over here
        admin.setAdminCode(updateDTO.adminCode());
        admin.setAdminName(updateDTO.adminName());
        admin.setPhoneNumber(updateDTO.phoneNumber());

        adminRepository.save(admin);

    }

}
