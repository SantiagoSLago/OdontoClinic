package com.BackIntegrador.clinicaIntegrador.Repositorio;


import com.BackIntegrador.clinicaIntegrador.Entidad.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno,Integer> {


    @Query("SELECT t FROM Turno t ORDER BY t.id DESC")
    List<Turno> listaInvertidaTurnos();
}
