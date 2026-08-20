package br.com.findyourplace.findyourplaces.integration.geoapify.dto.response;

import java.util.Map;

public record GeoapifyRouteFeature(GeoapifyRouteProperties properties,
                                   Map<String, Object> geometry) {
}
