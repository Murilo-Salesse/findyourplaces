package br.com.findyourplace.findyourplaces.controller;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateTripRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateTripResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
import br.com.findyourplace.findyourplaces.service.TripService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PreAuthorize("hasAuthority('SCOPE_trips:write')")
    @PostMapping
    public ResponseEntity<ResponseAPIDefault<CreateTripResponseDTO>> create(@Valid @RequestBody CreateTripRequestDTO dto,
                                                                            JwtAuthenticationToken token) {

        UUID userId = UUID.fromString(token.getToken().getSubject());
        var trip = this.tripService.create(dto, userId);

        return ResponseEntity.ok().body(new ResponseAPIDefault<>("Viagem criada com sucesso", trip));
    }
}
