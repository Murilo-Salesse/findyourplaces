package br.com.findyourplace.findyourplaces.controller.dto.request;

import br.com.findyourplace.findyourplaces.enums.TransportType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTripRequestDTO(UUID vehicleId,
                                   @NotBlank(message = "O título da viagem é obrigatório")
                                   String title,
                                   @NotBlank(message = "A cidade de origem é obrigatória")
                                   String originCity,
                                   @NotBlank(message = "O estado de origem é obrigatório")
                                   String originState,
                                   @NotBlank(message = "A cidade de destino é obrigatória")
                                   String destinationCity,
                                   @NotBlank(message = "O estado de destino é obrigatório")
                                   String destinationState,
                                   @NotNull(message = "O orçamento é obrigatório")
                                   @PositiveOrZero(message = "O orçamento não pode ser negativo")
                                   BigDecimal budget,
                                   @NotNull(message = "A quantidade de viajantes é obrigatória")
                                   @Min(value = 1, message = "A viagem deve ter pelo menos um viajante")
                                   Integer travelersCount,
                                   @NotNull(message = "O tipo de transporte é obrigatório")
                                   TransportType transportType,
                                   @NotNull(message = "A data de partida é obrigatória")
                                   @Future(message = "A data de partida deve estar no futuro")
                                   LocalDateTime departureAt,
                                   @NotNull(message = "A data de retorno é obrigatória")
                                   @Future(message = "A data de retorno deve estar no futuro")
                                   LocalDateTime returnAt) {

    @AssertTrue(message = "A data de retorno deve ser posterior à data de partida")
    public boolean isReturnAfterDeparture() {
        if (departureAt == null || returnAt == null) {
            return true;
        }

        return returnAt.isAfter(departureAt);
    }
}
