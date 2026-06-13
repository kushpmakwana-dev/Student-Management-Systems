package org.kushPmakwana.StudentManagement.controller;

import lombok.RequiredArgsConstructor;
import org.kushPmakwana.StudentManagement.dtos.request.InstituteRegisterDTO;
import org.kushPmakwana.StudentManagement.services.InstituteRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class InstituteRegisterController {

    private final InstituteRegisterService service;

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody InstituteRegisterDTO request
    ){
        service.create(request);
        return ResponseEntity.ok("Registration Successful");
    }
}
