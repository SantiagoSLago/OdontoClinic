package com.BackIntegrador.clinicaIntegrador.OdontologoTests;


import com.BackIntegrador.clinicaIntegrador.Controlador.OdontologoControlador;
import com.BackIntegrador.clinicaIntegrador.Entidad.Odontologo;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.rmi.NoSuchObjectException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OdontologoControladorTest {

    @Autowired
    OdontologoControlador odontologoControlador;
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(OdontologoControladorTest.class);

    @Test
    public void creacion_odontologo_exitosa_test() {
        //Arrange
        HttpStatusCode codigo_respuesta_esperado = HttpStatus.CREATED;
        HttpStatusCode respuesta = null;
        Odontologo odontologo = new Odontologo(123, "Juan", "Perez");
        //Act

        try {
            respuesta = odontologoControlador.crearOdontologo(odontologo).getStatusCode();
        } catch (Exception e) {

        }
        //Assert
        assertEquals(codigo_respuesta_esperado, respuesta);
    }

    @Test
    public void actualizacion_odontologo_exitosa_test() {
        //Arrange
        HttpStatusCode codigo_respuesta_esperado = HttpStatus.CREATED;
        HttpStatusCode respuesta = null;
        Odontologo odontologo = new Odontologo(123, "Juan", "Perez");
        try {
            odontologoControlador.crearOdontologo(odontologo);
            //Act
            odontologo.setNombre("Nombre modificado");
            odontologo.setApellido("Apellido modificado");
            respuesta = odontologoControlador.modificarOdontologo(odontologo).getStatusCode();
        } catch (Exception e) {

        }

        //Assert
        assertEquals(codigo_respuesta_esperado, respuesta);

    }

    @Test
    public void consulta_de_odontologo_exitosa_test() throws ValidacionExcepcion, NoSuchObjectException {
        //Arrange
        HttpStatusCode codigo_respuesta_esperado = HttpStatus.OK;
        HttpStatusCode respuesta = null;
        Odontologo odontologo = new Odontologo(54399, "Martin", "Robles");

        //Act
        odontologoControlador.crearOdontologo(odontologo);
        respuesta = odontologoControlador.consultarOdonotolog(odontologo.getMatricula()).getStatusCode();
        //Assert

        assertEquals(codigo_respuesta_esperado,respuesta);

    }

    @Test
    public void eliminacion_odontologo_exitosa_test() {
        //Arrange
        HttpStatusCode codigo_respuesta_esperado = HttpStatus.NO_CONTENT;
        HttpStatusCode respuesta = null;
        Odontologo odontologo = new Odontologo(123, "Juan", "Perez");
        try {
            odontologoControlador.crearOdontologo(odontologo);
            //Act
            respuesta = odontologoControlador.eliminarOdontologo(odontologo.getMatricula()).getStatusCode();


        } catch (Exception e) {

        }
        //Assert
        assertEquals(codigo_respuesta_esperado, respuesta);

    }

}
