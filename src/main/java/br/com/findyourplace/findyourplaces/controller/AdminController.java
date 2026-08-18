package br.com.findyourplace.findyourplaces.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateAdminRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateAdminResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
import br.com.findyourplace.findyourplaces.exceptions.ExceptionResponse;
import br.com.findyourplace.findyourplaces.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Administração", description = "Configuração inicial do administrador do sistema")
public class AdminController {

	
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@PostMapping(path = "/setup-admin")
	@Operation(summary = "Configurar administrador inicial", description = "Cria o administrador inicial quando ainda não existem usuários cadastrados no sistema.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Administrador cadastrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados do administrador inválidos", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "422", description = "O sistema já possui usuários cadastrados", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
	})
	public ResponseEntity<ResponseAPIDefault<CreateAdminResponseDTO>> createAdmin(
			@Valid @RequestBody CreateAdminRequestDTO dto) {

		var admin = this.adminService.setupAdmin(dto);

		return ResponseEntity.ok().body(new ResponseAPIDefault<>("Admin cadastrado com sucesso", admin));
	}
}
