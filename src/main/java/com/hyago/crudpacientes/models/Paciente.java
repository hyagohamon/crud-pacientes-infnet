package com.hyago.crudpacientes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 140)
    private String nome;
    @NotBlank
    @Column(unique = true)
    private String cpf;
    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate nascimento;
    private String cidade;
}
