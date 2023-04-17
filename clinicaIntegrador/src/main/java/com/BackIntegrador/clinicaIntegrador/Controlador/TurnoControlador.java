package com.BackIntegrador.clinicaIntegrador.Controlador;


import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoRequestDTO;
import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoResponseDTO;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Servicio.TurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import java.rmi.NoSuchObjectException;
import java.util.List;


@RestController
@RequestMapping("/turnos")
public class TurnoControlador {


    @Autowired
    TurnoServicio turnoServicio;


    @PostMapping("/altaTurno")
    public ResponseEntity<TurnoResponseDTO> altaTurno(@RequestBody TurnoRequestDTO turnoRequestDTO) throws NoSuchObjectException, ValidacionExcepcion {
        return new ResponseEntity(turnoServicio.sacarTurno(turnoRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/consultarTurno/{id}")
    public ResponseEntity<TurnoResponseDTO> consultarTurno(@PathVariable Integer id) throws NoSuchObjectException, ValidacionExcepcion {
        return new ResponseEntity(turnoServicio.consultarTurno(id), HttpStatus.OK);
    }

    @GetMapping("/listarTurnos")
    public ResponseEntity<List<TurnoResponseDTO>> turnos() {
        return new ResponseEntity(turnoServicio.listarTurnos(), HttpStatus.OK);
    }

    @PutMapping("/actualizarTurno")
    public ResponseEntity<TurnoResponseDTO> actualizarTurno(@RequestBody TurnoRequestDTO turnoRequestDTO) throws NoSuchObjectException, ValidacionExcepcion {
        return new ResponseEntity(turnoServicio.actualizarTurno(turnoRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarTurno/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) throws NoSuchObjectException, ValidacionExcepcion {
        return new ResponseEntity(turnoServicio.eliminarTurno(id), HttpStatus.NO_CONTENT);
    }


}
