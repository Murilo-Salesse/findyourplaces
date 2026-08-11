package br.com.findyourplace.findyourplaces.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "E-mail deve ser válido.")
        String email,

        @NotBlank(message = "Senha é obrigatória.")
        String password) {}
