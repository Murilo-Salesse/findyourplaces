package br.com.findyourplace.findyourplaces.controller.dto.response;

import br.com.findyourplace.findyourplaces.enums.TripPlanStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TripPlanResponseDTO(
        UUID id,
        UUID tripId,
        TripPlanStatus status,
        String summary,
        String aiRecommendation,
        BigDecimal estimatedTotalCost,
        BigDecimal remainingBudget,
        LocalDateTime generatedAt,
        LocalDateTime createdAt) {
}
