package br.com.findyourplace.findyourplaces.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateAdminRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateAdminResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
import br.com.findyourplace.findyourplaces.service.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

	
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@PostMapping(path = "/setup-admin")
	public ResponseEntity<ResponseAPIDefault<CreateAdminResponseDTO>> createAdmin(
			@Valid @RequestBody CreateAdminRequestDTO dto) {

		var admin = this.adminService.setupAdmin(dto);

		return ResponseEntity.ok().body(new ResponseAPIDefault<>("Admin cadastrado com sucesso", admin));
	}
}
