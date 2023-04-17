package com.BackIntegrador.clinicaIntegrador.TurnoTests;


import com.BackIntegrador.clinicaIntegrador.Controlador.TurnoControlador;
import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoRequestDTO;
import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import com.BackIntegrador.clinicaIntegrador.Entidad.Odontologo;
import com.BackIntegrador.clinicaIntegrador.Entidad.Paciente;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Servicio.OdontologoServicio;
import com.BackIntegrador.clinicaIntegrador.Servicio.PacienteServicio;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.NoSuchObjectException;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class TurnoControladorTest {


    @Autowired
    TurnoControlador turnoControlador;

    @Autowired
    PacienteServicio pacienteServicio;

    @Autowired
    OdontologoServicio odontologoServicio;


    @Test
    public void creacion_turno_exitosa_test() throws NoSuchObjectException, ValidacionExcepcion {
        //Arrange
        HttpStatusCode codigo_respuesta_esperada = HttpStatus.CREATED;
        HttpStatusCode respuesta;
        Domicilio domicilio = new Domicilio(212, "Piedras", "San Telmo", "Buenos Aires", "Argentina");
        Paciente paciente = new Paciente(98124, "Martin", "Rios", "mr@gmail.com", domicilio, new Date());
        Odontologo odontologo = new Odontologo(89324, "Matias", "Rojo");
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO(paciente.getDni(), odontologo.getMatricula(), new Date());

        //Act
        pacienteServicio.crearPaciente(paciente);
        odontologoServicio.crearOdontologo(odontologo);
        respuesta = turnoControlador.altaTurno(turnoRequestDTO).getStatusCode();

        //Assert
        assertEquals(codigo_respuesta_esperada,respuesta);

    }

    @Test
    public void actualizacion_turno_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        HttpStatusCode codigo_respuesta_esperada = HttpStatus.CREATED;
        HttpStatusCode respuesta;
        Domicilio primer_domicilio = new Domicilio(11212, "Robles", "Tortugas", "Buenos Aires", "Argentina");
        Paciente primer_paciente = new Paciente(54380, "John", "Wick", "babbaYaga@gmail.com", primer_domicilio, new Date());
        Odontologo primer_odontologo = new Odontologo(541, "Julian", "Alvarez");
        Domicilio segundo_domicilio = new Domicilio(5545, "Acacias", "Nappa", "California", "USA");
        Paciente segundo_paciente = new Paciente(8832, "Luke", "Skywlaker", "i_love_yoda@gmail.com", primer_domicilio, new Date());
        Odontologo segundo_odontologo = new Odontologo(123008, "Darth", "Vader");
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO(primer_paciente.getDni(), primer_odontologo.getMatricula(), new Date());
        Integer id_turno;


        //Act
        pacienteServicio.crearPaciente(primer_paciente);
        pacienteServicio.crearPaciente(segundo_paciente);
        odontologoServicio.crearOdontologo(primer_odontologo);
        odontologoServicio.crearOdontologo(segundo_odontologo);
        id_turno = turnoControlador.altaTurno(turnoRequestDTO).getBody().getId_turno();
        turnoRequestDTO.setId(id_turno);
        turnoRequestDTO.setDni_paciente(segundo_paciente.getDni());
        turnoRequestDTO.setMatricula_odontologo(segundo_odontologo.getMatricula());
        turnoRequestDTO.setFecha_turno(new Date(2023-1900, Calendar.APRIL,8));
        respuesta = turnoControlador.actualizarTurno(turnoRequestDTO).getStatusCode();


        //Assert
        assertEquals(codigo_respuesta_esperada,respuesta);


    }

    @Test
    public void consulta_turno_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        HttpStatusCode codigo_respuesta_esperada = HttpStatus.OK;
        HttpStatusCode respuesta;
        Domicilio domicilio = new Domicilio(3434, "25st", "New York", "Ney York", "USA");
        Paciente paciente = new Paciente(343, "Donald", "Trump", "money@gmail.com", domicilio, new Date());
        Odontologo odontologo = new Odontologo(38927, "Vladimir", "Putin");
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO(paciente.getDni(), odontologo.getMatricula(), new Date());
        Integer id_turno_creado;

        //Act
        pacienteServicio.crearPaciente(paciente);
        odontologoServicio.crearOdontologo(odontologo);
        id_turno_creado = turnoControlador.altaTurno(turnoRequestDTO).getBody().getId_turno();
        respuesta = turnoControlador.consultarTurno(id_turno_creado).getStatusCode();

        //Assert
        assertEquals(codigo_respuesta_esperada,respuesta);

    }

    @Test
    public void eliminacion_turno_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        HttpStatusCode codigo_respuesta_esperada = HttpStatus.NO_CONTENT;
        HttpStatusCode respuesta;
        Domicilio domicilio = new Domicilio(7821, "Saenz Peña", "Kingston", "Capital", "Jamaica");
        Paciente paciente = new Paciente(4353, "Bob", "Marley", "rasta@gmail.com", domicilio, new Date());
        Odontologo odontologo = new Odontologo(4302, "Charles", "Xavier");
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO(paciente.getDni(), odontologo.getMatricula(), new Date());
        Integer id_turno_creado;

        //Act
        pacienteServicio.crearPaciente(paciente);
        odontologoServicio.crearOdontologo(odontologo);
        id_turno_creado = turnoControlador.altaTurno(turnoRequestDTO).getBody().getId_turno();
        respuesta = turnoControlador.eliminarTurno(id_turno_creado).getStatusCode();

        //Assert
        assertEquals(codigo_respuesta_esperada,respuesta);


    }

}
