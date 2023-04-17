package com.BackIntegrador.clinicaIntegrador.Repositorio;


import com.BackIntegrador.clinicaIntegrador.Entidad.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepositorio extends JpaRepository<Domicilio,Integer> {



    @Query("SELECT e FROM Domicilio e ORDER BY e.id DESC")
    public List<Domicilio> listaInvertidaDomicilios();
}
