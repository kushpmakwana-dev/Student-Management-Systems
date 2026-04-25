package org.kushPmakwana.StudentManagement.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.kushPmakwana.StudentManagement.dto.userDto.UserRequest;
import org.kushPmakwana.StudentManagement.dto.userDto.UserResponse;
import org.kushPmakwana.StudentManagement.exceptions.UserAlreadyExistsException;
import org.kushPmakwana.StudentManagement.model.User;
import org.kushPmakwana.StudentManagement.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse addUser(UserRequest req){

        if(userRepository.existsByEmail(req.getEmail())){
           throw new UserAlreadyExistsException("User Already exist");
        }

        User newUser = dtoToEntity(req);
        User savedUser = userRepository.save(newUser);

        return entityToDto(savedUser);
    }

    private User dtoToEntity(UserRequest req){
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setPhone(req.getPhone());
        user.setRole(req.getRole());
        user.setBloodGroup(req.getBloodGroup());
        user.setGender(req.getGender());
        return user;
    }

    private UserResponse entityToDto(User user){
        UserResponse res = new UserResponse();
        res.setEmail(user.getEmail());
        res.setPhone(user.getPhone());
        res.setRole(user.getRole());
        return res;
    }
}
