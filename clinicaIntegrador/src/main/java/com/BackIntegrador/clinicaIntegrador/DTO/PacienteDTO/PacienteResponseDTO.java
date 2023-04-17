package com.BackIntegrador.clinicaIntegrador.DTO.PacienteDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PacienteResponseDTO {
    private String nombre,apellido,email;
    private Integer dni;
    private String domicilio;


}
