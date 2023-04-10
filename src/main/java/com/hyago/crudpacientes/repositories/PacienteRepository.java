package com.hyago.crudpacientes.repositories;

import com.hyago.crudpacientes.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
