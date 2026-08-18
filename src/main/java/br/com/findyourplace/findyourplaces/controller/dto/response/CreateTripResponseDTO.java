package br.com.findyourplace.findyourplaces.controller.dto.response;

import br.com.findyourplace.findyourplaces.enums.TripStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTripResponseDTO(UUID id,
                                    String title,
                                    Origin origin,
                                    Destination destination,
                                    BigDecimal budget,
                                    Integer travelersCount,
                                    String transportType,
                                    Vehicle vehicle,
                                    LocalDateTime departureAt,
                                    LocalDateTime returnAt,
                                    TripStatus status,
                                    LocalDateTime createdAt) {


    public record Origin(
            String city,
            String state) {
    }


    public record Destination(
            String city,
            String state) {
    }

    public record Vehicle(
            UUID id,
            String nickname,
            String brand,
            String model,
            Integer year) {
    }

}
