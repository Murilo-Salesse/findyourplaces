package br.com.findyourplace.findyourplaces.controller.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTripRequestDTO(UUID vehicleId,
                                   String title,
                                   String originCity,
                                   String originState,
                                   String destinationCity,
                                   String destinationState,
                                   BigDecimal budget,
                                   Integer travelersCount,
                                   String transportType,
                                   LocalDateTime departureAt,
                                   LocalDateTime returnAt) {
}
