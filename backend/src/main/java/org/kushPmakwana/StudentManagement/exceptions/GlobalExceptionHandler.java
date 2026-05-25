package org.kushPmakwana.StudentManagement.exceptions;

import org.kushPmakwana.StudentManagement.dtos.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistException(UserAlreadyExist ex){
        ErrorDTO err = ErrorDTO.builder()
                .errorMessage("User Already Exist")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
