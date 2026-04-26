package org.kushPmakwana.StudentManagement.exceptions;


import org.kushPmakwana.StudentManagement.dto.userDto.responseDto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO<Void>> handleUserAlreadyExist(UserAlreadyExistsException e){
        ResponseDTO<Void> res = new ResponseDTO<>();
        res.setMessage(e.getMessage());
        res.setStatus(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }
}
