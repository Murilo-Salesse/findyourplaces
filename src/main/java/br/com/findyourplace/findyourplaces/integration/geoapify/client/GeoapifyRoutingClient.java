package br.com.findyourplace.findyourplaces.integration.geoapify.client;

import br.com.findyourplace.findyourplaces.configuration.GeoapifyProperties;
import br.com.findyourplace.findyourplaces.exceptions.GeocodingUnavailableException;
import br.com.findyourplace.findyourplaces.integration.geoapify.dto.response.GeoapifyRoutingResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Component
public class GeoapifyRoutingClient {

    private final RestClient restClient;
    private final GeoapifyProperties geoapifyProperties;

    public GeoapifyRoutingClient(RestClient restClient, GeoapifyProperties geoapifyProperties) {
        this.restClient = restClient;
        this.geoapifyProperties = geoapifyProperties;
    }

    public GeoapifyRoutingResponse route(BigDecimal originLat,
                                         BigDecimal originLon,
                                         BigDecimal destinationLat,
                                         BigDecimal destinationLon) {
        try {
            return restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/routing")
                            .queryParam(
                                    "waypoints",
                                    originLat + "," + originLon
                                            + "|" +
                                            destinationLat + "," + destinationLon
                            )
                            .queryParam("mode", "drive")
                            .queryParam("apiKey", geoapifyProperties.apiKey())
                            .build())
                    .retrieve()
                    .body(GeoapifyRoutingResponse.class);
        } catch (ResourceAccessException ex) {
            throw new GeocodingUnavailableException();
        }
    }
}
