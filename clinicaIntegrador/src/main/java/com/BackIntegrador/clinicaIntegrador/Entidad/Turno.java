package com.BackIntegrador.clinicaIntegrador.Entidad;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne()
    @JoinColumn(name="odontologo_id")
    private Odontologo odontologo;
    private LocalDateTime fecha_turno;


    public Turno( Paciente paciente,  Odontologo odontologo, LocalDateTime fecha_turno) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha_turno = fecha_turno;
    }

    public Turno() {
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente.getNombre() + paciente.getApellido() + paciente.getDni() +
                ", odontologo=" + odontologo.getNombre() + odontologo.getApellido() + odontologo.getMatricula() +
                ", fecha_turno=" + fecha_turno +
                '}';
    }



}
