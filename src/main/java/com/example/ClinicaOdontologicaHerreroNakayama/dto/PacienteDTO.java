package com.example.ClinicaOdontologicaHerreroNakayama.dto;

import com.example.ClinicaOdontologicaHerreroNakayama.entity.Domicilio;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private LocalDate fechaIngreso;
    private Domicilio domicilio;
    private String email;

}
