package br.com.findyourplace.findyourplaces.controller.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ListVehiclesResponseDTO(UUID    id,
									  String  nickname,
									  String  brand,
									  String  model,
									  Integer year,
									  String  fuelType,
								      BigDecimal cityConsumptionKmL,
								      BigDecimal highwayConsumptionKmL,
									  BigDecimal tankCapacityLiters,
									  Boolean    active) {}
