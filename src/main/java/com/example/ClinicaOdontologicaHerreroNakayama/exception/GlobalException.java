package com.example.ClinicaOdontologicaHerreroNakayama.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({ResorceNotFoundException.class})
    public ResponseEntity<String> tratamientoResorceNotFoundException(ResorceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> handleBadRequestException(BadRequestException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<String> handleCustomException(CustomException ce){
        String mensajeError = "Ocurrió un error personalizado: " + ce.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
    }





}
