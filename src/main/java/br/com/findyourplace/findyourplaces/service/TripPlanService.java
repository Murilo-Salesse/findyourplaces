package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.entity.TripEntity;
import br.com.findyourplace.findyourplaces.entity.TripPlanEntity;
import br.com.findyourplace.findyourplaces.enums.TripPlanStatus;
import br.com.findyourplace.findyourplaces.exceptions.EntityNotFoundException;
import br.com.findyourplace.findyourplaces.repository.TripPlanRepository;
import br.com.findyourplace.findyourplaces.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TripPlanService {

    private final TripPlanRepository tripPlanRepository;
    private final TripRepository tripRepository;

    public TripPlanService(TripPlanRepository tripPlanRepository, TripRepository tripRepository) {
        this.tripPlanRepository = tripPlanRepository;
        this.tripRepository = tripRepository;
    }

    @Transactional
    public void createPendingPlan(TripEntity trip) {

        var tripPlan = new TripPlanEntity();

        tripPlan.setTrip(trip);
        tripPlan.setStatus(TripPlanStatus.PENDING);

        tripPlanRepository.save(tripPlan);
    }

    @Transactional
    public void generatePlan(UUID tripId, UUID userId) {

        var trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada", "Não foi possível encontrar a viagem."));

        var tripPlan = tripPlanRepository.findByTripId(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Planejamento não encontrado", "A viagem ainda não possui um planejamento."));

        // futuramente:
        // 1. Routing API
        // 2. Popular o TripRoute com as INFOS do Ruting API
        // 3. cálculos
        // 4. Places API
        // 5. IA -> Integração com o GEMINI
        // 6. atualizar TripPlan

    }
}
