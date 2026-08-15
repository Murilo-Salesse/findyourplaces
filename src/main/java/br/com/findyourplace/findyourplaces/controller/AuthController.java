package br.com.findyourplace.findyourplaces.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findyourplace.findyourplaces.controller.dto.request.LoginRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.LoginResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
import br.com.findyourplace.findyourplaces.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;
	private final AuthenticationManager authenticationManager;

	public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
		this.authService = authService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/token")
	public ResponseEntity<ResponseAPIDefault<LoginResponseDTO>> getToken(@Valid @RequestBody LoginRequestDTO dto) {

		Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(dto.email(),
				dto.password());

		Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

		var token = authService.generateToken(authenticationResponse);

		return ResponseEntity.ok(new ResponseAPIDefault<>("Login realizado com sucesso", token));
	}
}