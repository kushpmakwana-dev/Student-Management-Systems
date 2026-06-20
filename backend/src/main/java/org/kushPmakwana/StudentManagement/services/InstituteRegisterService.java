package org.kushPmakwana.StudentManagement.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.kushPmakwana.StudentManagement.dtos.request.InstituteRegisterDTO;
import org.kushPmakwana.StudentManagement.enums.Role;
import org.kushPmakwana.StudentManagement.exceptions.AlreadyFoundException;
import org.kushPmakwana.StudentManagement.exceptions.TenantAlreadyExists;
import org.kushPmakwana.StudentManagement.exceptions.UserAlreadyExist;
import org.kushPmakwana.StudentManagement.models.Admin;
import org.kushPmakwana.StudentManagement.models.Tenant;
import org.kushPmakwana.StudentManagement.models.User;
import org.kushPmakwana.StudentManagement.repository.AdminRepository;
import org.kushPmakwana.StudentManagement.repository.TenantRepository;
import org.kushPmakwana.StudentManagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;

@Service
@RequiredArgsConstructor
public class InstituteRegisterService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(InstituteRegisterDTO request){

        if(tenantRepository.existsByAisheCode(request.getAisheCode())){
            throw new TenantAlreadyExists("Aishe Code must be unique");
        }

        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExist("User with email " + request.getEmail() + " already exists");
        }

        if(adminRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new AlreadyFoundException("Phone Number already exists ");
        }

        // first add the Tenant information
        Tenant tenant = new Tenant();
        tenant.setCollegeName(request.getCollegeName());
        tenant.setState(request.getState());
        tenant.setCity(request.getCity());
        tenant.setPinCode(request.getPinCode());
        tenant.setAisheCode(request.getAisheCode());

        Tenant savedTenant = tenantRepository.save(tenant);

        // Add user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);

        User newUser = userRepository.save(user);

        // Add Admin
        Admin admin = new Admin();
        admin.setAdminName(request.getAdminName());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setUser(newUser);
        admin.setTenant(savedTenant);

        adminRepository.save(admin);
    }
}
