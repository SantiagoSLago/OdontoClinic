package com.BackIntegrador.clinicaIntegrador.Controlador;


import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import com.BackIntegrador.clinicaIntegrador.Servicio.DomicilioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioControlador {


    @Autowired
    DomicilioServicio domicilioServicio;

    @PostMapping("/crearDomicilio")
    public ResponseEntity<Domicilio> postDomicilio(@RequestBody Domicilio domicilio) {

        ResponseEntity<Domicilio> respuesta = null;

        try {
            respuesta = ResponseEntity.ok(domicilioServicio.crearDomicilio2(domicilio));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return respuesta;
    }

    @GetMapping("/listarDomicilios")
    public List<Domicilio> listarDomicilios() {
        return domicilioServicio.listarDomicilios();
    }

    @DeleteMapping("/eliminarDomicilio/{id}")
    public ResponseEntity eliminarDomicilio(@PathVariable Integer id) {
        ResponseEntity response = null;

        try {

            domicilioServicio.eliminarDomicilio(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);//Devuelve no content pq ya no se encuentra el registro en la BD, fue eliminadok con exito

        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }


        return response;


    }

    @PutMapping("/actualizarDomicilio")
    public ResponseEntity<Domicilio> actualizarDomicilio(@RequestBody Domicilio domicilio) {

        ResponseEntity response = null;
        try{
            response = new ResponseEntity(domicilioServicio.actualizarDomicilio(domicilio), HttpStatus.OK);
        }catch(NoSuchObjectException e){
            //Utilizar un Logger para obtener el mensaje de la excepcion
            System.out.println(e.getMessage());
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }





        return response;


    }

    @GetMapping("/consultarDomicilio/{id}")
    public ResponseEntity<Domicilio> consultarDomicilio(@PathVariable Integer id){
        ResponseEntity<Domicilio> response = null;
        try {
             response = ResponseEntity.ok(domicilioServicio.buscarPorId(id));
        }catch (NoSuchObjectException e){
            //Incorporar un Logger para registrar el texto de la excepcion
            System.out.println(e.getMessage());
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;

    }


}
