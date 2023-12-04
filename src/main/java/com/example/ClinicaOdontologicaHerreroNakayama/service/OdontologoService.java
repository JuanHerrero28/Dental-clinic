package com.example.ClinicaOdontologicaHerreroNakayama.service;

import com.example.ClinicaOdontologicaHerreroNakayama.dto.OdontologoDTO;
import com.example.ClinicaOdontologicaHerreroNakayama.dto.PacienteDTO;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Domicilio;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Odontologo;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Paciente;
import com.example.ClinicaOdontologicaHerreroNakayama.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public OdontologoDTO registrarOdontologo(Odontologo odontologo){
        Odontologo odontologoRegistrado = odontologoRepository.save(odontologo);
        return odontologoAodontologoDTO(odontologoRegistrado);

    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }
    public  void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }
    public List<Odontologo> listarTodos(){
        return odontologoRepository.findAll();
    }
    public Optional<Odontologo> buscarPorId(Long id){
        return odontologoRepository.findById(id);
    }
    public Optional<Odontologo> buscarPorMatricula(String matricula){
        return odontologoRepository.findByMatricula(matricula);
    }

    private OdontologoDTO odontologoAodontologoDTO(Odontologo odontologo) {
        OdontologoDTO respuesta = new OdontologoDTO();
        respuesta.setId(odontologo.getId());
        respuesta.setMatricula(odontologo.getMatricula());
        respuesta.setNombre(odontologo.getNombre());
        respuesta.setApellido(odontologo.getApellido());

        return respuesta;
    }

}
