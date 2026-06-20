package org.kushPmakwana.StudentManagement.exceptions;

import org.kushPmakwana.StudentManagement.dtos.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistException(UserAlreadyExist ex){
        ErrorDTO err = ErrorDTO.builder()
                .errorMessage("User Already Exist")
                .statusCode(HttpStatus.FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TenantAlreadyExists.class)
    public ResponseEntity<ErrorDTO> handleTenantAlreadyExistException(TenantAlreadyExists ex){
        ErrorDTO err = ErrorDTO.builder()
                .errorMessage("Tenant Already exists")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyFoundException.class)
    public ResponseEntity<ErrorDTO> handleAlreadyExistsException(AlreadyFoundException ex){
        ErrorDTO err = ErrorDTO.builder()
                .errorMessage(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ErrorDTO> handleUserDisabledException(UserDisabledException ex){
        ErrorDTO err = ErrorDTO.builder()
                .errorMessage(ex.getMessage())
                .statusCode(HttpStatus.LOCKED.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(err, HttpStatus.LOCKED);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleAdminNotFoundException(AdminNotFoundException ex){
        ErrorDTO err = ErrorDTO.builder()
                .errorMessage(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
