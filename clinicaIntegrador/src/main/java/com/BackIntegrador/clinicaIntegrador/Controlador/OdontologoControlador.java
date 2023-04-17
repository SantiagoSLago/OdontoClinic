package com.BackIntegrador.clinicaIntegrador.Controlador;


import com.BackIntegrador.clinicaIntegrador.Entidad.Odontologo;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Servicio.OdontologoServicio;
import org.apache.coyote.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoControlador {


    @Autowired
    OdontologoServicio odontologoServicio;


    @PostMapping("/crearOdontologo")
    public ResponseEntity<Odontologo> crearOdontologo(@RequestBody Odontologo odontologo) throws ValidacionExcepcion {
        return new ResponseEntity(odontologoServicio.crearOdontologo(odontologo), HttpStatus.CREATED);
    }

    @GetMapping("/listarOdontologos")
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        return new ResponseEntity(odontologoServicio.listarOdontologos(), HttpStatus.OK);
    }

    @GetMapping("/consultarOdontologo/{matricula}")
    public ResponseEntity<Odontologo> consultarOdonotolog(@PathVariable Integer matricula) throws NoSuchObjectException {
        return new ResponseEntity<>(odontologoServicio.buscarPorMatricula(matricula),HttpStatus.OK);
    }

    @PutMapping("/actualizarOdontologo")
    public ResponseEntity<Odontologo> modificarOdontologo(@RequestBody Odontologo odontologo) throws NoSuchObjectException, ValidacionExcepcion {
        return new ResponseEntity(odontologoServicio.actualizarOdontologo(odontologo), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarOdontologo/{matricula}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer matricula) throws NoSuchObjectException, ValidacionExcepcion {
        return new ResponseEntity(odontologoServicio.eliminarOdontologo(matricula),HttpStatus.NO_CONTENT);
    }


}
