package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.entity.TravelEstimateEntity;
import br.com.findyourplace.findyourplaces.entity.TripEntity;
import br.com.findyourplace.findyourplaces.entity.TripPlanEntity;
import br.com.findyourplace.findyourplaces.mapper.TravelEstimateMapper;
import br.com.findyourplace.findyourplaces.repository.TravelEstimateRepository;
import br.com.findyourplace.findyourplaces.service.model.CalculatedRoute;
import br.com.findyourplace.findyourplaces.service.model.TravelEstimate;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TravelEstimateService {

    private final TravelEstimateRepository travelEstimateRepository;
    private final TravelEstimateMapper travelEstimateMapper;

    public TravelEstimateService(TravelEstimateRepository travelEstimateRepository, TravelEstimateMapper travelEstimateMapper) {
        this.travelEstimateRepository = travelEstimateRepository;
        this.travelEstimateMapper = travelEstimateMapper;
    }

    private static final BigDecimal DEFAULT_FUEL_PRICE_PER_LITER = BigDecimal.valueOf(5.89);

    public TravelEstimate calculate(TripEntity trip, CalculatedRoute route) {

        // 1. Calcular distância em KM e duração em minutos
        BigDecimal distanceKm = route.distanceMeters()
                .divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);

        Integer durationMinutes = route.durationSeconds() / 60;

        // 2. Calcular se houver veiculo
        BigDecimal fuelLiters = null;
        BigDecimal estimatedFuelCost = BigDecimal.ZERO;

        if (trip.getVehicle() != null && trip.getVehicle().getHighwayConsumptionKmL() != null) {
            BigDecimal consumptionKmL = trip.getVehicle().getHighwayConsumptionKmL();

            // Litros = Distância (KM) / Consumo (KM/L)
            fuelLiters = distanceKm.divide(consumptionKmL, 2, RoundingMode.HALF_UP);

            // Custo = Litros * Preço por litro
            estimatedFuelCost = fuelLiters.multiply(DEFAULT_FUEL_PRICE_PER_LITER)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        // Por enquanto, o custo total é o custo do combustível (mais tarde podemos somar pedágios/hospedagem)
        BigDecimal estimatedTotalCost = estimatedFuelCost;

        // Orçamento restante = Orçamento da viagem - Custo total
        BigDecimal remainingBudget = BigDecimal.ZERO;
        if (trip.getBudget() != null) {
            remainingBudget = trip.getBudget().subtract(estimatedTotalCost);
        }

        return new TravelEstimate(
                distanceKm,
                durationMinutes,
                fuelLiters,
                estimatedFuelCost,
                estimatedTotalCost,
                remainingBudget,
                route.hasTolls(),
                route.hasFerry()
        );
    }

    @Transactional
    public TravelEstimateEntity save(TripPlanEntity tripPlan,
                                     TravelEstimate estimate,
                                     TripEntity trip) {

        BigDecimal fuelConsumptionKmL = null;

        if (trip.getVehicle() != null) {
            fuelConsumptionKmL = trip.getVehicle().getHighwayConsumptionKmL();
        }

        var entity = travelEstimateMapper.toEntity(
                tripPlan,
                estimate,
                DEFAULT_FUEL_PRICE_PER_LITER,
                fuelConsumptionKmL
        );

        return travelEstimateRepository.save(entity);
    }
}
