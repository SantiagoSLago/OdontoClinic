package com.BackIntegrador.clinicaIntegrador.Excepciones;


import com.BackIntegrador.clinicaIntegrador.Servicio.OdontologoServicio;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.http.HttpResponse;
import java.rmi.NoSuchObjectException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchObjectException.class)
    public ResponseEntity<String> NotFoundExceptionHandler(NoSuchObjectException e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ValidacionExcepcion.class)
    public ResponseEntity<String> ValidacionExceptionHandler(ValidacionExcepcion e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
