package com.BackIntegrador.clinicaIntegrador.OdontologoTests;

import com.BackIntegrador.clinicaIntegrador.Entidad.Odontologo;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Servicio.OdontologoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.rmi.NoSuchObjectException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    OdontologoServicio odontologoServicio;


    @Test
    public void lanzamiento_de_excepcion_de_validacion_de_datos_test() {
        //Arrange
        Odontologo odontologo = new Odontologo(1234, null, "");
        ValidacionExcepcion excepcion = null;
        //Act
        try {
            odontologoServicio.validacionDatos(odontologo);
        } catch (ValidacionExcepcion e) {
            excepcion = e;
        }

        assertNotNull(excepcion);
    }

    @Test
    public void lanzamiento_de_excepcion_odontologo_no_encontrado_test() throws ValidacionExcepcion {
        //Arrange
        Odontologo odontologo = new Odontologo(44539, "Pedro", "Ramirez");
        NoSuchObjectException excepcion = new NoSuchObjectException(null);
        String mensaje_error_esperado = "El odontologo solicitado no se encuentra registrado";
        //Act
        try {
            odontologoServicio.buscarPorMatricula(odontologo.getMatricula());
        } catch (NoSuchObjectException e) {
            excepcion = e;
        }
        //Assert
        assertEquals(mensaje_error_esperado, excepcion.getMessage());
    }

    @Test
    public void validacion_datos_exitosa_creacion_odontologo_test() {
        //Arrange
        Odontologo odontologo = new Odontologo(1234, "Juan", "Perez");
        ValidacionExcepcion excepcion = null;
        //Act
        try {
            odontologoServicio.validacionDatos(odontologo);
        } catch (ValidacionExcepcion e) {
            excepcion = e;
        }
        //Assert
        assertNull(excepcion);


    }

    @Test
    public void creacion_odontologo_exitosa_test() throws ValidacionExcepcion {

        //Arrange
        Odontologo odontologo_a_crear = new Odontologo(1234, "Juan", "Perez");
        Odontologo odontologo_creado = null;
        int resultado_esperado = 0;
        int resultado_comparacion = -1;
        //Act

        odontologo_creado = odontologoServicio.crearOdontologo(odontologo_a_crear);
        resultado_comparacion = odontologoServicio.compare(odontologo_a_crear, odontologo_creado);


        //Assert
        assertEquals(resultado_esperado, resultado_comparacion);


    }

    @Test
    public void busqueda_odontologo_por_matricula_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        Odontologo odontologo = odontologoServicio.crearOdontologo(new Odontologo(1234, "Juan", "Perez"));
        Integer resultado_comparacion_esperado = 0;
        Integer resultado_comparacion = -1;
        Odontologo odontologo_encontrado = null;
        //Act
        odontologo_encontrado = odontologoServicio.buscarPorMatricula(odontologo.getMatricula());
        resultado_comparacion = odontologoServicio.compare(odontologo, odontologo_encontrado);
        //Assert
        assertEquals(resultado_comparacion_esperado, resultado_comparacion);


    }

    @Test
    public void actualizacion_datos_odontologo_exitosa_test() throws NoSuchObjectException, ValidacionExcepcion {
        //Arrange
        Odontologo odontologoCreado = odontologoServicio.crearOdontologo(new Odontologo(1234, "Juan", "Perez"));
        Odontologo odontologoConModificaciones = new Odontologo(1234, "nombre_modificacion", "apellido_modificacion");
        int resultado_esperado = 0;
        //Act
        Odontologo odontologoModificado = odontologoServicio.actualizarOdontologo(odontologoConModificaciones);
        //Assert
        assertEquals(resultado_esperado, odontologoServicio.compare(odontologoModificado, odontologoConModificaciones));

    }

    /*
    @Test
    public void eliminacion_odontologo_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        Odontologo odontologo = odontologoServicio.crearOdontologo(new Odontologo(1234, "Juan", "Perez"));
        String resultado_esperado = "Odontologo eliminado";
        String resultado_actual = "";
        //Act
        resultado_actual = odontologoServicio.eliminarOdontologo(odontologo.getMatricula());
        //Assert
        assertEquals(resultado_esperado, resultado_actual);


    }

     */

}
