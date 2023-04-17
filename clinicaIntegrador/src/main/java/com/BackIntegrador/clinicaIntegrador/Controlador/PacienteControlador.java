package com.BackIntegrador.clinicaIntegrador.Controlador;


import com.BackIntegrador.clinicaIntegrador.DTO.PacienteDTO.PacienteResponseDTO;
import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoResponseDTO;
import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import com.BackIntegrador.clinicaIntegrador.Entidad.Paciente;
import com.BackIntegrador.clinicaIntegrador.Entidad.Turno;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Servicio.DomicilioServicio;
import com.BackIntegrador.clinicaIntegrador.Servicio.PacienteServicio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteControlador {


    @Autowired
    PacienteServicio pacienteServicio;



    @PostMapping("/altaPacientes")
    public ResponseEntity<PacienteResponseDTO> altaPaciente(@RequestBody Paciente paciente) throws ValidacionExcepcion {
        return new ResponseEntity(pacienteServicio.crearPaciente(paciente), HttpStatus.CREATED);
    }

    @GetMapping("/listarPacientes")
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes() {
        return new ResponseEntity(pacienteServicio.listarPacientes(), HttpStatus.OK);
    }

    @PutMapping("/actualizarPaciente")
    public ResponseEntity<PacienteResponseDTO> actualizarPaciente(@RequestBody Paciente paciente) throws NoSuchObjectException, ValidacionExcepcion {
        return new ResponseEntity(pacienteServicio.actualizarPaciente(paciente), HttpStatus.CREATED);
    }


    @DeleteMapping("/eliminarPaciente/{dni}")
    public ResponseEntity eliminarPaciente(@PathVariable Integer dni) throws NoSuchObjectException, ValidacionExcepcion {
        return new ResponseEntity(pacienteServicio.eliminarPaciente(dni), HttpStatus.NO_CONTENT);
    }




    @GetMapping("/consultarPaciente/{dni}")
    public ResponseEntity<PacienteResponseDTO> consultarPacientePorId(@PathVariable Integer dni) throws NoSuchObjectException {
        return new ResponseEntity(pacienteServicio.consultarPaciente(dni), HttpStatus.OK);
    }


}
