package org.kushPmakwana.StudentManagement.controller;

import lombok.RequiredArgsConstructor;
import org.kushPmakwana.StudentManagement.dtos.update.AdminUpdateDTO;
import org.kushPmakwana.StudentManagement.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> update(
            @RequestBody AdminUpdateDTO request
    ){
        service.update(request);
        return ResponseEntity.ok("Updated Successfully");
    }
}
