package br.com.findyourplace.findyourplaces.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListAllUsersResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
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
	
	@GetMapping(path = "/list")
	public ResponseEntity<ResponseAPIDefault<List<ListAllUsersResponseDTO>>> listAll() {
		
	    return ResponseEntity
	            .ok()
	            .body(new ResponseAPIDefault<>(
	                    "Usuários listados com sucesso",
	                    this.userService.listAll()
	             ));
	}
}
