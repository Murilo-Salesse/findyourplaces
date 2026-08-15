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
import br.com.findyourplace.findyourplaces.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<ResponseAPIDefault<CreateUserResponseDTO>> createUser(
			@Valid @RequestBody CreateUserRequestDTO dto) {

		var user = this.userService.createUser(dto);
		var location = URI.create("/users/" + user.id());

		return ResponseEntity.created(location).body(new ResponseAPIDefault<>("Usuário cadastrado com sucesso", user));
	}

	@PreAuthorize("hasAuthority('SCOPE_users:profile')")
	@PatchMapping(path = "/me")
	public ResponseEntity<ResponseAPIDefault<UpdateProfileInfosResponseDTO>> updateUser(JwtAuthenticationToken token,
			@Valid @RequestBody UpdateUserRequestDTO dto) {

		UUID userId = UUID.fromString(token.getToken().getSubject());
		var user = this.userService.update(userId, dto);

		return ResponseEntity.ok().body(new ResponseAPIDefault<>("Usuário atualizado com sucesso", user));
	}

	@PreAuthorize("hasAuthority('SCOPE_users:profile')")
	@GetMapping(path = "/me")
	public ResponseEntity<ResponseAPIDefault<ListInfosUserDTO>> listInfos(JwtAuthenticationToken token) {

		UUID userId = UUID.fromString(token.getToken().getSubject());
		var user = this.userService.listInfos(userId);

		return ResponseEntity.ok().body(new ResponseAPIDefault<>("Usuário encontrado", user));
	}

	@PreAuthorize("hasAuthority('SCOPE_users:read')")
	@GetMapping
	public ResponseEntity<ResponseAPIDefault<List<ListAllUsersResponseDTO>>> listAll() {

		return ResponseEntity.ok()
				.body(new ResponseAPIDefault<>("Usuários listados com sucesso", this.userService.listAll()));
	}
}
