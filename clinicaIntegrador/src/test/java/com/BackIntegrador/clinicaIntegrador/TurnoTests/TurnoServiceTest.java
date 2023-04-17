package com.BackIntegrador.clinicaIntegrador.TurnoTests;


import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoRequestDTO;
import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoResponseDTO;
import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import com.BackIntegrador.clinicaIntegrador.Entidad.Odontologo;
import com.BackIntegrador.clinicaIntegrador.Entidad.Paciente;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Servicio.OdontologoServicio;
import com.BackIntegrador.clinicaIntegrador.Servicio.PacienteServicio;
import com.BackIntegrador.clinicaIntegrador.Servicio.TurnoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NoSuchObjectException;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    PacienteServicio pacienteServicio;

    @Autowired
    OdontologoServicio odontologoServicio;

    @Autowired
    TurnoServicio turnoServicio;


    @Test
    public void lanzamiento_de_excepcion_de_validacion_de_datos_test() {

        //Arrange
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setDni_paciente(null);
        turnoRequestDTO.setMatricula_odontologo(null);
        turnoRequestDTO.setFecha_turno(null);
        ValidacionExcepcion excepcion = null;

        //Act
        try {
            turnoServicio.validacionDatos(turnoRequestDTO);
        } catch (ValidacionExcepcion e) {
            excepcion = e;
        }

        //Assert
        assertNotNull(excepcion);


    }

   /* @Test
    public void validacion_datos_exitosa_creacion_turno_test() {

        //Arrange
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setDni_paciente(123);
        turnoRequestDTO.setMatricula_odontologo(4456);
        turnoRequestDTO.setFecha_turno(new Date());
        ValidacionExcepcion excepcion = null;

        //Act
        try {
            turnoServicio.validacionDatos(turnoRequestDTO);
        } catch (ValidacionExcepcion e) {
            excepcion = e;
        }

        //Assert
        assertNull(excepcion);


    }

    */

   /* @Test
    public void creacion_turno_exitosa_test() {
        //Arrange
        Domicilio domicilio = new Domicilio(443, "Los perales", "San Martin", "Santa Fe", "Argentina");
        Paciente paciente = new Paciente(1234, "Juan", "Perez", "jp@gmail.com", domicilio, new Date());
        Odontologo odontologo = new Odontologo(222, "Martin", "Gonzalez");
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setDni_paciente(paciente.getDni());
        turnoRequestDTO.setMatricula_odontologo(odontologo.getMatricula());
        turnoRequestDTO.setFecha_turno(new Date());
        TurnoResponseDTO turnoResponseDTO = null;


        //Act
        try {
            pacienteServicio.crearPaciente(paciente);
            odontologoServicio.crearOdontologo(odontologo);
            turnoResponseDTO = turnoServicio.sacarTurno(turnoRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Assert
        assertNotNull(turnoResponseDTO);


    }

    */

   /* @Test
    public void consulta_de_turno_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        Domicilio domicilio = new Domicilio(342355, "Los perales", "San Martin", "Santa Fe", "Argentina");
        Paciente paciente = new Paciente(4400883, "Pedro", "Bermudez", "pb@gmail.com", domicilio, new Date());
        Odontologo odontologo = new Odontologo(44325, "Julian", "Martinez");
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        TurnoResponseDTO turnoResponseDTO = null;
        //Act
        pacienteServicio.crearPaciente(paciente);
        odontologoServicio.crearOdontologo(odontologo);
        turnoRequestDTO.setDni_paciente(paciente.getDni());
        turnoRequestDTO.setMatricula_odontologo(odontologo.getMatricula());
        turnoRequestDTO.setFecha_turno(new Date());
        turnoResponseDTO = turnoServicio.sacarTurno(turnoRequestDTO);
        //Assert
        assertNotNull(turnoResponseDTO);


    }

    */

   /* @Test
    public void actualizacion_turno_exitosa_test() {
        //Arrange
        Domicilio domicilio = new Domicilio(121212, "Azcuenaga", "La consulta", "Cordoba", "Argentina");
        Paciente paciente = new Paciente(333, "Juan", "Perez", "jp@gmail.com", domicilio, new Date());
        Odontologo primer_odontologo = new Odontologo(12332, "Martin", "Gonzalez");
        Odontologo segundo_odontologo = new Odontologo(9999, "Manuel", "Belgrano");
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO(paciente.getDni(), primer_odontologo.getMatricula(), new Date());
        TurnoResponseDTO turno_actualizadoDTO;
        Integer id_turno_creado;


        //Act
        try {
            pacienteServicio.crearPaciente(paciente);
            odontologoServicio.crearOdontologo(primer_odontologo);
            odontologoServicio.crearOdontologo(segundo_odontologo);
            id_turno_creado = turnoServicio.sacarTurno(turnoRequestDTO).getId_turno();
            turnoRequestDTO.setId(id_turno_creado);
            turnoRequestDTO.setMatricula_odontologo(segundo_odontologo.getMatricula());
            turnoRequestDTO.setFecha_turno(new Date(2023 - 1900, Calendar.JANUARY, 10));
            turno_actualizadoDTO = turnoServicio.actualizarTurno(turnoRequestDTO);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Assert
        assertNotNull(turno_actualizadoDTO);

    }

    */


    /*@Test
    public void eliminacion_turno_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        Domicilio domicilio = new Domicilio(14, "Zapata", "Rio IV", "Cordoba", "Argentina");
        Paciente paciente = new Paciente(543, "Juan", "Riddle", "snakeFace@gmail.com", domicilio, new Date());
        Odontologo odontologo = new Odontologo(90, "Juan", "Juanes");
        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO(paciente.getDni(), odontologo.getMatricula(), new Date(2023 - 1900, Calendar.APRIL, 15));
        TurnoResponseDTO turno_creadoDTO;
        String respuesta_esperada = "Turno eliminado";
        String respuesta = "";

        //Act
        pacienteServicio.crearPaciente(paciente);
        odontologoServicio.crearOdontologo(odontologo);
        turno_creadoDTO = turnoServicio.sacarTurno(turnoRequestDTO);
        respuesta = turnoServicio.eliminarTurno(turno_creadoDTO.getId_turno());

        //Assert
        assertEquals(respuesta_esperada,respuesta);


    }

     */


}
