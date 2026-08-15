package br.com.findyourplace.findyourplaces.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateVehiclesResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
import br.com.findyourplace.findyourplaces.service.VehicleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
	
	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}
	
	
	@PreAuthorize("hasAuthority('SCOPE_vehicles:write')")
	@PostMapping(path = "/vehicles")
	public ResponseEntity<ResponseAPIDefault<CreateVehiclesResponseDTO>> createUser(@Valid @RequestBody CreateVehicleRequestDTO dto,
																					JwtAuthenticationToken token) {

		var userID = UUID.fromString(token.getToken().getSubject());
		
	    var vehicle = this.vehicleService.createVehicle(dto, userID);
	    var location = URI.create("/vehicles/" + vehicle.id());

	    return ResponseEntity
	            .created(location)
	            .body(new ResponseAPIDefault<>(
	                    "Veículo cadastrado com sucesso",
	                    vehicle
	            ));
	}

	
	@PreAuthorize("hasAuthority('SCOPE_vehicles:write')")
	@GetMapping(path = "/me/vehicles")
	public ResponseEntity<ResponseAPIDefault<CreateVehiclesResponseDTO>> list(@Valid @RequestBody CreateVehicleRequestDTO dto,
																			  JwtAuthenticationToken token) {

			var userID = UUID.fromString(token.getToken().getSubject());
			
			var vehicle = this.vehicleService.createVehicle(dto, userID);
			var location = URI.create("/vehicles/" + vehicle.id());
			
			return ResponseEntity
			.created(location)
			.body(new ResponseAPIDefault<>(
			"Veículo cadastrado com sucesso",
			vehicle
			));
	}
}
