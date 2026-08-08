package br.com.findyourplace.findyourplaces.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findyourplace.findyourplaces.controller.dto.CreateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.ResponseAPIDefault;
import br.com.findyourplace.findyourplaces.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@PostMapping(path = "/auth/register")
	public ResponseEntity<ResponseAPIDefault<CreateUserResponseDTO>> createUser(@Valid @RequestBody CreateUserRequestDTO dto) {

	    var user = this.userService.createUser(dto);
	    var location = URI.create("/users/" + user.id());

	    return ResponseEntity
	            .created(location)
	            .body(new ResponseAPIDefault<>(
	                    "Usuário cadastrado com sucesso",
	                    user
	            ));
	}
}
