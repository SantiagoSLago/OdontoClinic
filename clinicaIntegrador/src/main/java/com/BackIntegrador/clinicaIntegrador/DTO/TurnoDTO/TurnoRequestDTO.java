package com.BackIntegrador.clinicaIntegrador.DTO.TurnoDTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TurnoRequestDTO {

    private Integer id;

    private Integer dni_paciente;

    private Integer matricula_odontologo;

    private LocalDateTime fecha_turno;

    public TurnoRequestDTO() {
    }

    public TurnoRequestDTO(Integer dni_paciente, Integer matricula_odontologo, LocalDateTime fecha_turno) {
        this.dni_paciente = dni_paciente;
        this.matricula_odontologo = matricula_odontologo;
        this.fecha_turno = fecha_turno;
    }
}
