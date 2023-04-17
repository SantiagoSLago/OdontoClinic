package com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
public class TurnoResponseDTO {
    private Integer id_turno;

    private String nombre_paciente, apellido_paciente, nombre_odontologo, apellido_odontologo;

    private String fecha_turno;

    public TurnoResponseDTO() {
    }





}
