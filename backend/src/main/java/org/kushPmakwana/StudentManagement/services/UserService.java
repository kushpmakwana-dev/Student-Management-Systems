package org.kushPmakwana.StudentManagement.services;

import lombok.RequiredArgsConstructor;
import org.kushPmakwana.StudentManagement.dtos.request.user.UserRequestDTO;
import org.kushPmakwana.StudentManagement.dtos.response.user.UserResponseDTO;
import org.kushPmakwana.StudentManagement.models.User;
import org.kushPmakwana.StudentManagement.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO addUser(UserRequestDTO request){

        if(userRepository.existByEmail(request.getEmail())){
            throw new RuntimeException("User Already Exists");
        }

        User newUser = toEntity(request);
        User savedUser = userRepository.save(newUser);
        return toDTO(savedUser);
    }

    public User toEntity(UserRequestDTO request){
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .enabled(true)
                .build();
    }

    public UserResponseDTO toDTO(User user){
        return UserResponseDTO.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
