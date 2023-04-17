package com.BackIntegrador.clinicaIntegrador.Repositorio;


import com.BackIntegrador.clinicaIntegrador.Entidad.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdontologoRepositorio extends JpaRepository<Odontologo,Integer> {


    @Query("SELECT o FROM Odontologo o ORDER BY o.id DESC")
    public List<Odontologo> listaInvertidaOdontologos();

}
