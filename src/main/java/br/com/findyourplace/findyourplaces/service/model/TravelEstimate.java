package br.com.findyourplace.findyourplaces.service.model;

import java.math.BigDecimal;

public record TravelEstimate(BigDecimal distanceKm,
                             Integer durationMinutes,
                             BigDecimal fuelLiters,
                             BigDecimal estimatedFuelCost,
                             BigDecimal estimatedTotalCost,
                             BigDecimal remainingBudget,
                             Boolean hasToll,
                             Boolean hasFerry) {
}
