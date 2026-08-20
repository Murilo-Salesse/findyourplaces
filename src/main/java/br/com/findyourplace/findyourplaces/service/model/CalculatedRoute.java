package br.com.findyourplace.findyourplaces.service.model;

import br.com.findyourplace.findyourplaces.enums.ExternalProvider;
import br.com.findyourplace.findyourplaces.enums.RouteDirection;
import br.com.findyourplace.findyourplaces.enums.RouteType;
import br.com.findyourplace.findyourplaces.enums.RoutingMode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public record CalculatedRoute(RouteDirection direction,
                              ExternalProvider externalProvider,
                              RoutingMode transportMode,
                              RouteType routeType,
                              BigDecimal distanceMeters,
                              Integer durationSeconds,
                              Boolean hasTolls,
                              Boolean hasFerry,
                              Map<String, Object> geometry,
                              LocalDateTime calculatedAt) {
}
