package br.com.findyourplace.findyourplaces.mapper;

import br.com.findyourplace.findyourplaces.entity.TravelEstimateEntity;
import br.com.findyourplace.findyourplaces.entity.TripPlanEntity;
import br.com.findyourplace.findyourplaces.service.model.TravelEstimate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelEstimateMapper {
    public TravelEstimateEntity toEntity(TripPlanEntity tripPlan,
                                         TravelEstimate estimate,
                                         BigDecimal fuelPricePerLiter,
                                         BigDecimal fuelConsumptionKmL) {

        var entity = new TravelEstimateEntity();
        entity.setTripPlan(tripPlan);

        // Distâncias e duração
        entity.setDistanceOneWayKm(estimate.distanceKm());
        entity.setTotalDistanceKm(estimate.distanceKm()); // Se for só ida por enquanto
        entity.setEstimatedDurationMinutes(estimate.durationMinutes());

        // Combustível
        entity.setFuelConsumptionKmL(fuelConsumptionKmL);
        entity.setFuelPricePerLiter(fuelPricePerLiter);
        entity.setEstimatedFuelLiters(estimate.fuelLiters());
        entity.setEstimatedFuelCost(estimate.estimatedFuelCost());

        // Custos adicionais (podem iniciar nulos ou zerados)
        entity.setEstimatedTollCost(BigDecimal.ZERO);
        entity.setEstimatedTicketCost(BigDecimal.ZERO);
        entity.setEstimatedAccommodationCost(BigDecimal.ZERO);
        entity.setEstimatedFoodCost(BigDecimal.ZERO);
        entity.setEmergencyReserve(BigDecimal.ZERO);

        // Total e Moeda
        entity.setTotalEstimatedCost(estimate.estimatedTotalCost());
        entity.setCurrency("BRL");

        return entity;
    }
}

