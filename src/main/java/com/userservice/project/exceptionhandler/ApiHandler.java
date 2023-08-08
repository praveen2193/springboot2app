package com.userservice.project.exceptionhandler;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiHandler {
    private String message;
    private boolean success;
    private HttpStatus status;

}
