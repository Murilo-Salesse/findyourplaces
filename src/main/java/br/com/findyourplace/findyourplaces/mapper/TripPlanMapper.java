package br.com.findyourplace.findyourplaces.mapper;

import br.com.findyourplace.findyourplaces.controller.dto.response.TripPlanResponseDTO;
import br.com.findyourplace.findyourplaces.entity.TripPlanEntity;
import org.springframework.stereotype.Component;

@Component
public class TripPlanMapper {

    public TripPlanResponseDTO toResponse(TripPlanEntity entity) {
        return new TripPlanResponseDTO(
                entity.getId(),
                entity.getTrip().getId(),
                entity.getStatus(),
                entity.getSummary(),
                entity.getAiRecommendation(),
                entity.getEstimatedTotalCost(),
                entity.getRemainingBudget(),
                entity.getGeneratedAt(),
                entity.getCreatedAt()
        );
    }
}
