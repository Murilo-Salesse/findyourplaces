package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.controller.dto.response.TripPlanResponseDTO;
import br.com.findyourplace.findyourplaces.entity.TripEntity;
import br.com.findyourplace.findyourplaces.entity.TripPlanEntity;
import br.com.findyourplace.findyourplaces.enums.RouteDirection;
import br.com.findyourplace.findyourplaces.enums.TripPlanStatus;
import br.com.findyourplace.findyourplaces.exceptions.EntityNotFoundException;
import br.com.findyourplace.findyourplaces.mapper.TripPlanMapper;
import br.com.findyourplace.findyourplaces.repository.TripPlanRepository;
import br.com.findyourplace.findyourplaces.repository.TripRepository;
import br.com.findyourplace.findyourplaces.repository.TripRouteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TripPlanService {

    private final TripPlanRepository tripPlanRepository;
    private final TripRepository tripRepository;
    private final TripRouteService tripRouteService;
    private final TripPlanMapper tripPlanMapper;
    private final RoutingService routingService;

    public TripPlanService(TripPlanRepository tripPlanRepository, TripRepository tripRepository, TripRouteService tripRouteService,
                           TripPlanMapper tripPlanMapper, RoutingService routingService) {
        this.tripPlanRepository = tripPlanRepository;
        this.tripRepository = tripRepository;
        this.tripRouteService = tripRouteService;
        this.tripPlanMapper = tripPlanMapper;
        this.routingService = routingService;
    }

    @Transactional
    public void createPendingPlan(TripEntity trip) {

        var tripPlan = new TripPlanEntity();

        tripPlan.setTrip(trip);

        tripPlanRepository.save(tripPlan);
    }

    @Transactional
    public TripPlanResponseDTO generatePlan(UUID tripId, UUID userId) {

        // 1. Busca a viagem validando que pertence ao usuário logado
        var trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada", "Não foi possível encontrar a viagem."));

        // 2. Busca o planejamento inicial da viagem
        var tripPlan = tripPlanRepository.findByTripId(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Planejamento não encontrado", "A viagem ainda não possui um planejamento."));


        // 3. Muda o status usando o método de domínio que criei
        tripPlan.startProcessing();

        // 4. Calcular a rota de IDA (OUTBOUND)
        var outboundRoute = routingService.calculatedRoute(trip, RouteDirection.OUTBOUND);

        // 5. Persiste a rota no banco (tb_trip_routes)
        tripRouteService.saveRoute(tripPlan, outboundRoute);

        // (Futuramente aqui entrarão: TravelEstimateService, Places API, Gemini AI e tripPlan.complete)

        // 6. Retorna o DTO de resposta
        return tripPlanMapper.toResponse(tripPlan);
    }
}
