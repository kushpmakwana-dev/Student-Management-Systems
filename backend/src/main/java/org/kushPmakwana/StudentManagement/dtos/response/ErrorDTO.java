package org.kushPmakwana.StudentManagement.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {
    private String errorMessage;

    private int statusCode;

    private LocalDateTime timeStamp;
}
