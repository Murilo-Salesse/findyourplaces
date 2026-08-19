package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.repository.TripRouteRepository;
import org.springframework.stereotype.Service;

@Service
public class TripRouteService {

    private final TripRouteRepository tripRouteRepository;

    public TripRouteService(TripRouteRepository tripRouteRepository) {
        this.tripRouteRepository = tripRouteRepository;
    }
}
