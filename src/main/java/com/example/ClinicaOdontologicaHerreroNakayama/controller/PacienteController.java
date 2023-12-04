package com.example.ClinicaOdontologicaHerreroNakayama.controller;

import com.example.ClinicaOdontologicaHerreroNakayama.dto.PacienteDTO;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Odontologo;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Paciente;
import com.example.ClinicaOdontologicaHerreroNakayama.exception.BadRequestException;
import com.example.ClinicaOdontologicaHerreroNakayama.exception.CustomException;
import com.example.ClinicaOdontologicaHerreroNakayama.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>>  buscarPacientePorID(@PathVariable Long id) throws CustomException {
        Optional<Paciente> paciente = pacienteService.buscarPacientePorID(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente);
        } else {
            throw new CustomException("Paciente no encontrado con ID: " + id);
        }
    }
    @PostMapping
    public  ResponseEntity<PacienteDTO> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("paciente actualizado");
        } else {
            throw new BadRequestException("Paciente no encontrado para actualizar");
        }
    }
    @GetMapping("/buscar/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String email) throws CustomException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorCorreo(email);
        if(pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            throw new CustomException("Paciente no encontrado para el correo: " + email);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> elimninarPaciente(@PathVariable Long id) throws CustomException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Eliminado con éxito");
        } else {
            throw new CustomException("No se pudo eliminar: Paciente no encontrado con ID " + id);
        }

    }
    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> buscarTodos() throws CustomException {
        try {
            List<Paciente> pacientes = pacienteService.listarTodos();
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            throw new CustomException("Error al recuperar la lista de pacientes");
        }
    }
}
