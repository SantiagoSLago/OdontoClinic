package com.BackIntegrador.clinicaIntegrador.PacienteTests;

import com.BackIntegrador.clinicaIntegrador.DTO.PacienteDTO.PacienteResponseDTO;
import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import com.BackIntegrador.clinicaIntegrador.Entidad.Paciente;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Servicio.PacienteServicio;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NoSuchObjectException;
import java.util.Date;

@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    PacienteServicio pacienteServicio;

    @Test
    public void lanzamiento_de_excepcion_de_validacion_de_datos_test() {
        ///Arrange
        Domicilio domicilio = new Domicilio(123, "Las palomas", "", "Santa Fe", "");
        Paciente paciente = new Paciente(null, "", null, "jp@gmail.com", domicilio, new Date());
        ValidacionExcepcion excepcion = null;
        //Act
        try {
            pacienteServicio.validacionDatos(paciente);
        } catch (ValidacionExcepcion e) {
            excepcion = e;
        }
        //Assert
        assertNotNull(excepcion);

    }


    @Test
    public void lanzamiento_de_excepcion_paciente_no_encontrado_test() {
        //Arrange
        NoSuchObjectException excepcion = new NoSuchObjectException(null);
        String mensaje_error_esperado = "El paciente solicitado no se encuentra registrado en la base de datos";
        Integer dni = 1234431;
        //Act
        try {
            pacienteServicio.buscarPorId(dni);


        } catch (NoSuchObjectException e) {
            excepcion = e;
        }

        //Assert
        assertEquals(mensaje_error_esperado, excepcion.getMessage());

    }

    @Test
    public void validacion_datos_exitosa_creacion_paciente_test() {
        //Arrange
        Domicilio domicilio = new Domicilio(123, "Las palomas", "Rosario", "Santa Fe", "Argentina");
        Paciente paciente = new Paciente(22345678, "Juan", "Perez", "jp@gmail.com", domicilio, new Date());
        ValidacionExcepcion excepcion = null;
        //Act
        try {
            pacienteServicio.validacionDatos(paciente);
        } catch (ValidacionExcepcion e) {
            excepcion = e;
        }
        //Assert
        assertNull(excepcion);

    }

    @Test
    public void creacion_paciente_exitosa_test() {
        //Arrange
        Domicilio domicilio = new Domicilio(123, "Las palomas", "Rosario", "Santa Fe", "Argentina");
        Paciente paciente_a_crear = new Paciente(22345678, "Juan", "Perez", "jp@gmail.com", domicilio, new Date());
        Paciente paciente_creado = null;
        int resultado_esperado = 0;
        int resultado_comparacion = -1;
        //Act
        try {
            pacienteServicio.crearPaciente(paciente_a_crear);
            paciente_creado = pacienteServicio.buscarPorId(paciente_a_crear.getDni());
            resultado_comparacion = pacienteServicio.compare(paciente_a_crear, paciente_creado);
        } catch (Exception e) {

        }
        //Assert
        assertEquals(resultado_esperado, resultado_comparacion);

    }

    @Test
    public void consulta_de_paciente_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        Domicilio domicilio = new Domicilio(44322, "Las palomas", "Rosario", "Santa Fe", "Argentina");
        Paciente paciente = new Paciente(1110092, "Lucas", "Perez", "lp@gmail.com", domicilio, new Date());
        Paciente paciente_encontrado = null;
        Integer resultado_comparacion_esperado = 0;
        Integer resultado_comparacion = -1;
        //Act
        pacienteServicio.crearPaciente(paciente);
        paciente_encontrado = pacienteServicio.buscarPorId(paciente.getDni());
        resultado_comparacion = pacienteServicio.compare(paciente, paciente_encontrado);

        //Assert
        assertEquals(resultado_comparacion_esperado, resultado_comparacion);

    }

    @Test
    public void actualizacion_datos_paciente_exitosa_test() {
        //Arrange
        Domicilio domicilio = new Domicilio(123, "Las palomas", "Rosario", "Santa Fe", "Argentina");
        Paciente paciente_creado;
        Paciente paciente_actualizado;
        PacienteResponseDTO pacienteDTO;
        PacienteResponseDTO paciente_actualizadoDTO;
        int resultado_esperado = 0;
        int resultado_comparacion = -1;

        //Act
        try {
            pacienteDTO = pacienteServicio.crearPaciente(new Paciente(111, "Juan", "Perez", "jp@gmail.com", domicilio, new Date()));
            paciente_creado = pacienteServicio.buscarPorId(pacienteDTO.getDni());
            paciente_creado.setNombre("Modificacion nombre");
            paciente_creado.setApellido("Modificacion apellido");
            paciente_creado.setEmail("Modificacion Email");
            paciente_actualizadoDTO = pacienteServicio.actualizarPaciente(paciente_creado);
            paciente_actualizado = pacienteServicio.buscarPorId(paciente_actualizadoDTO.getDni());
            resultado_comparacion = pacienteServicio.compare(paciente_creado, paciente_actualizado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Assert
        assertEquals(resultado_esperado, resultado_comparacion);


    }

    @Test
    public void eliminacion_de_paciente_exitosa_test() {
        //Arrange
        Domicilio domicilio = new Domicilio(123, "Las palomas", "Rosario", "Santa Fe", "Argentina");
        Paciente paciente = new Paciente(22345678, "Juan", "Perez", "jp@gmail.com", domicilio, new Date());
        String resultado_esperado = "Paciente eliminado";
        String resultado_actual = "";
        //Act
        try {
            pacienteServicio.crearPaciente(paciente);
            resultado_actual = pacienteServicio.eliminarPaciente(paciente.getDni());

        } catch (Exception e) {
        }

        //Assert
        assertEquals(resultado_esperado, resultado_actual);

    }

}
