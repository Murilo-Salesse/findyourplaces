package br.com.findyourplace.findyourplaces.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.findyourplace.findyourplaces.enums.UserStatus;

public record CreateUserResponseDTO(UUID id,
								    String name,
								    String email,
								    String phone,
								    UserStatus status,
								    LocalDateTime createdAt) {}
