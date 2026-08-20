package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.entity.TripPlanEntity;
import br.com.findyourplace.findyourplaces.entity.TripRouteEntity;
import br.com.findyourplace.findyourplaces.mapper.TripRouteMapper;
import br.com.findyourplace.findyourplaces.repository.TripRouteRepository;
import br.com.findyourplace.findyourplaces.service.model.CalculatedRoute;
import org.springframework.stereotype.Service;

@Service
public class TripRouteService {

    private final TripRouteRepository tripRouteRepository;
    private final TripRouteMapper tripRouteMapper;

    public TripRouteService(TripRouteRepository tripRouteRepository, TripRouteMapper tripRouteMapper) {

        this.tripRouteRepository = tripRouteRepository;
        this.tripRouteMapper = tripRouteMapper;
    }


    public TripRouteEntity saveRoute(TripPlanEntity tripPlan, CalculatedRoute calculatedRoute) {

        var routeEntity = tripRouteMapper.toEntity(tripPlan, calculatedRoute);
        return tripRouteRepository.save(routeEntity);
    }
}
