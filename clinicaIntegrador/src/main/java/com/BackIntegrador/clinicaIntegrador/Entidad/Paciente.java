package com.BackIntegrador.clinicaIntegrador.Entidad;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Paciente {


    @Id
    private Integer dni;
    private String nombre, apellido, email;
    private Date fecha_alta;
    @OneToOne
    private Domicilio domicilio;




    public Paciente(Integer dni, String nombre, String apellido, String email, Domicilio domicilio, Date fecha_alta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fecha_alta = fecha_alta;
        this.email = email;

    }

    public Paciente() {
    }


}
