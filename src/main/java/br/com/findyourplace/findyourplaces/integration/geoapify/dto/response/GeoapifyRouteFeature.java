package br.com.findyourplace.findyourplaces.integration.geoapify.dto.response;

import tools.jackson.databind.JsonNode;

public record GeoapifyRouteFeature(GeoapifyRouteProperties properties,
                                   JsonNode geometry) {
}
