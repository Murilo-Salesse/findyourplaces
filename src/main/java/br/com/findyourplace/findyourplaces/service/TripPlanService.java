package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.repository.TripPlanRepository;
import org.springframework.stereotype.Service;

@Service
public class TripPlanService {

    private final TripPlanRepository tripPlanRepository;

    public TripPlanService(TripPlanRepository tripPlanRepository) {
        this.tripPlanRepository = tripPlanRepository;
    }
}
