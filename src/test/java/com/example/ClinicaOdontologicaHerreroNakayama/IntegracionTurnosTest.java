package com.example.ClinicaOdontologicaHerreroNakayama;

import com.example.ClinicaOdontologicaHerreroNakayama.dto.TurnoDTO;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Domicilio;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Odontologo;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Paciente;
import com.example.ClinicaOdontologicaHerreroNakayama.entity.Turno;
import com.example.ClinicaOdontologicaHerreroNakayama.service.OdontologoService;
import com.example.ClinicaOdontologicaHerreroNakayama.service.PacienteService;
import com.example.ClinicaOdontologicaHerreroNakayama.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //nececito desactivar la seguridad en la clase
public class IntegracionTurnosTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void agregarDatosIniciales(){
        Paciente paciente= new Paciente("Jorgito","Pereyra","11111", LocalDate.of(2023,11,28),new Domicilio("Calle ",1,"La Rioja","La Rioja"),"jorge.pereyra@digitalhouse.com");
        Odontologo odontologo= new Odontologo("123","Maria de Los Angeles","Magaz");
        Turno turno= new Turno(paciente,odontologo,LocalDate.of(2023,12,01));
        pacienteService.registrarPaciente(paciente);
        odontologoService.registrarOdontologo(odontologo);
        turnoService.guardarTurno(turno);
    }
    @Test
    public void listarTurnosTest() throws Exception{
        agregarDatosIniciales();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void registrarTurnoTest() throws Exception {
        agregarDatosIniciales();
        // JSON del nuevo turno
        String nuevoTurnoJSON = "{\"fechaTurno\":\"2023-10-25\",\"paciente\":{\"id\":1},\"odontologo\":{\"id\":1}}";

        // Realizar la solicitud POST para registrar un nuevo turno usando el JSON
        mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .content(nuevoTurnoJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void buscarPorIdTest() throws Exception {
        // Asegurarse de tener datos iniciales (paciente, odontólogo y turno) antes de buscar por ID
        agregarDatosIniciales();

        // Obtener el ID del último turno creado en agregarDatosIniciales()
        List<TurnoDTO> turnos = turnoService.buscarTodos();
        //turnos.size() - 1 obtiene el índice del último elemento en la lista turnos. turnos.size() devuelve el tamaño total de la lista de turnos, y al restarle 1, obtenemos el índice del último elemento de la lista. En Java, los índices de las
        // listas comienzan desde 0, por lo que el último elemento tiene un índice de turnos.size() - 1.
        long ultimoTurnoId = turnos.get(turnos.size() - 1).getId();

        // Realizar la solicitud GET para buscar el turno por su ID
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/{id}", ultimoTurnoId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void borrarTurnoTest() throws Exception {
        // Asegurarse de tener datos iniciales (paciente, odontólogo y turno) antes de intentar borrar
        agregarDatosIniciales();

        // Obtener el ID del último turno creado en agregarDatosIniciales()
        List<TurnoDTO> turnos = turnoService.buscarTodos();
        long ultimoTurnoId = turnos.get(turnos.size() - 1).getId();

        // Realizar la solicitud DELETE para borrar el turno por su ID
        mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/{id}", ultimoTurnoId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verificar que el turno se ha eliminado (por ejemplo, comprobando si está presente)
        assertFalse(turnoService.buscarTurnoPorId(ultimoTurnoId).isPresent());
    }

    @Test
    public void actualizarTurnoTest() throws Exception {
        // Asegurarse de tener datos iniciales (paciente, odontólogo y turno) antes de actualizar
        agregarDatosIniciales();

        // Obtener el ID del último turno creado en agregarDatosIniciales()
        List<TurnoDTO> turnos = turnoService.buscarTodos();
        long ultimoTurnoId = turnos.get(turnos.size() - 1).getId();

        // JSON con la información actualizada del turno
        String turnoActualizadoJSON = "{\"id\": " + ultimoTurnoId + ", \"pacienteId\": 1, \"odontologoId\": 1, \"fechaTurno\": \"2024-01-15\"}";

        // Realizar la solicitud PUT para actualizar el turno usando el JSON actualizado
        mockMvc.perform(MockMvcRequestBuilders.put("/turnos")
                        .content(turnoActualizadoJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verificar que el turno se ha actualizado correctamente (por ejemplo, comprobando si los datos coinciden)
        TurnoDTO turnoActualizado = turnoService.buscarTurnoPorId(ultimoTurnoId).orElse(null);
        assertNotNull(turnoActualizado);
        assertEquals(LocalDate.parse("2024-01-15"), turnoActualizado.getFechaTurno());

    }




}
