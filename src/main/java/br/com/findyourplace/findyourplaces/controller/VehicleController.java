package br.com.findyourplace.findyourplaces.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import br.com.findyourplace.findyourplaces.controller.dto.request.UpdateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.UpdateVehicleResponseDTO;
import br.com.findyourplace.findyourplaces.exceptions.ExceptionResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateVehicleResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListVehiclesResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ResponseAPIDefault;
import br.com.findyourplace.findyourplaces.service.VehicleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Veículos", description = "Cadastro, consulta e atualização de veículos")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        super();
        this.vehicleService = vehicleService;
    }

    @PreAuthorize("hasAuthority('SCOPE_vehicles:write')")
    @PostMapping()
    @Operation(summary = "Cadastrar veículo", description = "Cadastra um veículo para o usuário autenticado.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do veículo inválidos", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token sem o escopo vehicles:write", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Usuário inativo", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<ResponseAPIDefault<CreateVehicleResponseDTO>> createVehicle(
            @Valid @RequestBody CreateVehicleRequestDTO dto,
            @Parameter(hidden = true) JwtAuthenticationToken token) {

        var userId = UUID.fromString(token.getToken().getSubject());

        var vehicle = this.vehicleService.createVehicle(dto, userId);
        var location = URI.create("/vehicles/" + vehicle.id());

        return ResponseEntity.created(location)
                .body(new ResponseAPIDefault<>("Veículo cadastrado com sucesso", vehicle));
    }

    @PreAuthorize("hasAuthority('SCOPE_vehicles:read')")
    @GetMapping(path = "/me")
    @Operation(summary = "Listar meus veículos", description = "Retorna todos os veículos pertencentes ao usuário autenticado.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículos encontrados"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token sem o escopo vehicles:read", content = @Content)
    })
    public ResponseEntity<ResponseAPIDefault<List<ListVehiclesResponseDTO>>> list(
            @Parameter(hidden = true) JwtAuthenticationToken token) {

        var userId = UUID.fromString(token.getToken().getSubject());
        var vehicles = this.vehicleService.findAllByUserId(userId);

        return ResponseEntity.ok()
                .body(new ResponseAPIDefault<>(
                        "Veículos encontrados",
                        vehicles));
    }

    @PreAuthorize("hasAuthority('SCOPE_vehicles:read')")
    @GetMapping(path = "/{vehicleId}")
    @Operation(summary = "Consultar veículo", description = "Retorna um veículo do usuário autenticado pelo identificador.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token sem o escopo vehicles:read", content = @Content),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<ResponseAPIDefault<ListVehiclesResponseDTO>> listById(
            @Parameter(description = "Identificador do veículo", required = true) @PathVariable("vehicleId") UUID vehicleId,
            @Parameter(hidden = true) JwtAuthenticationToken token) {

        var userId = UUID.fromString(token.getToken().getSubject());
        var vehicle = this.vehicleService.findById(vehicleId, userId);

        return ResponseEntity.ok()
                .body(new ResponseAPIDefault<>(
                        "Veículo encontrado",
                        vehicle));
    }

    @PreAuthorize("hasAuthority('SCOPE_vehicles:read')")
    @PatchMapping(path = "/{vehicleId}")
    @Operation(summary = "Atualizar veículo", description = "Atualiza parcialmente um veículo pertencente ao usuário autenticado.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização inválidos", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token sem o escopo vehicles:read", content = @Content),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<ResponseAPIDefault<UpdateVehicleResponseDTO>> update(
            @Valid @RequestBody UpdateVehicleRequestDTO dto,
            @Parameter(description = "Identificador do veículo", required = true) @PathVariable("vehicleId") UUID vehicleId,
            @Parameter(hidden = true) JwtAuthenticationToken token) {

        var userId = UUID.fromString(token.getToken().getSubject());
        var vehicle = this.vehicleService.update(dto, vehicleId, userId);

        return ResponseEntity.ok()
                .body(new ResponseAPIDefault<>(
                        "Veículo atualizado com sucesso",
                        vehicle));
    }

    @PreAuthorize("hasAuthority('SCOPE_vehicles:read')")
    @DeleteMapping(path = "/{vehicleId}")
    @Operation(summary = "Excluir veículo", description = "Exclui um veículo pertencente ao usuário autenticado.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo excluído com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token sem o escopo vehicles:read", content = @Content),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<ResponseAPIDefault<Void>> deleteById(
            @Parameter(description = "Identificador do veículo", required = true) @PathVariable("vehicleId") UUID vehicleId,
            @Parameter(hidden = true) JwtAuthenticationToken token) {

        var userId = UUID.fromString(token.getToken().getSubject());
        this.vehicleService.delete(vehicleId, userId);

        return ResponseEntity.ok()
                .body(new ResponseAPIDefault<>(
                        "Veículo excluído",
                        null));
    }
}
