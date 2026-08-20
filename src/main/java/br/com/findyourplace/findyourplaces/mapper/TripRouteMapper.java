package br.com.findyourplace.findyourplaces.mapper;

import br.com.findyourplace.findyourplaces.entity.TripPlanEntity;
import br.com.findyourplace.findyourplaces.entity.TripRouteEntity;
import br.com.findyourplace.findyourplaces.service.model.CalculatedRoute;
import org.springframework.stereotype.Component;

@Component
public class TripRouteMapper {

    public TripRouteEntity toEntity(TripPlanEntity tripPlan, CalculatedRoute calculatedRoute) {

        var tripRoute = new TripRouteEntity();

        tripRoute.setTripPlan(tripPlan);
        tripRoute.setDirection(calculatedRoute.direction());
        tripRoute.setExternalProvider(calculatedRoute.externalProvider());
        tripRoute.setTransportMode(calculatedRoute.transportMode());
        tripRoute.setRouteType(calculatedRoute.routeType());
        tripRoute.setDistanceMeters(calculatedRoute.distanceMeters());
        tripRoute.setDurationSeconds(calculatedRoute.durationSeconds());
        tripRoute.setHasTolls(calculatedRoute.hasTolls());
        tripRoute.setHasFerry(calculatedRoute.hasFerry());
        tripRoute.setGeometry(calculatedRoute.geometry());
        tripRoute.setCalculatedAt(calculatedRoute.calculatedAt());

        return tripRoute;
    }
}
