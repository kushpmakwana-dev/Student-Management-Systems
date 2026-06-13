package org.kushPmakwana.StudentManagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tenant")
public class InstituteRegisterController {
    private final TenantService tenantService;

}
