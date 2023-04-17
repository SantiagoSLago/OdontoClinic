package com.BackIntegrador.clinicaIntegrador.Repositorio;



import com.BackIntegrador.clinicaIntegrador.Entidad.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente,Integer> {


    @Query("Select p From Paciente p Where p.dni = :dni")
    public Paciente buscarPorDni(Integer dni);

    @Query("SELECT p FROM Paciente p ORDER BY p.id DESC")
    public List<Paciente> listaInvertidaPacientes();



}
