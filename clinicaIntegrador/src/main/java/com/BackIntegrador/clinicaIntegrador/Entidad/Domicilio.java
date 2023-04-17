package com.BackIntegrador.clinicaIntegrador.Entidad;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Domicilio {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id_domicilio;

    @Getter
    @Setter
    private Integer numero;
    @Getter
    @Setter
    private String calle,ciudad,provincia,pais;

    public Domicilio() {
    }

    public Domicilio(Integer numero, String calle, String ciudad, String provincia, String pais) {
        this.numero = numero;
        this.calle = calle;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
    }


}
