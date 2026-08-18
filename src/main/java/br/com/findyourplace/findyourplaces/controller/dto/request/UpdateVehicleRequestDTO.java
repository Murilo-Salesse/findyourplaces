package br.com.findyourplace.findyourplaces.controller.dto.request;

import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record UpdateVehicleRequestDTO(@Length(min = 3, max = 60)
                                      String nickname,

                                      @Length(min = 3, max = 60)
                                      String brand,

                                      @Length(min = 3, max = 60)
                                      String model,

                                      @Positive
                                      Integer year,

                                      @Length(min = 3, max = 60)
                                      String fuelType,

                                      @Positive
                                      BigDecimal cityConsumptionKmL,

                                      @Positive
                                      BigDecimal highwayConsumptionKmL,

                                      @Positive
                                      BigDecimal tankCapacityLiters) {
}
