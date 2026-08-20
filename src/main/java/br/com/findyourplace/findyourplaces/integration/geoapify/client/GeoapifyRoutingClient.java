package br.com.findyourplace.findyourplaces.integration.geoapify.client;

import br.com.findyourplace.findyourplaces.configuration.GeoapifyProperties;
import br.com.findyourplace.findyourplaces.enums.RoutingMode;
import br.com.findyourplace.findyourplaces.exceptions.GeocodingUnavailableException;
import br.com.findyourplace.findyourplaces.exceptions.RoutingUnavailableException;
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
                                         BigDecimal destinationLon,
                                         RoutingMode mode) {
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
                            .queryParam("mode", mode == null ? RoutingMode.DRIVE.getDescription() : mode.getDescription())
                            .queryParam("apiKey", geoapifyProperties.apiKey())
                            .build())
                    .retrieve()
                    .body(GeoapifyRoutingResponse.class);
        } catch (ResourceAccessException ex) {
            ex.printStackTrace(); // 👈 Isso vai imprimir o erro exato e a causa raiz no console da IDE
            throw new RoutingUnavailableException();
        }
    }
}
