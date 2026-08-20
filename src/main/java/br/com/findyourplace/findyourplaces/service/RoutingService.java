package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.entity.TripEntity;
import br.com.findyourplace.findyourplaces.enums.*;
import br.com.findyourplace.findyourplaces.exceptions.RoutingUnavailableException;
import br.com.findyourplace.findyourplaces.integration.geoapify.client.GeoapifyRoutingClient;
import br.com.findyourplace.findyourplaces.service.model.CalculatedRoute;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class RoutingService {

    private final GeoapifyRoutingClient geoapifyRoutingClient;

    public RoutingService(GeoapifyRoutingClient geoapifyRoutingClient) {
        this.geoapifyRoutingClient = geoapifyRoutingClient;
    }

    public CalculatedRoute calculatedRoute(TripEntity trip, RouteDirection direction) {

        BigDecimal originLat;
        BigDecimal originLon;
        BigDecimal destinationLat;
        BigDecimal destinationLon;

        if (direction == RouteDirection.RETURN) {

            originLat = trip.getDestinationLatitude();
            originLon = trip.getDestinationLongitude();
            destinationLat = trip.getOriginLatitude();
            destinationLon = trip.getOriginLongitude();
        } else {

            originLat = trip.getOriginLatitude();
            originLon = trip.getOriginLongitude();
            destinationLat = trip.getDestinationLatitude();
            destinationLon = trip.getDestinationLongitude();
        }

        RoutingMode routingMode = resolveRoutingMode(trip.getTransportType());

        var response = geoapifyRoutingClient.route(originLat,
                originLon,
                destinationLat,
                destinationLon,
                routingMode);

        if (response == null || response.features() == null || response.features().isEmpty()) {
            throw new RoutingUnavailableException();
        }

        var feature = response.features().getFirst();
        var properties = feature.properties();

        return new CalculatedRoute(direction, ExternalProvider.GEOAPIFY,
                routingMode,
                RouteType.BALANCED,
                properties.distance(),
                properties.time() != null ? properties.time().intValue() : 0,
                Boolean.TRUE.equals(properties.toll()),
                Boolean.TRUE.equals(properties.ferry()),
                feature.geometry(),
                LocalDateTime.now());
    }


    private RoutingMode resolveRoutingMode(TransportType type) {

        if (type == TransportType.MOTORCYCLE) {
            return RoutingMode.MOTORCYCLE;
        }

        return RoutingMode.DRIVE;
    }
}
