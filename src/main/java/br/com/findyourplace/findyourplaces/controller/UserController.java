package br.com.findyourplace.findyourplaces.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.request.UpdateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListAllUsersResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListInfosUserDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
import br.com.findyourplace.findyourplaces.controller.dto.response.UpdateProfileInfosResponseDTO;
import br.com.findyourplace.findyourplaces.exceptions.ExceptionResponse;
import br.com.findyourplace.findyourplaces.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Usuários", description = "Cadastro, consulta e atualização de usuários")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Cria uma nova conta de usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de cadastro inválidos", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Nome ou e-mail já cadastrado", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<ResponseAPIDefault<CreateUserResponseDTO>> createUser(@Valid @RequestBody CreateUserRequestDTO dto) {

		var user = this.userService.createUser(dto);
		var location = URI.create("/users/" + user.id());

		return ResponseEntity.created(location).body(new ResponseAPIDefault<>("Usuário cadastrado com sucesso", user));
	}

	@PreAuthorize("hasAuthority('SCOPE_users:profile')")
	@PatchMapping(path = "/me")
    @Operation(summary = "Atualizar o próprio perfil", description = "Atualiza nome e/ou telefone do usuário autenticado.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização inválidos", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token sem o escopo users:profile", content = @Content)
    })
    public ResponseEntity<ResponseAPIDefault<UpdateProfileInfosResponseDTO>> updateUser(@Parameter(hidden = true) JwtAuthenticationToken token,
                                                                                        @Valid @RequestBody UpdateUserRequestDTO dto) {

		UUID userId = UUID.fromString(token.getToken().getSubject());
		var user = this.userService.update(userId, dto);

		return ResponseEntity.ok().body(new ResponseAPIDefault<>("Usuário atualizado com sucesso", user));
	}

	@PreAuthorize("hasAuthority('SCOPE_users:profile')")
	@GetMapping(path = "/me")
    @Operation(summary = "Consultar o próprio perfil", description = "Retorna os dados do usuário autenticado.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token sem o escopo users:profile", content = @Content)
    })
    public ResponseEntity<ResponseAPIDefault<ListInfosUserDTO>> findProfile(@Parameter(hidden = true) JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getToken().getSubject());

        var user = this.userService.findProfile(userId);

	    return ResponseEntity
	            .ok()
	            .body(new ResponseAPIDefault<>(
	                    "Usuário encontrado",
	                    user
	            ));
	}


    @PreAuthorize("hasAuthority('SCOPE_users:read')")
	@GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna todos os usuários cadastrados.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token sem o escopo users:read", content = @Content)
    })
	public ResponseEntity<ResponseAPIDefault<List<ListAllUsersResponseDTO>>> listAll() {

		return ResponseEntity.ok()
				.body(new ResponseAPIDefault<>("Usuários listados com sucesso", this.userService.listAll()));
	}
}
