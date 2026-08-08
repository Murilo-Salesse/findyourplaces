package br.com.findyourplace.findyourplaces.controller.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDTO(@NotBlank @Length(min = 3, max = 20) String name,
		 						   @NotBlank @Email @Length(min = 3, max = 100)String email,
		 						   @NotBlank @Length(min = 4, max = 16) String password,
								   String phone) {}
