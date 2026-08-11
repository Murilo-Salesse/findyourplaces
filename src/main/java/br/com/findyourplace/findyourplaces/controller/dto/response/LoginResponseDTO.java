package br.com.findyourplace.findyourplaces.controller.dto.response;

public record LoginResponseDTO(String accessToken,
	     					   Long expiresIn) {}
