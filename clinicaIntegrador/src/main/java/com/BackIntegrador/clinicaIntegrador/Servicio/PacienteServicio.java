package com.BackIntegrador.clinicaIntegrador.Servicio;


import com.BackIntegrador.clinicaIntegrador.DTO.PacienteDTO.PacienteResponseDTO;
import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoResponseDTO;
import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import com.BackIntegrador.clinicaIntegrador.Entidad.Paciente;
import com.BackIntegrador.clinicaIntegrador.Entidad.Turno;
import com.BackIntegrador.clinicaIntegrador.Excepciones.GlobalExceptionHandler;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Repositorio.PacienteRepositorio;
import com.BackIntegrador.clinicaIntegrador.Repositorio.TurnoRepositorio;
import com.sun.tools.jconsole.JConsoleContext;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PacienteServicio implements Comparator<Paciente> {


    @Autowired
    PacienteRepositorio pacienteRepositorio;

    @Autowired
    DomicilioServicio domicilioServicio;

    @Autowired
    TurnoRepositorio turnoRepositorio;





    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(PacienteServicio.class);


    @Transactional
    public PacienteResponseDTO crearPaciente(Paciente paciente) throws ValidacionExcepcion {
        validacionDatos(paciente);
        Domicilio domicilio = domicilioServicio.crearDomicilio2(paciente.getDomicilio());
        paciente.setDomicilio(domicilio);
        Date fecha = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.format(fecha);
        paciente.setFecha_alta(fecha);
        pacienteRepositorio.save(paciente);
        LOGGER.info("Paciente creado " + "DNI: " + paciente.getDni() + " Nombre y Apellido: " + paciente.getNombre() + " " + paciente.getApellido());
        return conversor_a_pacienteResponseDto(paciente);
    }


    public Paciente buscarPorId(Integer dni) throws NoSuchObjectException {
        Paciente paciente = null;
        Optional<Paciente> respuesta = pacienteRepositorio.findById(dni);


        if (respuesta.isPresent()) {
            paciente = respuesta.get();

        } else {
            throw new NoSuchObjectException("El paciente solicitado no se encuentra registrado en la base de datos");
        }
        return paciente;

    }


    public PacienteResponseDTO consultarPaciente(Integer id) throws NoSuchObjectException {
        Paciente paciente = buscarPorId(id);
        return conversor_a_pacienteResponseDto(paciente);

    }

    public List<PacienteResponseDTO> listarPacientes() {
        List<Paciente> pacientes = pacienteRepositorio.findAll();
        List<PacienteResponseDTO> pacientesDTO = new ArrayList<>();
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();

        for (Paciente p : pacientes) {
            pacienteResponseDTO = conversor_a_pacienteResponseDto(p);
            pacientesDTO.add(pacienteResponseDTO);
        }


        return pacientesDTO;
    }

    @Transactional
    public String eliminarPaciente(Integer dni) throws NoSuchObjectException {

        String mensaje = null;
        Paciente p = buscarPorId(dni);
        for (Turno t: turnoRepositorio.findAll()){
            if(t.getPaciente().getDni().equals(dni)){
                turnoRepositorio.delete(t);
            }
        }


        pacienteRepositorio.delete(p);
        LOGGER.info("Paciente eliminado: " + "DNI: " + dni);
        return "Paciente eliminado";
    }


    @Transactional
    public PacienteResponseDTO actualizarPaciente(Paciente paciente) throws NoSuchObjectException, ValidacionExcepcion {

        if (paciente.getDni() != null) {
            Paciente p = buscarPorId(paciente.getDni());
            if (!paciente.getNombre().equals("")) {
                p.setNombre(paciente.getNombre());
            }
            if (!paciente.getApellido().equals("")) {
                p.setApellido(paciente.getApellido());
            }
            if (!paciente.getEmail().equals("")) {
                p.setEmail(paciente.getEmail());
            }
            if (paciente.getDni() != null) {
                p.setDni(paciente.getDni());
            }
            if (paciente.getDomicilio().getNumero() != null) {
                p.getDomicilio().setNumero(paciente.getDomicilio().getNumero());
            }
            if (!paciente.getDomicilio().getCalle().equals("")) {
                p.getDomicilio().setCalle(paciente.getDomicilio().getCalle());
            }
            if (!paciente.getDomicilio().getCiudad().equals("")) {
                p.getDomicilio().setCiudad(paciente.getDomicilio().getCiudad());
            }
            if (!paciente.getDomicilio().getProvincia().equals("")) {
                p.getDomicilio().setProvincia(paciente.getDomicilio().getProvincia());
            }
            if (!paciente.getDomicilio().getPais().equals("")) {
                p.getDomicilio().setPais(paciente.getDomicilio().getPais());
            }
            pacienteRepositorio.save(p);
            LOGGER.info("Paciente actualizado:" + "DNI: " + p.getDni());
            return conversor_a_pacienteResponseDto(p);
        } else {
            throw new ValidacionExcepcion("El dni no puede ser nulo ni estar vacio");
        }


    }


    public void validacionDatos(Paciente paciente) throws ValidacionExcepcion {


        if ((paciente.getNombre() == null) || (paciente.getNombre().isBlank())) {
            throw new ValidacionExcepcion("El nombre no puede ser nulo ni estar vacio");
        }
        if ((paciente.getApellido() == null) || (paciente.getApellido().isBlank())) {
            throw new ValidacionExcepcion("El apellido no puede ser nulo ni estar vacio");
        }
        if ((paciente.getEmail() == null) || (paciente.getEmail().isBlank())) {
            throw new ValidacionExcepcion("El email no puede ser nulo ni estar vacio");
        }
        if (paciente.getDni() == null) {
            throw new ValidacionExcepcion("El dni no puede ser nulo ni estar vacio");
        }
        domicilioServicio.validacionDatos(paciente.getDomicilio());


    }


    public Paciente ultimoPacienteRegistrado() {
        List<Paciente> pacientes = pacienteRepositorio.listaInvertidaPacientes();
        Paciente paciente = null;
        for (Paciente p : pacientes) {
            paciente = p;
            break;
        }
        return paciente;
    }


    public PacienteResponseDTO conversor_a_pacienteResponseDto(Paciente paciente) {
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();

        String domicilio = paciente.getDomicilio().getNumero().toString() + "-"
                + paciente.getDomicilio().getCalle() + "-"
                + paciente.getDomicilio().getCiudad() + "-"
                + paciente.getDomicilio().getProvincia() + "-"
                + paciente.getDomicilio().getPais();

        pacienteResponseDTO.setNombre(paciente.getNombre());
        pacienteResponseDTO.setApellido(paciente.getApellido());
        pacienteResponseDTO.setDni(paciente.getDni());
        pacienteResponseDTO.setDomicilio(domicilio);
        pacienteResponseDTO.setEmail(paciente.getEmail());

        return pacienteResponseDTO;


    }





    @Override
    public int compare(Paciente o1, Paciente o2) {
        int resultado = -1;
        if (o1.getDni().equals(o2.getDni())) {
            if (o1.getNombre().equals(o2.getNombre()) && o1.getApellido().equals(o2.getApellido())) {
                if (o1.getEmail().equals(o2.getEmail())) {
                    if (o1.getFecha_alta().equals(o2.getFecha_alta())) {
                        resultado = domicilioServicio.compare(o1.getDomicilio(), o2.getDomicilio());
                    }
                }
            }
        }


        return resultado;
    }
}
