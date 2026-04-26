package org.kushPmakwana.StudentManagement.controller;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kushPmakwana.StudentManagement.dto.userDto.UserRequest;
import org.kushPmakwana.StudentManagement.dto.userDto.UserResponse;
import org.kushPmakwana.StudentManagement.dto.userDto.responseDto.ResponseDTO;
import org.kushPmakwana.StudentManagement.model.User;
import org.kushPmakwana.StudentManagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Data
public class UserController {
    private final UserService service;

    @PostMapping("/addUser")
    public ResponseEntity<ResponseDTO<UserResponse>> addUser(@RequestBody @Valid UserRequest req){
        ResponseDTO<UserResponse> resDTO = new ResponseDTO<>();
        UserResponse user = service.addUser(req);
        resDTO.setMessage("User Created Successfully");
        resDTO.setStatus(HttpStatus.CREATED.value());
        resDTO.setData(user);
        return new ResponseEntity<ResponseDTO<UserResponse>>(resDTO, HttpStatus.CREATED);
    }
}
