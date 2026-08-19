package br.com.findyourplace.findyourplaces.mapper;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateTripRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateTripResponseDTO;
import br.com.findyourplace.findyourplaces.entity.TripEntity;
import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.entity.VehicleEntity;
import br.com.findyourplace.findyourplaces.service.model.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {

    public TripEntity toEntity(CreateTripRequestDTO request,
                               UserEntity user,
                               VehicleEntity vehicle,
                               Coordinates originCoordinates,
                               Coordinates destinationCoordinates) {
        var trip = new TripEntity();

        trip.setUser(user);
        trip.setVehicle(vehicle);
        trip.setTitle(request.title());

        trip.setOriginCity(request.originCity());
        trip.setOriginState(request.originState());
        trip.setOriginLatitude(originCoordinates.latitude());
        trip.setOriginLongitude(originCoordinates.longitude());

        trip.setDestinationCity(request.destinationCity());
        trip.setDestinationState(request.destinationState());
        trip.setDestinationLatitude(destinationCoordinates.latitude());
        trip.setDestinationLongitude(destinationCoordinates.longitude());

        trip.setBudget(request.budget());
        trip.setTravelersCount(request.travelersCount());
        trip.setTransportType(request.transportType());
        trip.setDepartureAt(request.departureAt());
        trip.setReturnAt(request.returnAt());

        return trip;
    }

    public CreateTripResponseDTO toResponse(TripEntity trip) {
        CreateTripResponseDTO.Vehicle vehicle = null;

        if (trip.getVehicle() != null) {
            vehicle = new CreateTripResponseDTO.Vehicle(
                    trip.getVehicle().getId(),
                    trip.getVehicle().getNickname(),
                    trip.getVehicle().getBrand(),
                    trip.getVehicle().getModel(),
                    trip.getVehicle().getYear()
            );
        }

        return new CreateTripResponseDTO(
                trip.getId(),
                trip.getTitle(),
                new CreateTripResponseDTO.Origin(
                        trip.getOriginCity(),
                        trip.getOriginState()
                ),
                new CreateTripResponseDTO.Destination(
                        trip.getDestinationCity(),
                        trip.getDestinationState()
                ),
                trip.getBudget(),
                trip.getTravelersCount(),
                trip.getTransportType().name(),
                vehicle,
                trip.getDepartureAt(),
                trip.getReturnAt(),
                trip.getStatus(),
                trip.getCreatedAt()
        );
    }
}
