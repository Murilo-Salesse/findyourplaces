package br.com.findyourplace.findyourplaces.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findyourplace.findyourplaces.controller.dto.request.LoginRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.LoginResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1")
public class AuthController {
	
	
	@PostMapping(path = "/auth/token")
	public ResponseEntity<ResponseAPIDefault<LoginResponseDTO>> getToken(@Valid LoginRequestDTO dto) {
		
		return null;
	}

}
