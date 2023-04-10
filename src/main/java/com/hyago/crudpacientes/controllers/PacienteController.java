package com.hyago.crudpacientes.controllers;

import com.hyago.crudpacientes.models.Paciente;
import com.hyago.crudpacientes.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Paciente paciente) {
        Paciente cadastrado = pacienteService.salvar(paciente);

        return ResponseEntity.ok(cadastrado);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.find(id);

        return ResponseEntity.ok(paciente.orElse(null));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        pacienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid Paciente paciente) {
        Paciente pacienteBuscado = pacienteService.find(id).get();
        pacienteBuscado.setNome(paciente.getNome());
        pacienteBuscado.setCidade(paciente.getCidade());
        pacienteBuscado.setCpf(paciente.getCpf());
        pacienteBuscado.setNascimento(paciente.getNascimento());
        pacienteService.salvar(pacienteBuscado);
        return ResponseEntity.ok(pacienteBuscado);
    }


}
