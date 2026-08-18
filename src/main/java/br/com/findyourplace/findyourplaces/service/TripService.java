package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateTripRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateTripResponseDTO;
import br.com.findyourplace.findyourplaces.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public CreateTripResponseDTO create(CreateTripRequestDTO req, UUID userId) {
        return null;
    }
}
