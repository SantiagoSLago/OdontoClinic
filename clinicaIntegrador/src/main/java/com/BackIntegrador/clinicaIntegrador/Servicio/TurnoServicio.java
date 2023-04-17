package com.BackIntegrador.clinicaIntegrador.Servicio;


import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoRequestDTO;
import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoResponseDTO;
import com.BackIntegrador.clinicaIntegrador.Entidad.Odontologo;
import com.BackIntegrador.clinicaIntegrador.Entidad.Paciente;
import com.BackIntegrador.clinicaIntegrador.Entidad.Turno;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Repositorio.TurnoRepositorio;
import com.sun.tools.jconsole.JConsoleContext;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServicio {


    @Autowired
    TurnoRepositorio turnoRepositorio;

    @Autowired
    PacienteServicio pacienteServicio;

    @Autowired
    OdontologoServicio odontologoServicio;

    private static final Logger LOGGER = Logger.getLogger(TurnoServicio.class);

    @Transactional
    public TurnoResponseDTO sacarTurno(TurnoRequestDTO turnoRequestDTO) throws NoSuchObjectException, ValidacionExcepcion {

        validacionDatos(turnoRequestDTO);

        Paciente p = pacienteServicio.buscarPorId(turnoRequestDTO.getDni_paciente());
        Odontologo o = odontologoServicio.buscarPorMatricula(turnoRequestDTO.getMatricula_odontologo());


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaTurno = turnoRequestDTO.getFecha_turno();
        dateFormat.format(fechaTurno);


        Turno turno = new Turno(p, o, fechaTurno);




        turnoRepositorio.save(turno);
        TurnoResponseDTO turnoRegistradoDTO = ultimoTurnoRegistrado();

        LOGGER.info("Alta turno exitosa: " + "Paciente: " + p.getNombre() + " " + p.getApellido()
                + " Odontologo: " + o.getNombre() + " " + o.getApellido() +
                " Fecha: " + fechaTurno);
        return turnoRegistradoDTO;


    }

    public List<TurnoResponseDTO> listarTurnos() {
        List<Turno> turnos = turnoRepositorio.findAll();
        List<TurnoResponseDTO> turnosDTO = new ArrayList<>();
        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();

        for (Turno t : turnos) {
            turnoResponseDTO = conversor_a_turnoResponseDto(t);
            turnosDTO.add(turnoResponseDTO);
        }

        return turnosDTO;


    }

    @Transactional
    public TurnoResponseDTO actualizarTurno(TurnoRequestDTO turnoRequestDTO) throws NoSuchObjectException, ValidacionExcepcion {


            Turno t = buscarPorId(turnoRequestDTO.getId());


            if(turnoRequestDTO.getFecha_turno() == null && turnoRequestDTO.getMatricula_odontologo() == null){
                throw new ValidacionExcepcion("La fecha o matricula de odontologo deben contener algun dato");
            }

            if (turnoRequestDTO.getFecha_turno() != null) {
                t.setFecha_turno(turnoRequestDTO.getFecha_turno());
            }
            if (turnoRequestDTO.getMatricula_odontologo() != null) {
                t.setOdontologo(odontologoServicio.buscarPorMatricula(turnoRequestDTO.getMatricula_odontologo()));
            }
            turnoRepositorio.save(t);
            LOGGER.info("Modificacion de turno exitosa: " + "Paciente: " + t.getPaciente().getNombre() + " " + t.getPaciente().getApellido()
                    + " Odontologo: " + t.getOdontologo().getNombre() + " " + t.getOdontologo().getApellido() +
                    " Fecha: " + t.getFecha_turno());

            return conversor_a_turnoResponseDto(t);





    }

    public TurnoResponseDTO consultarTurno(Integer id) throws NoSuchObjectException, ValidacionExcepcion {
        Turno turno = buscarPorId(id);
        return conversor_a_turnoResponseDto(turno);
    }

    @Transactional
    public String eliminarTurno(Integer id) throws NoSuchObjectException, ValidacionExcepcion {
        Turno t= null;
        try {
          t =   buscarPorId(id);
        }catch (ValidacionExcepcion e){
            throw new ValidacionExcepcion(e.getMessage());
        }
        turnoRepositorio.delete(t);
        LOGGER.info("Turno Eliminado con exito: " + "Id Turno Eliminado: " + id);
        return "Turno eliminado";


    }

    public Turno buscarPorId(Integer id) throws NoSuchObjectException, IllegalArgumentException, ValidacionExcepcion {
        if (id == null){
            throw new ValidacionExcepcion("El id del turno no puede ser nulo ni debe estar vacio");
        }else{
            Turno turno = null;
            Optional<Turno> respuesta = turnoRepositorio.findById(id);

            if (respuesta.isPresent()) {
                turno = respuesta.get();
            } else {
                throw new NoSuchObjectException("El turno solicitado no se encuentra en la base de datos");
            }
            return turno;
        }




    }

    public static TurnoResponseDTO conversor_a_turnoResponseDto(Turno turno) {
        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
        turnoResponseDTO.setNombre_paciente(turno.getPaciente().getNombre());
        turnoResponseDTO.setApellido_paciente(turno.getPaciente().getApellido());
        turnoResponseDTO.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoResponseDTO.setApellido_odontologo(turno.getOdontologo().getApellido());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        turnoResponseDTO.setFecha_turno(dateFormat.format(turno.getFecha_turno()));
        turnoResponseDTO.setId_turno(turno.getId());
        return turnoResponseDTO;

    }

    public void validacionDatos(TurnoRequestDTO turnoRequestDTO) throws ValidacionExcepcion {

        if (turnoRequestDTO.getDni_paciente() == null) {
            throw new ValidacionExcepcion("El dni del paciente no debe ser nulo ni debe estar vacio");
        }
        if (turnoRequestDTO.getMatricula_odontologo() == null) {
            throw new ValidacionExcepcion("La matricula del odontologo no debe ser nula ni debe estar vacia");
        }
        if (turnoRequestDTO.getFecha_turno() == null) {
            throw new ValidacionExcepcion("La fecha del turno no debe ser nula ni debe estar vacia");
        }


    }

    public TurnoResponseDTO ultimoTurnoRegistrado() {
        TurnoResponseDTO turnoResponseDTO = null;
        List<Turno> turnos = turnoRepositorio.listaInvertidaTurnos();
        for (Turno t : turnos) {
            turnoResponseDTO = conversor_a_turnoResponseDto(t);
            break;
        }

        return turnoResponseDTO;

    }

    public List<Turno> listadoTurnos(){
        return this.turnoRepositorio.findAll();
    }







}
