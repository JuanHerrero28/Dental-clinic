package com.example.ClinicaOdontologicaHerreroNakayama.repository;

import com.example.ClinicaOdontologicaHerreroNakayama.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente,Long> {


    Optional<Paciente> findByEmail(String email);
}
