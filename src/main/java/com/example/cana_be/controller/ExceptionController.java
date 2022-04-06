package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> accessHandler(Exception e) {
        if (e instanceof AccessDeniedException) {
            return new ResponseEntity<>(new ResponseMessage("403"), HttpStatus.OK);
        }else return new ResponseEntity<>(new ResponseMessage("404"), HttpStatus.OK);
    }
}
