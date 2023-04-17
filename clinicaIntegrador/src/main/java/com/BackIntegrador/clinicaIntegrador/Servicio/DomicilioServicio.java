package com.BackIntegrador.clinicaIntegrador.Servicio;


import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import com.BackIntegrador.clinicaIntegrador.Excepciones.ValidacionExcepcion;
import com.BackIntegrador.clinicaIntegrador.Repositorio.DomicilioRepositorio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServicio implements Comparator<Domicilio> {

    private static final Logger LOGGER = Logger.getLogger(DomicilioServicio.class);

    @Autowired
    DomicilioRepositorio domicilioRepositorio;

    public Domicilio crearDomicilio2(Domicilio domicilio) throws ValidacionExcepcion {
        validacionDatos(domicilio);
        domicilioRepositorio.save(domicilio);
        return ultimoDomicilioRegistrado();


    }


    public Domicilio buscarPorId(Integer id) throws NoSuchObjectException {
        Domicilio domicilio = null;

        Optional<Domicilio> respuesta = domicilioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            domicilio = respuesta.get();
        } else {
            throw new NoSuchObjectException("El domicilio solicitado no se encuentra registrado");
        }
        return domicilio;
    }

    public List<Domicilio> listarDomicilios() {
        return domicilioRepositorio.findAll();
    }

    public Domicilio ultimoDomicilioRegistrado() {


        List<Domicilio> domicilios = domicilioRepositorio.listaInvertidaDomicilios();
        Domicilio domicilio = null;
        for (Domicilio d : domicilios) {
            domicilio = d;
            break;
        }

        return domicilio;
    }

    public void validacionDatos(Domicilio domicilio) throws ValidacionExcepcion {

        if (domicilio.getNumero() == null) {
            throw new ValidacionExcepcion("La numeracion debe ser valida o no debe estar vacia");
        }
        if ((domicilio.getCalle() == null) || domicilio.getCalle().isBlank()) {
            throw new ValidacionExcepcion("La calle debe ser valida o no debe estar vacia");
        }
        if ((domicilio.getCiudad() == null) || (domicilio.getCiudad().isBlank())) {
            throw new ValidacionExcepcion("La ciudad debe ser valida o no debe estar vacia");
        }
        if ((domicilio.getProvincia() == null) || (domicilio.getProvincia().isBlank())) {
            throw new ValidacionExcepcion("La provincia debe ser valida o no debe estar vacia");
        }
        if ((domicilio.getPais() == null) || (domicilio.getPais().isBlank())) {
            throw new ValidacionExcepcion("El pais debe ser valido o no debe estar vacio");
        }

    }

    public void eliminarDomicilio(Integer id) {


        try {
            Domicilio domicilio = buscarPorId(id);
            LOGGER.info("Eliminando domicilio: " + domicilio.toString());
            domicilioRepositorio.delete(domicilio);
            LOGGER.info("Domicilio eliminado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public Domicilio actualizarDomicilio(Domicilio domicilio) throws NoSuchObjectException {

        Domicilio d = buscarPorId(domicilio.getId_domicilio());

        if (d != null) {
            d.setNumero(domicilio.getNumero());
            d.setCalle(domicilio.getCalle());
            d.setCiudad(domicilio.getCiudad());
            d.setProvincia(domicilio.getProvincia());
            d.setPais(domicilio.getPais());


            domicilioRepositorio.save(d);
            LOGGER.info("Domicilio Actualizado: " + d.toString());
            return buscarPorId(d.getId_domicilio());
        } else {
            System.out.println("Domicilio no encontrado: manejo provisorio");
            return null;
        }


    }


    @Override
    public int compare(Domicilio o1, Domicilio o2) {
        int resultado = -1;

        if (o1.getNumero().equals(o2.getNumero())) {
            if (o1.getCalle().equals(o2.getCalle())) {
                if (o1.getCiudad().equals(o2.getCiudad())) {
                    if (o1.getProvincia().equals(o2.getProvincia())) {
                        if (o1.getPais().equals(o2.getPais())) {
                            resultado = 0;
                        }
                    }
                }
            }
        }

        return resultado;
    }
}
