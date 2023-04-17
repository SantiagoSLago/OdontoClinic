package com.BackIntegrador.clinicaIntegrador.PacienteTests;


import com.BackIntegrador.clinicaIntegrador.Controlador.PacienteControlador;
import com.BackIntegrador.clinicaIntegrador.DTO.PacienteDTO.PacienteResponseDTO;
import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import com.BackIntegrador.clinicaIntegrador.Entidad.Paciente;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Servicio.PacienteServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NoSuchObjectException;
import java.util.Date;

@SpringBootTest
public class PacienteControladorTest {

    @Autowired
    PacienteControlador pacienteControlador;

    @Autowired
    PacienteServicio pacienteServicio;

    @Test
    public void creacion_paciente_exitosa_test() {
        //Arrange
        HttpStatusCode codigo_respuesta_esperado = HttpStatus.CREATED;
        HttpStatusCode respuesta = null;
        Domicilio domicilio = new Domicilio(456, "San Martin", "Capital", "Cordoba", "Argentina");
        Paciente paciente = new Paciente(111, "Juan", "Perez", "jp@gmail.com", domicilio, new Date());

        //Act
        try {
            respuesta = pacienteControlador.altaPaciente(paciente).getStatusCode();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Assert
        assertEquals(codigo_respuesta_esperado, respuesta);

    }

    @Test
    public void actualizacion_paciente_exitosa_test() {
        //Arrange
        HttpStatusCode codigo_respuesta_esperado = HttpStatus.CREATED;
        HttpStatusCode respuesta = null;
        Domicilio domicilio = new Domicilio(3, "colinas", "La Comarca", "Hobbiton", "Eriador");
        Paciente paciente = new Paciente(1234567, "Gandal", "El Gris", "goEagles@gmail.com", domicilio, new Date());

        //Act
        try {
            pacienteControlador.altaPaciente(paciente);
            paciente.setNombre("Nombre modificado");
            paciente.setApellido("El Blanco");
            respuesta = pacienteControlador.actualizarPaciente(paciente).getStatusCode();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Assert
        assertEquals(codigo_respuesta_esperado, respuesta);


    }

    @Test
    public void consulta_paciente_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        HttpStatusCode codigo_respuesta_esperado = HttpStatus.OK;
        HttpStatusCode respuesta = null;
        Domicilio domicilio = new Domicilio(3341, "San Martin", "Capital", "Cordoba", "Argentina");
        Paciente paciente = new Paciente(653, "Pedro", "Gonzalez", "pg@gmail.com", domicilio, new Date());

        //Act
        pacienteServicio.crearPaciente(paciente);
        respuesta = pacienteControlador.consultarPacientePorId(paciente.getDni()).getStatusCode();

        //Assert
        assertEquals(codigo_respuesta_esperado,respuesta);
    }

    @Test
    public void eliminacion_paciente_exitosa_test() {
        //Arrange
        PacienteResponseDTO paciente_creadoDTO = null;
        Paciente paciente_creado = null;
        HttpStatusCode codigo_respuesta_esperado = HttpStatus.NO_CONTENT;
        HttpStatusCode respuesta = null;
        Domicilio domicilio = new Domicilio(4561, "Casterly Rock", "Lannisport", "West Coast", "Westeros");
        Paciente paciente = new Paciente(9090, "Cersei", "Lannister", "psico_woman@gmail.com", domicilio, new Date());
        //Arrange
        try {
            paciente_creadoDTO = pacienteServicio.crearPaciente(paciente);
            paciente_creado = pacienteServicio.buscarPorId(paciente_creadoDTO.getDni());
            respuesta = pacienteControlador.eliminarPaciente(paciente_creado.getDni()).getStatusCode();

        } catch (Exception e) {

        }

        //Assert
        assertEquals(codigo_respuesta_esperado,respuesta);

    }


}
