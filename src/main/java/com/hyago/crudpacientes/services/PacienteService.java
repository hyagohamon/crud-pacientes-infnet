package com.hyago.crudpacientes.services;

import com.hyago.crudpacientes.models.Paciente;
import com.hyago.crudpacientes.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger log = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    public Paciente salvar(Paciente paciente) {
        log.info("Realizando o cadastro do paciente " + paciente.getNome());

        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e) {
            log.error("Erro ao salvar o paciente.");
            throw new RuntimeException(e);
        }

    }

    public void excluir(Long id) {

        try {
            pacienteRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Erro ao deletar o paciente.");
            throw new RuntimeException(e);
        }


    }

    public Optional<Paciente> find(Long id) {
        return Optional.ofNullable(pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado")));
    }

    public List<Paciente> buscarTodos() {

        return pacienteRepository.findAll();
    }
}
