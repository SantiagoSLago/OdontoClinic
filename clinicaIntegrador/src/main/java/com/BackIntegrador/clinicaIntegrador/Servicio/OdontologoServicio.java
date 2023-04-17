package com.BackIntegrador.clinicaIntegrador.Servicio;


import com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO.TurnoResponseDTO;
import com.BackIntegrador.clinicaIntegrador.Entidad.Odontologo;
import com.BackIntegrador.clinicaIntegrador.Entidad.Turno;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Repositorio.OdontologoRepositorio;
import com.BackIntegrador.clinicaIntegrador.Repositorio.TurnoRepositorio;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServicio implements Comparator<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoServicio.class);

    @Autowired
    OdontologoRepositorio odontologoRepositorio;

    @Autowired
    TurnoRepositorio turnoRepositorio;

    @Autowired
    PacienteServicio pacienteServicio;


    @Transactional
    public Odontologo crearOdontologo(Odontologo odontologo) throws ValidacionExcepcion {
        validacionDatos(odontologo);
        odontologoRepositorio.save(odontologo);
        LOGGER.info("Odontologo creado: " + "Matricula: " + odontologo.getMatricula() +
                " Nombre: " + odontologo.getNombre() + " Apellido: " + odontologo.getApellido());
        return ultimoOdontologoRegistrado();
    }

    public Odontologo buscarPorMatricula(Integer matricula) throws NoSuchObjectException {
        Odontologo odontologo = null;
        Optional<Odontologo> respuesta = odontologoRepositorio.findById(matricula);
        if (respuesta.isPresent()) {
            odontologo = respuesta.get();
        } else {
            throw new NoSuchObjectException("El odontologo solicitado no se encuentra registrado");
        }
        return odontologo;

    }

    public List<Odontologo> listarOdontologos() {
        return odontologoRepositorio.findAll();
    }

    @Transactional
    public Odontologo actualizarOdontologo(Odontologo odontologo) throws NoSuchObjectException, ValidacionExcepcion {

        if (odontologo.getMatricula() != null) {
            if ((odontologo.getNombre().isBlank()) && (odontologo.getApellido().isBlank())) {
                throw new ValidacionExcepcion("El nombre o el apellido del odontologo deben tener algun valor");
            }

            Odontologo o = buscarPorMatricula(odontologo.getMatricula());
            if (odontologo.getMatricula() != null) {
                o.setMatricula(odontologo.getMatricula());
            }
            if (!odontologo.getNombre().equals("")) {
                o.setNombre(odontologo.getNombre());
            }
            if (!odontologo.getApellido().equals("")) {
                o.setApellido(odontologo.getApellido());
            }
            odontologoRepositorio.save(o);
            LOGGER.info("Odontologo Actualizado: " + "Matricula: " + o.getMatricula() +
                    " Nombre: " + o.getNombre() + " Apellido: " + o.getApellido());
            return o;
        } else {
            throw new ValidacionExcepcion("La matricula no puede ser nula ni estar vacia");
        }


    }

    @Transactional
    public String eliminarOdontologo(Integer matricula) throws NoSuchObjectException, ValidacionExcepcion {


        String mensaje = null;
        Odontologo o = buscarPorMatricula(matricula);
        for (Turno t: turnoRepositorio.findAll()){
            if (t.getOdontologo().getMatricula().equals(matricula)){
                turnoRepositorio.delete(t);
            }
        }




        odontologoRepositorio.delete(o);
        LOGGER.info("Odontologo Eliminado: " + "Matricula: " + matricula);
        mensaje = "Odontologo eliminado";

        return mensaje;

    }



    public Odontologo ultimoOdontologoRegistrado() {
        Odontologo odontologo = null;
        List<Odontologo> odontologos = odontologoRepositorio.listaInvertidaOdontologos();
        for (Odontologo o : odontologos) {
            odontologo = o;
            break;
        }
        return odontologo;
    }

    public void validacionDatos(Odontologo odontologo) throws ValidacionExcepcion {
        if ((odontologo.getNombre() == null) || (odontologo.getNombre().isBlank())) {
            throw new ValidacionExcepcion("EL nombre debe ser valido o no debe estar vacio");
        }

        if ((odontologo.getApellido() == null) || (odontologo.getApellido().isBlank())) {
            throw new ValidacionExcepcion("El apellido debe ser valido o no debe estar vacio");
        }
        if (odontologo.getMatricula() == null) {
            throw new ValidacionExcepcion("La matricula debe ser valida o no debe estar vacia");
        }


    }





    @Override
    public int compare(Odontologo o1, Odontologo o2) {
        int resultado = -1;
        if (o1.getNombre().equals(o2.getNombre())) {
            if (o1.getApellido().equals(o2.getApellido())) {
                if (o1.getMatricula().equals(o2.getMatricula())) {
                    resultado = 0;
                }
            }
        }

        return resultado;
    }
}
