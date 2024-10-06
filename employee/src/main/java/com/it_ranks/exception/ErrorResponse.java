package com.it_ranks.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private LocalDateTime dateTime ;
    private HttpStatus status ;
    private int statusCode;

    public  ErrorResponse (String message, HttpStatus status) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
        this.statusCode=status.value () ;
        this.status=status;
    }
}
