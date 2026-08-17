package br.com.findyourplace.findyourplaces.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record UpdatedVehicleRequestDTO(@NotBlank @Length(min = 3, max = 60) String nickname,
                                       @NotBlank @Length(min = 3, max = 60) String brand,
                                       @NotBlank @Length(min = 3, max = 60) String model,
                                       @NotNull @Positive Integer year,
                                       @NotBlank @Length(min = 3, max = 60) String fuelType,
                                       @NotNull @Positive BigDecimal cityConsumptionKmL,
                                       @NotNull @Positive BigDecimal highwayConsumptionKmL,
                                       @NotNull @Positive BigDecimal tankCapacityLiters) {}
