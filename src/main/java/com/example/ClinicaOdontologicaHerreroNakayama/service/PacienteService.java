package com.example.ClinicaOdontologicaHerreroNakayama.service;

import com.example.ClinicaOdontologicaHerreroNakayama.dto.PacienteDTO;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Domicilio;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Paciente;
import com.example.ClinicaOdontologicaHerreroNakayama.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteDTO registrarPaciente(Paciente paciente){
        Paciente pacienteRegistrado = pacienteRepository.save(paciente);
        return pacienteApacienteDTO(pacienteRegistrado);
    }

    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    public Optional<Paciente> buscarPacientePorID(Long id){
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorCorreo(String email){
        return pacienteRepository.findByEmail(email);
    }
    public List<Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }

    private PacienteDTO pacienteApacienteDTO(Paciente paciente){
        PacienteDTO respuesta= new PacienteDTO();
        respuesta.setId(paciente.getId());
        respuesta.setNombre(paciente.getNombre());
        respuesta.setApellido(paciente.getApellido());
        respuesta.setCedula(paciente.getCedula());
        respuesta.setFechaIngreso(paciente.getFechaIngreso());
        // Verifica si el domicilio en el paciente no es nulo antes de asignarlo
        if (paciente.getDomicilio() != null) {
            Domicilio domicilio = new Domicilio(
                    paciente.getDomicilio().getCalle(),
                    paciente.getDomicilio().getNumero(),
                    paciente.getDomicilio().getLocalidad(),
                    paciente.getDomicilio().getProvincia()
            );
            respuesta.setDomicilio(domicilio);
        } else {
            respuesta.setDomicilio(new Domicilio()); // Asigna un Domicilio vacío si es null en Paciente
        }

        respuesta.setEmail(paciente.getEmail());

        return respuesta;
    }
}
