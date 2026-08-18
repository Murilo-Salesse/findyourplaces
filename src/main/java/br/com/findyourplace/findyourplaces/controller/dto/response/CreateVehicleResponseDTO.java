package br.com.findyourplace.findyourplaces.controller.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateVehicleResponseDTO(UUID id,
                                       String nickname,
                                       String brand,
                                       String model,
                                       Integer year,
                                       String fuelType,
                                       BigDecimal cityConsumptionKmL,
                                       BigDecimal highwayConsumptionKmL,
                                       BigDecimal tankCapacityLiters,
                                       Boolean active,
                                       LocalDateTime createdAt) {
}
