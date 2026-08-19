package br.com.findyourplace.findyourplaces.integration.geoapify.dto.response;

import java.util.List;

public record GeoapifyRoutingResponse(List<GeoapifyRouteFeature> features) {
}
