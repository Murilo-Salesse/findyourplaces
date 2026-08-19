package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateTripRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateTripResponseDTO;
import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.entity.VehicleEntity;
import br.com.findyourplace.findyourplaces.exceptions.EntityNotFoundException;
import br.com.findyourplace.findyourplaces.exceptions.UserNotFoundException;
import br.com.findyourplace.findyourplaces.mapper.TripMapper;
import br.com.findyourplace.findyourplaces.repository.TripRepository;
import br.com.findyourplace.findyourplaces.repository.UserRepository;
import br.com.findyourplace.findyourplaces.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final GeocodingService geocodingService;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public TripService(TripRepository tripRepository, TripMapper tripMapper, GeocodingService geocodingService, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.geocodingService = geocodingService;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public CreateTripResponseDTO create(CreateTripRequestDTO req, UUID userId) {

        var user = findUserOrThrow(userId);
        VehicleEntity vehicle = null;
        if (req.vehicleId() != null) {
            vehicle = findVehicleOrThrow(req.vehicleId(), userId);
        }

        var originCoordinates = geocodingService.geocode(req.originCity(), req.originState());
        var destinationCoordinates = geocodingService.geocode(req.destinationCity(), req.destinationState());

        var trip = tripMapper.toEntity(
                req,
                user,
                vehicle,
                originCoordinates,
                destinationCoordinates
        );

        var savedTrip = tripRepository.save(trip);

        return tripMapper.toResponse(savedTrip);
    }


    private UserEntity findUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuário não encontrado", "O usuário informado não existe."));
    }

    private VehicleEntity findVehicleOrThrow(UUID vehicleId, UUID userId) {
        return vehicleRepository.findByIdAndUserId(vehicleId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Veículo não encontrado", "O veículo informado não existe ou não pertence ao usuário."));
    }
}
