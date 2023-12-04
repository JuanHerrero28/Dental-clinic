package com.example.ClinicaOdontologicaHerreroNakayama.service;

import com.example.ClinicaOdontologicaHerreroNakayama.dto.TurnoDTO;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Odontologo;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Paciente;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Turno;
import com.example.ClinicaOdontologicaHerreroNakayama.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoGuardado= turnoRepository.save(turno);
        return turnoATurnoDTO(turnoGuardado);
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    public void actualizarTurno(TurnoDTO turnodto){
        turnoRepository.save(turnoDTOaTurno(turnodto));
    }
    public Optional<TurnoDTO> buscarTurnoPorId(Long id){
        Optional<Turno> turnoBuscado =turnoRepository.findById(id);
        if(turnoBuscado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        return Optional.empty();
    }
    public List<TurnoDTO> buscarTodos(){
        List<Turno> turnosEncontrados= turnoRepository.findAll();
        List<TurnoDTO> listaDTO= new ArrayList<>();
        //recorremos esa lista
        for (Turno turno : turnosEncontrados) {
            listaDTO.add(turnoATurnoDTO(turno));

        }
        return listaDTO;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO respuesta= new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFechaTurno(turno.getFechaTurno());
        return  respuesta;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Odontologo odontologo= new Odontologo();
        Paciente paciente= new Paciente();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setFechaTurno(turnoDTO.getFechaTurno());
        turno.setId(turnoDTO.getId());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return turno;
    }
}
