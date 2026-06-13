package org.kushPmakwana.StudentManagement.services;

import lombok.RequiredArgsConstructor;
import org.kushPmakwana.StudentManagement.dtos.request.InstituteRegisterDTO;
import org.kushPmakwana.StudentManagement.models.Admin;
import org.kushPmakwana.StudentManagement.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

}
