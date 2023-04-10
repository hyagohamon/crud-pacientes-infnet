package com.hyago.crudpacientes.services;

import com.hyago.crudpacientes.models.Paciente;
import com.hyago.crudpacientes.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PacienteServiceTest {

    PacienteService pacienteService;
    @Mock
    PacienteRepository pacienteRepository;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.pacienteService = new PacienteService(pacienteRepository);
    }

    @Test
    @Order(1)
    void salvar() {
        Paciente novo = new Paciente(1L, "Hyago Hamon", "0502786123", LocalDate.of(1992, 7, 1), "Bacabal");
        Mockito.when(pacienteRepository.save(novo)).thenReturn(novo);
        Paciente cadastrado = pacienteService.salvar(novo);
        assertEquals(novo.getNome(), cadastrado.getNome());

    }


    @Test
    void find() {
        Paciente paciente = new Paciente(1L, "Pedro Alves", "050278123654", LocalDate.of(1999, 8, 9), "São Luis");
        Mockito.when(pacienteRepository.findById(paciente.getId())).thenReturn(Optional.of(paciente));

        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(1L);

        assertEquals(paciente.getNome(), pacienteEncontrado.get().getNome());

    }


    //
    @Test
    void findAll() {
        Mockito.when(pacienteRepository.findAll()).thenReturn(pacientes());

        List<Paciente> list = pacienteService.buscarTodos();

        assertEquals(2, list.size());
    }

    private List<Paciente> pacientes() {
        Paciente p1 = new Paciente(1L, "Pedro Alves", "050278123654", LocalDate.of(1999, 8, 9), "São Luis");
        Paciente p2 = new Paciente(2L, "Hyago Hamon", "0502786123", LocalDate.of(1992, 7, 1), "Bacabal");
        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(p2);
        pacientes.add(p1);
        return pacientes;
    }

    @Test
    void delete() {
        pacienteService.excluir(1L);
        Mockito.verify(pacienteRepository).deleteById(1L);
    }

}