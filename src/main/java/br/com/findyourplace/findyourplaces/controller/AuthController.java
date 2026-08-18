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
import br.com.findyourplace.findyourplaces.exceptions.ExceptionResponse;
import br.com.findyourplace.findyourplaces.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "Autenticação de usuários e emissão de tokens de acesso")
public class AuthController {

	private final AuthService authService;
	private final AuthenticationManager authenticationManager;

	public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
		this.authService = authService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/token")
    @Operation(summary = "Autenticar usuário", description = "Valida e-mail e senha e emite um token JWT com os escopos do usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de autenticação inválidos", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
	public ResponseEntity<ResponseAPIDefault<LoginResponseDTO>> getToken(@Valid @RequestBody LoginRequestDTO dto) {

		Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(dto.email(),
				dto.password());

		Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

		var token = authService.generateToken(authenticationResponse);

		return ResponseEntity.ok(new ResponseAPIDefault<>("Login realizado com sucesso", token));
	}
}
